package ru.markelov.security.FirstSecurityApp.services;

import ru.markelov.security.FirstSecurityApp.models.ClientsDB;
import org.springframework.transaction.annotation.Transactional;
import ru.markelov.security.FirstSecurityApp.models.Employee;

import java.nio.file.Path;
import java.util.List;

public interface ClientService {
    public List<ClientsDB> getAllClients();

    public List<ClientsDB> getClientsWithRepair();

    public List<ClientsDB> getClientsWithOutRepair();


    public void clearDataBase();
//            Очистить базу данных, сбросить Primary Key
//            Удалить загруженные файлы


//    load files

    public void load1CFile();
    public void loadDepartmentsFile();
    public Path verify1CFile();
    public Path verifyDepartmentsFile();

    void updateStatusAndDateMessage(ClientsDB clientsDB);

    public void updateAllClientsInfo(List<ClientsDB> oldClientList, List<ClientsDB> newClientList);

    @Transactional
    void deleteClient(ClientsDB clientsDB);

    public void addDateSendMessageInClientDB();
    public void addStatusMessage();

    public void sendMessageToClient();
    public void reSendMessageToClient();

    @Transactional
   public void addAllDevicesInDepartment(List<ClientsDB> clientsDBList);

    public ClientsDB getClientDB(int id);
    public List<ClientsDB> createListDevicesInDepartment(Path path1C, Path pathDepartments);
//    public Optional<Employee> findByUsername(String name);
//public UserDetails loadUserByUsername(String username);

}
