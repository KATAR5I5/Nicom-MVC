package ru.markelov.security.FirstSecurityApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.markelov.security.FirstSecurityApp.DTO.ClientDTO;
import ru.markelov.security.FirstSecurityApp.models.ClientsDB;
import ru.markelov.security.FirstSecurityApp.models.Employee;
import ru.markelov.security.FirstSecurityApp.security.EmployeeDetails;
import ru.markelov.security.FirstSecurityApp.services.ClientService;
import ru.markelov.security.FirstSecurityApp.services.EmployeeDetailService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestControllerClient {
    private ClientService clientService;
    private final EmployeeDetailService employeeDetailService;

    @ModelAttribute("thisEmp")
    public Employee authEmployee() {
        Employee emp = ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        return emp;
    }

    @Autowired
    public RestControllerClient(ClientService clientService, EmployeeDetailService employeeDetailService) {
        this.clientService = clientService;
        this.employeeDetailService = employeeDetailService;
    }

    @GetMapping("/clientsJSON")
    public List<ClientDTO> getAllClientDTO() {
        List<ClientDTO> clientDTOList;
        clientDTOList = clientService.getAllClients(authEmployee().getId())
                .stream()
                .map(this::covertToClientDTO)
                .collect(Collectors.toList());

        return clientDTOList;
    }
    private ClientDTO covertToClientDTO(ClientsDB clientsDB){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(clientsDB, ClientDTO.class);
    }

}
