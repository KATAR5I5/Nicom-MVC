package ru.markelov.security.FirstSecurityApp.DAO;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.markelov.security.FirstSecurityApp.models.ClientsDB;
import ru.markelov.security.FirstSecurityApp.models.Employee;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.markelov.security.FirstSecurityApp.security.EmployeeDetails;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientsDAOImpl implements ClientsDAO {
    private EntityManager entityManager;

    @Autowired
    public ClientsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Employee authEmployee() {
        Employee emp = ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        return emp;
    }
    @Override
    public void addAllDevicesInClientDB(List<ClientsDB> clientsDBList) {
        Session session = entityManager.unwrap(Session.class);
        clientsDBList.stream()
                .forEach(clientsDB -> session.saveOrUpdate(clientsDB));
    }

    @Override
    public void addClientDB() {

    }

    @Override
    public void updateAllClientDBInfo() {

    }

    @Override
    public void saveClientDBInfo(ClientsDB clientsDB) {
//        получаем из базы клиента, меняем значения в колонках
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(clientsDB);
            }

    @Override
    public List<ClientsDB> getAllClientsDBInfo(int employeeID) {
        Session session = entityManager.unwrap(Session.class);
        Employee currentEmployee = session.get(Employee.class, employeeID);
        List<ClientsDB> allClientsCurrentEmployee = currentEmployee.getClientsDBList();
//        List<ClientsDB> allClients = session.createQuery("FROM ClientsDB").
//                getResultList();
        return allClientsCurrentEmployee;
    }

    @Override
    public ClientsDB getClientDBInfo(int id) {
        Session session = entityManager.unwrap(Session.class);
        //        Получаем клиента из таблицы по ID
        ClientsDB clientsDB = session.get(ClientsDB.class, id);

        return clientsDB;
    }

    @Override
    public void deleteClientDB(ClientsDB clientsDB) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(clientsDB);

    }

    @Override
    public void clearDataBase() {
        Session session = entityManager.unwrap(Session.class);
//        session.createQuery("truncate clients").executeUpdate();
        Employee employee = session.get(Employee.class, authEmployee().getId());
        System.out.println("clearMethod");
        List<ClientsDB> clientsDBCurrentEmployee = employee.getClientsDBList();

        System.out.println(clientsDBCurrentEmployee);
        System.out.println("clear-ok1");
        for (ClientsDB clientsDB: clientsDBCurrentEmployee){
            System.out.println(clientsDB);
            session.remove(clientsDB);

        }
//         session.
//        session.createSQLQuery("truncate table clients").executeUpdate();
//        session.clear();
    }

    @Override
    public Optional<Employee> findByUsername(String name) {
        Session session = entityManager.unwrap(Session.class);
        Employee employee = session.get(Employee.class, name);
        Optional<Employee> optionalEmployee = Optional.of(employee);
        return optionalEmployee;
    }

    public List<Employee> getAllEmployees() {
        Session session = entityManager.unwrap(Session.class);
        List<Employee> allEmployees = session.createQuery("from Employee").getResultList();
        return allEmployees;
    }
}


