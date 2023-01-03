package ru.markelov.security.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.markelov.security.FirstSecurityApp.models.ClientsDB;
import ru.markelov.security.FirstSecurityApp.repositories.ClientsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientServiceJPA {
    public final ClientsRepository clientsRepository;

    @Autowired
    public ClientServiceJPA(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public List<ClientsDB> findAll() {
        return clientsRepository.findAll();
    }

//    public ClientsDB findOne(int id){
//        Optional<ClientsDB> foundClientsDB = clientsRepository.findById(id);
//        return foundClientsDB.orElse(null);
//    }
    @Transactional
    public void deleteClientDB(ClientsDB client){
        clientsRepository.delete(client);
    }
    public void clearAllClientsDB(){
        clientsRepository.deleteAll();
    }
//    @Transactional
//    public void saveClientDB(ClientsDB client){
//        clientsRepository.save(client);
//    }
    @Transactional
    public void updateStatusAndDateMessage(ClientsDB client){
    clientsRepository.save(client);
    }

}
