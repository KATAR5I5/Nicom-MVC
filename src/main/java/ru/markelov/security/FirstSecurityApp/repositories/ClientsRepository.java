package ru.markelov.security.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.markelov.security.FirstSecurityApp.models.ClientsDB;
@Repository
public interface ClientsRepository extends JpaRepository <ClientsDB, Integer> {


}
