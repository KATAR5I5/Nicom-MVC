package ru.markelov.security.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.markelov.security.FirstSecurityApp.models.Employee;
import ru.markelov.security.FirstSecurityApp.services.RegistrationService;
import ru.markelov.security.FirstSecurityApp.util.EmployeeValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;
    private final EmployeeValidator employeeValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, EmployeeValidator employeeValidator) {
        this.registrationService = registrationService;
        this.employeeValidator = employeeValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("employee") Employee employee) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("employee")  Employee employee, BindingResult bindingResult) {
        employeeValidator.validate(employee, bindingResult);
        if(bindingResult.hasErrors()){
        return "auth/registration";
        }
        registrationService.register(employee);

        return "redirect:/auth/login";
    }
}
