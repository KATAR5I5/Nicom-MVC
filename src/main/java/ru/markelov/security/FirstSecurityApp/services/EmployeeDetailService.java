package ru.markelov.security.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.markelov.security.FirstSecurityApp.DAO.EmployeeDAO;
import ru.markelov.security.FirstSecurityApp.models.Employee;
import ru.markelov.security.FirstSecurityApp.repositories.EmployeeRepository;
import ru.markelov.security.FirstSecurityApp.security.EmployeeDetails;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeDetailService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeDetailService(EmployeeRepository employeeRepository, EmployeeDAO employeeDAO) {
        this.employeeRepository = employeeRepository;
        this.employeeDAO = employeeDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByUsername(username);
        if (employee.isEmpty()) {
            throw new UsernameNotFoundException("нет такого работника");
        }
        return new EmployeeDetails(employee.get());
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
}

