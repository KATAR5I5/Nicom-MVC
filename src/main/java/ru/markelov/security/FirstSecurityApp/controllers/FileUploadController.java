package ru.markelov.security.FirstSecurityApp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.markelov.security.FirstSecurityApp.models.Employee;
import ru.markelov.security.FirstSecurityApp.security.EmployeeDetails;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {


    @ModelAttribute("thisEmp")
    public Employee authEmployee(){
        Employee emp = ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        return emp;
    }

    @PostMapping("/uploadFile1C")
    public String uploadFile1C(@RequestParam("file") MultipartFile file, Model model) {// имена параметров (тут - "file") - из формы JSP.
        Employee currentEmp = ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        Path Path_1C = Paths.get(currentEmp.getPath() + "\\" + currentEmp.getUsername() + "full.xlsx");
//        System.out.println(Path_1C);
//        System.out.println(Path_1C.toFile().getAbsolutePath());
        if (!file.isEmpty() && file.getOriginalFilename().endsWith("xlsx")) {
            try {
                byte[] bytes = file.getBytes();
                if (Files.deleteIfExists(Path_1C)) {
                    System.out.println("delete file");
                }
                Files.write(Path_1C, bytes);
                String s = "Загружено - " + file.getOriginalFilename();
                model.addAttribute("file1C", s);
//                model.addAttribute("thisEmp",currentEmp);
//                return "redirect:/uploadStatus";
                return "select-files";
            } catch (Exception e) {
                return "You failed to upload  => " + e.getMessage();
            }
        } else {
            return "Выберете фаил 1С-Техника в отделениях в формате XLSX. Произведена попытка загрузить фаил типа - " + file.getResource().getDescription();
        }
    }

    @PostMapping("/uploadFileDep")
    public String uploadFileDep(@RequestParam("file2") MultipartFile file, Model model) {// имена параметров (тут - "file") - из формы JSP.
        Employee currentEmp = ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        Path newPathDep = Paths.get(currentEmp.getPathdep());
        if (!file.isEmpty() && file.getOriginalFilename().endsWith("xlsx")) {
            try {
                byte[] bytes = file.getBytes();
                if (Files.deleteIfExists(newPathDep)) {
                    System.out.println("delete file");
                }
                Files.write(newPathDep, bytes);
                String s = "Загружено - " + file.getOriginalFilename();
                model.addAttribute("dep", s);
//                model.addAttribute("thisEmp",currentEmp);
                return "select-files";

            } catch (Exception e) {
                return "You failed to upload  => " + e.getMessage();
            }
        } else {
            return "Выберете фаил 1С-Техника в отделениях в формате XLSX. Произведена попытка загрузить фаил типа - " + file.getResource().getDescription();
        }
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus(Model model) {
        return "redirect:/";
    }
}
