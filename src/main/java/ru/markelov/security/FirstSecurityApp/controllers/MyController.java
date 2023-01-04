package ru.markelov.security.FirstSecurityApp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.markelov.security.FirstSecurityApp.models.ClientsDB;
import ru.markelov.security.FirstSecurityApp.models.Employee;
import ru.markelov.security.FirstSecurityApp.security.EmployeeDetails;
import ru.markelov.security.FirstSecurityApp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {
    @Autowired
    private ClientService clientService;

    @ModelAttribute("thisEmp")
    public Employee authEmployee() {
        Employee emp = ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        return emp;
    }

    @GetMapping("/")
    public String index(Model model) {
//        Employee emp = ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
//        model.addAttribute("thisEmp", emp);
        List <String> currentDepartmentAttributes = new ArrayList<>();

        model.addAttribute("currentDepartment", currentDepartmentAttributes);
        return "select-files";
    }

    @GetMapping("/generateReport1")
    public String showAllClients() {
        /*
        - создаем список клиентов из файлов
        - получаем список из базы
        - очищаем базу
        - обновляем новый список
        - заполняем базу из нового списка

         */
//        System.out.println(emp);
        Path path1C = clientService.verify1CFile();
        Path pathDepartment = clientService.verifyDepartmentsFile();
        List<ClientsDB> newClientList = clientService
                .createListDevicesInDepartment(path1C, pathDepartment);
        System.out.println("ok1");
        System.out.println(newClientList);
        List<ClientsDB> oldClientList = clientService.getAllClients(authEmployee().getId());
        System.out.println("ok2");
        System.out.println(oldClientList);
        clientService.clearDataBase();
        System.out.println("ok3");
// Обновляет новый список
        clientService.updateAllClientsInfo(oldClientList, newClientList);
        System.out.println(newClientList);
        clientService.addAllDevicesInDepartment(newClientList);
        return "redirect:/currentDB";
    }

    @GetMapping("/currentDB")
    public String showCurrentDB(Model model) {
    /*
     - Получение списков из базы
     */
//        Employee emp = ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
//        model.addAttribute("thisEmp", emp);
        List<ClientsDB> clientsWithRepair = clientService.getClientsWithRepair();
        List<ClientsDB> clientsWithOutRepair = clientService.getClientsWithOutRepair();
        model.addAttribute("clientsWithRepair", clientsWithRepair);
        System.out.println(clientsWithRepair);
        model.addAttribute("clientsWithOutRepair", clientsWithOutRepair);
        System.out.println(clientsWithOutRepair);
        return "all-clients";
    }

    @GetMapping("/addFile1C")
    public String addFile1C() {

        return "redirect:/";
    }

    @RequestMapping("/addFileDepartments")
    public void addFileDepartments() {
    }

    @GetMapping("/clearClients")
    public String clearClientsDB() {
        clientService.clearDataBase();
        return "redirect:/";
    }

    @PostMapping(value = "/whatsAppWebSend", produces = "text/plain;charset=UTF-8")
    public String sendWhatsAppWebMessage(@RequestParam("clientDbID") int id, @RequestParam("clientTel") Long tel, Model model) {

        ClientsDB clientsDB = clientService.getClientDB(id);
//        URLEncoder.encode(clientsDB.getMassage(), StandardCharsets.UTF_8);

//        urlWhatsAppWeb.append(tel).append("&text=").append(clientsDB.getMassage());
        StringBuilder urlWhatsAppWebSend = new StringBuilder("https://web.whatsapp.com/send?phone=");
        urlWhatsAppWebSend.append(tel).append("&text=").append(URLEncoder.encode(clientsDB.getMassage(), StandardCharsets.UTF_8));

        clientService.updateStatusAndDateMessage(clientsDB);
        return "redirect:" + urlWhatsAppWebSend;
    }

    @PostMapping("/whatsAppApplicationSend")
    public String sendWhatsAppApplicationMessage(@RequestParam("clientDbID") int id, @RequestParam("clientTel") Long tel, Model model) {
        ClientsDB clientsDB = clientService.getClientDB(id);
        StringBuilder uriWhatsAppSend = new StringBuilder("https://api.whatsapp.com/send?phone=");
        uriWhatsAppSend.append(tel).append("&text=").append(URLEncoder.encode(clientsDB.getMassage(), StandardCharsets.UTF_8));

//        String uriWhatsAppSend = "https://api.whatsapp.com/send?phone=" + tel + "&text=" + clientsDB.getMassage();
        clientService.updateStatusAndDateMessage(clientsDB);
        return "redirect:" + uriWhatsAppSend;
    }

    @GetMapping("/remove")
    public String removeClientDB(@RequestParam("clientDbID") int id, Model model) {
        ClientsDB clientsDB = clientService.getClientDB(id);

        clientService.deleteClient(clientsDB);
        return "redirect:/currentDB";
    }

    @GetMapping("/admin")
    public String adminPAge(){
        return "admin";
    }

}
