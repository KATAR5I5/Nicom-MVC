package ru.markelov.security.FirstSecurityApp.services;

import ru.markelov.security.FirstSecurityApp.models.ClientsDB;
import org.springframework.transaction.annotation.Transactional;
import ru.markelov.security.FirstSecurityApp.models.Employee;

import java.nio.file.Path;
import java.util.List;
@Transactional
public interface ClientService {
    public Path verify1CFile();
    public Path verifyDepartmentsFile();
//    @Transactional
    public List<ClientsDB> getAllClients(int employeeID);
    @Transactional
    public List<ClientsDB> getClientsWithRepair();
    @Transactional
    public List<ClientsDB> getClientsWithOutRepair();
    @Transactional
    public void clearDataBase();
    @Transactional
    void updateStatusAndDateMessage(ClientsDB clientsDB);
    public void updateAllClientsInfo(List<ClientsDB> oldClientList, List<ClientsDB> newClientList);
    @Transactional
    void deleteClient(ClientsDB clientsDB);
    @Transactional
   public void addAllDevicesInDepartment(List<ClientsDB> clientsDBList);
    @Transactional
    public ClientsDB getClientDB(int id);
    @Transactional
    public List<ClientsDB> createListDevicesInDepartment(Path path1C, Path pathDepartments);
}



//    public void load1CFile();
//    public void loadDepartmentsFile();
//    public void addDateSendMessageInClientDB();
//
//    public void addStatusMessage();
//        /*
//    - получить по ID клиента из базы
//    - добавить значок "Отправлено" в колонку Статус
//     */
//
//    public void sendMessageToClient();
//    public void reSendMessageToClient();
