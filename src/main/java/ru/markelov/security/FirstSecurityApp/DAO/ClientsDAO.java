package ru.markelov.security.FirstSecurityApp.DAO;

import ru.markelov.security.FirstSecurityApp.models.ClientsDB;
import ru.markelov.security.FirstSecurityApp.models.Employee;

import java.util.List;
import java.util.Optional;

public interface ClientsDAO {
    public void addAllDevicesInClientDB(List<ClientsDB> clientsDBList);
    public void addClientDB();

    void saveClientDBInfo(ClientsDB clientsDB);

    public List<ClientsDB> getAllClientsDBInfo();
    public ClientsDB getClientDBInfo(int id);
    public void updateAllClientDBInfo();

    void deleteClientDB(ClientsDB clientsDB);

    public void clearDataBase();
    public Optional<Employee> findByUsername(String name);


}
