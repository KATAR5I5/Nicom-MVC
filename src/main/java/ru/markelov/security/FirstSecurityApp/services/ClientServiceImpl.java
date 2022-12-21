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

    @Override
    @Transactional
    public List<ClientsDB> getAllClients() {
        return clientsDAO.getAllClientsDBInfo();
    }

    @Override
    @Transactional
    public List<ClientsDB> getClientsWithRepair() {

        List<ClientsDB> clientsWithRepair = clientsDAO.getAllClientsDBInfo().stream()
                .filter(e -> e.getPriceToRepair() > 1)
                .sorted()
                .collect(Collectors.toList());
        return clientsWithRepair;
    }

    @Override
    public void load1CFile() {

    }
    /*
    - Загрузить фаил в папку
    - Проверить, существует ли фаил
    - Произвести действие или вызвать небходимый метод
     */

    @Override
    public void loadDepartmentsFile() {
    /*
    - Загрузить фаил в папку
    - Проверить, существует ли фаил
    - Произвести действие или вызвать небходимый метод
     */
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
    @Transactional
    public void updateStatusAndDateMessage(ClientsDB clientsDB) {
        clientsDB.setStatusMessage(StatusMessage.SENT);
        clientsDB.setLocalDate(today);
        clientsDAO.saveClientDBInfo(clientsDB);
    }


    @Transactional
    @Override
    public List<ClientsDB> updateAllClientsInfo(List<ClientsDB> oldClient) {
        /*
        - создаем новую таблицу
        - заполняем таблицу сравнивая с существующей
        - ЕСЛИ НОВЫЙ добавляем клиента с пустыми колонками дата и статус если новый
        - ЕСЛИ СУЩЕСТВУЕТ добавляем клиента из старой базы с ДАТОЙ и кнопкой RELOAD
        - удаляем старую таблицу


         */
        List<ClientsDB> newAllClients = getAllClients();
//        clearDataBase();
//        addAllDevicesInDepartment(newAllClients);
//        List<ClientsDB> updateList = new ArrayList<>();
        for (ClientsDB client : oldClient) {
            if (newAllClients.contains(client)) {
                if (client.getStatusMessage() == null) {
                    client.setStatusMessage(StatusMessage.NOT_SENT);
                }

                saveClientDBInfo(client);
            }
        }
        return newAllClients;


//        Set<ClientsDB> setNewClients = Set.copyOf(newAllClients);
//        for (ClientsDB clients : oldClient) {
//            if (setNewClients.contains(clients)) {
//                if (clients.getStatusMessage() == null) {
//                    clients.setStatusMessage(StatusMessage.NOT_SENT);
//                }
//                setNewClients.add(clients);
//            }
//        }
//        System.out.println("new " + newClients.size());
//        System.out.println("old " + oldClient.size());
//        for (ClientsDB client : oldClient) {
//            if (newAllClients.contains(client)) {
//                ClientsDB temp = newAllClients.get(newAllClients.indexOf(client));
//                if (client.getStatusMessage() == null || client.getStatusMessage() == StatusMessage.NOT_SENT) {
//                    temp.setStatusMessage(StatusMessage.NOT_SENT);
////                    client.setStatusMessage(StatusMessage.NOT_SENT);
//                } else {
//                    temp.setStatusMessage(client.getStatusMessage());
//                    temp.setLocalDate(client.getLocalDate());
//                }
//
//            }
//        }
//        System.out.println("new update " + newAllClients.size());
//        List<ClientsDB> nl = List.copyOf(newAllClients);
//        clearDataBase();
//        System.out.println("new before " + newClients.size());
//        System.out.println("new before " + newAllClients.size());
//        clearDataBase();
//        addAllDevicesInDepartment(newAllClients);
//        return newAllClients;
    }

    @Override
    public void addStatusMessage() {
    /*
    - получить по ID клиента из базы
    - добавить значок "Отправлено" в колонку Статус
     */
    }

    @Override
    public void sendMessageToClient() {
    /*
    - перейти на страницу WhatsApp с номером клиента и текстом сообщения
    - получить ответ после нажатия кнопки отправить
    - добавить в поле "Дата" - актуальную дату
    - добавить значок "Отправлено" в колонку Статус
    - обновить в базе информацию о клиенте
     */


    }

    @Override
    public void reSendMessageToClient() {
    /*
    - отправить текст еще раз (вызвать метод sendMessageToClient)
    - обновить поле "Дата" - на актуальную дату
    - добавить значок "Отправлено" в колонку Статус
    - обновить в базе информацию о клиенте

     */
    }
    @Transactional
    public void saveClientDBInfo(ClientsDB clientsDB){
        clientsDAO.saveClientDBInfo(clientsDB);
    }

    @Override
    @Transactional
    public List<ClientsDB> getClientsWithOutRepair() {
        List<ClientsDB> clientsWithOutRepair = clientsDAO.getAllClientsDBInfo()
                .stream()
                .filter(e -> e.getPriceToRepair() < 1)
                .sorted()
                .collect(Collectors.toList());
        return clientsWithOutRepair;
    }

    @Override
    @Transactional
    public void clearDataBase() {
        clientsDAO.clearDataBase();
    }

    @Override
    @Transactional
    public void deleteClient(ClientsDB clientsDB) {
        /*

         */
        clientsDAO.deleteClientDB(clientsDB);
    }

    public void addDateSendMessageInClientDB() {
        /*
    - получить по ID клиента из базы
    - добавить актуальную дату
    - добавить значок "Отправлено" в колонку Статус
         */

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
//                    clientsDB.setLocalDate(LocalDate.now());
//                    clientsDB.setStatusMessage();
                    return clientsDB;
                })
                .collect(Collectors.toList());
        return clientsDBList;
    }

    @Override
    @Transactional
    public void addAllDevicesInDepartment(List<ClientsDB> clientsDBList) {
        clientsDAO.addAllDevicesInClientDB(clientsDBList);
    }

    @Override
    @Transactional
    public ClientsDB getClientDB(int id) {
        ClientsDB clientsDB = clientsDAO.getClientDBInfo(id);
        return clientsDB;
    }

    public Optional<Employee> findByUsername(String name) {
        return clientsDAO.findByUsername(name);
    }

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Employee> emp = clientsDAO.findByUsername(username);
//        if (emp.isEmpty()) {
//            throw new UsernameNotFoundException("User not found");
//
//        }
//        return new EmployeeDetails(emp.get());
//    }


}

