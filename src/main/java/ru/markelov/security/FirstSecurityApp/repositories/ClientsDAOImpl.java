package ru.markelov.security.FirstSecurityApp.repositories;

import ru.markelov.security.FirstSecurityApp.models.ClientsDB;
import ru.markelov.security.FirstSecurityApp.models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public List<ClientsDB> getAllClientsDBInfo() {
        Session session = entityManager.unwrap(Session.class);

        List<ClientsDB> allClients = session.createQuery("FROM ClientsDB").getResultList();
        return allClients;
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
        session.createQuery("delete ClientsDB ").executeUpdate();
        session.createSQLQuery("ALTER TABLE clients AUTO_INCREMENT=1").executeUpdate();
    }

    @Override
    public Optional<Employee> findByUsername(String name) {
        Session session = entityManager.unwrap(Session.class);
        Employee employee = session.get(Employee.class, name);
        Optional<Employee> optionalEmployee = Optional.of(employee);
        return optionalEmployee;
    }
}
