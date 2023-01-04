package ru.markelov.security.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.markelov.security.FirstSecurityApp.models.Employee;
import ru.markelov.security.FirstSecurityApp.repositories.EmployeeRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class RegistrationService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public RegistrationService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void register(Employee employee) {
        /*
        создаем каталоги для файлов
        заполняем
         */
        if (employee.getUsername().equals("123")) {
            employee.setRole("ROLE_ADMIN");
        } else employee.setRole("ROLE_USER");
//        создаем каталог для работы с файлом данного сотрудника
        Path uploadFiles = Paths.get("src\\main\\load_files");
        Path dir1C = Paths.get(uploadFiles + "\\Emp" + employee.getUsername());
        Path pathFileDep = Paths.get(uploadFiles + "\\Dep.xlsx");
        try {
            Files.createDirectories(dir1C);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        employee.setPath(dir1C.toString());
        employee.setPathdep(pathFileDep.toString());

        employeeRepository.save(employee);
    }
}
