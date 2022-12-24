package ru.markelov.security.FirstSecurityApp.services;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.markelov.security.FirstSecurityApp.aspect.logic.CreateNicomObjects;
import ru.markelov.security.FirstSecurityApp.aspect.logic.RepairMessage;
import ru.markelov.security.FirstSecurityApp.aspect.logic.StatusMessage;
import ru.markelov.security.FirstSecurityApp.DAO.ClientsDAO;
import ru.markelov.security.FirstSecurityApp.models.ClientsDB;
import ru.markelov.security.FirstSecurityApp.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.markelov.security.FirstSecurityApp.security.EmployeeDetails;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientsDAO clientsDAO;
    private LocalDate today = LocalDate.now();
    private CreateNicomObjects nicom = null;

    private Employee authEmployee() {
        Employee emp = ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        return emp;
    }

    @Override
    public List<ClientsDB> getAllClients(int employeeID) {
        return clientsDAO.getAllClientsDBInfo(employeeID);
    }

    @Override
    public List<ClientsDB> getClientsWithRepair() {
        List<ClientsDB> clientsWithRepair = clientsDAO.getAllClientsDBInfo(authEmployee().getId()).stream()
                .filter(e -> e.getPriceToRepair() > 1)
                .sorted()
                .collect(Collectors.toList());
        return clientsWithRepair;
    }

    @Override
    public Path verify1CFile() {
        Employee emp = ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        /*
        - сравнить файлы
        - вернуть путь нужного файла
        - удалить неактуальный фаил
         */
        Path path1C = Path.of(emp.getPath() + "\\" + emp.getUsername() + "full.xlsx");
        return path1C;
    }

    @Override
    public Path verifyDepartmentsFile() {
        Employee emp = ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
               /*
        - сравнить файлы
        - вернуть путь нужного файла
        - удалить неактуальный фаил
         */
        Path pathDepartments = Path.of(emp.getPathdep());
        return pathDepartments;
    }

    @Override
    public void updateStatusAndDateMessage(ClientsDB clientsDB) {
        clientsDB.setStatusMessage(StatusMessage.SENT);
        clientsDB.setLocalDate(today);
        clientsDAO.saveClientDBInfo(clientsDB);
    }


    //    @Transactional
    @Override
    public void updateAllClientsInfo(List<ClientsDB> oldClientList, List<ClientsDB> newClientList) {
        /*
        - создаем новую таблицу
        - заполняем таблицу сравнивая с существующей
        - ЕСЛИ НОВЫЙ добавляем клиента с пустыми колонками дата и статус если новый
        - ЕСЛИ СУЩЕСТВУЕТ добавляем клиента из старой базы с ДАТОЙ и кнопкой RELOAD
        - удаляем старую таблицу


         */
        for (ClientsDB clientInOldList : oldClientList) {
            if (newClientList.contains(clientInOldList)) {
                ClientsDB clientInNewList = newClientList.get(newClientList.indexOf(clientInOldList));
                if (clientInOldList.getStatusMessage() == null) {
                    clientInNewList.setStatusMessage(StatusMessage.NOT_SENT);
                } else {
                    clientInNewList.setStatusMessage(clientInOldList.getStatusMessage());
                    clientInNewList.setLocalDate(clientInOldList.getLocalDate());
                }
            }
        }

    }

    @Override
    public List<ClientsDB> getClientsWithOutRepair() {
        List<ClientsDB> clientsWithOutRepair = clientsDAO.getAllClientsDBInfo(authEmployee().getId())
                .stream()
                .filter(e -> e.getPriceToRepair() < 1)
                .sorted()
                .collect(Collectors.toList());
        return clientsWithOutRepair;
    }

    @Override
    public void clearDataBase() {
        clientsDAO.clearDataBase();
    }

    @Override
    public void deleteClient(ClientsDB clientsDB) {
        clientsDAO.deleteClientDB(clientsDB);
    }

    public List<ClientsDB> createListDevicesInDepartment(Path path1C, Path pathDepartments) {
        /*
        - Создать МАР устройств и клиентов из 1с документа

         */
//        Создаем МАР устройств и клиентов из 1с документа
        try {
            nicom = new CreateNicomObjects(path1C);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Thread thread1 = new Thread(nicom);
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        RepairMessage repairMessage = new RepairMessage(pathDepartments);
        List<ClientsDB> clientsDBList = nicom.getMapDevice()
                .entrySet()
                .stream()
                .map(device -> {
                    ClientsDB clientsDB = new ClientsDB();
                    clientsDB.setDepartment(device.getKey().getDepartment());
                    clientsDB.setTicketNumber(device.getKey().getTicketNumber());
                    clientsDB.setFirstName(device.getValue().getFirstName());
                    clientsDB.setSecondName(device.getValue().getSecondName());
                    clientsDB.setThirdName(device.getValue().getThirdName());
                    clientsDB.setDevice(device.getKey().getDevice());
                    clientsDB.setPriceToRepair(device.getKey().getPriceToRepair());
                    clientsDB.setPhoneNumberOne(device.getValue().getPhoneNumberOne());
                    clientsDB.setPhoneNumberTwo(device.getValue().getPhoneNumberTwo());
                    String message = repairMessage.getMessageComplete(device.getKey(), device.getValue());
                    clientsDB.setMassage(message);
                    clientsDB.setOwner(authEmployee());
//                    clientsDB.setLocalDate(LocalDate.now());
//                    clientsDB.setStatusMessage();
                    return clientsDB;
                })
                .collect(Collectors.toList());
        return clientsDBList;
    }

    @Override
    public void addAllDevicesInDepartment(List<ClientsDB> clientsDBList) {
        clientsDAO.addAllDevicesInClientDB(clientsDBList);
    }

    @Override
    public ClientsDB getClientDB(int id) {
        ClientsDB clientsDB = clientsDAO.getClientDBInfo(id);
        return clientsDB;
    }

    public Optional<Employee> findByUsername(String name) {
        return clientsDAO.findByUsername(name);
    }

}

