package ru.markelov.security.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.markelov.security.FirstSecurityApp.services.EmployeeDetailService;

@Controller
public class EmployeeController {
    private final EmployeeDetailService employeeDetailService;
    @Autowired
    public EmployeeController(EmployeeDetailService employeeDetailService) {
        this.employeeDetailService = employeeDetailService;
    }

    @GetMapping("/employee")
    public String showEmploee(Model model){
        model.addAttribute("listEmployee", employeeDetailService.getAllEmployees());
        return "employee";
    }
}
