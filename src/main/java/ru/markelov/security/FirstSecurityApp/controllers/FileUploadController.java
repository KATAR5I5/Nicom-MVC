package ru.markelov.security.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.markelov.security.FirstSecurityApp.models.Employee;
import ru.markelov.security.FirstSecurityApp.models.UploadFileModel;
import ru.markelov.security.FirstSecurityApp.security.EmployeeDetails;
import ru.markelov.security.FirstSecurityApp.util.FileValidator;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {
    private FileValidator fileValidator;

    @Autowired
    public FileUploadController(FileValidator fileValidator) {
        this.fileValidator = fileValidator;
    }

    @ModelAttribute("thisEmp")
    public Employee authEmployee() {
        Employee emp = ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        return emp;
    }

    @PostMapping("/uploadFile1C")
    public String uploadFile1C(@ModelAttribute("fileUp") @Valid UploadFileModel uploadFile, BindingResult result) {// имена параметров (тут - "file") - из формы JSP.
        Employee currentEmp = ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
        Path Path_1C = Paths.get(currentEmp.getPath() + "\\" + currentEmp.getUsername() + "full.xlsx");
        MultipartFile file = uploadFile.getFile();
        fileValidator.validate(uploadFile,result);
        if(result.hasErrors()){
            return "errors";
        }
            try {
                byte[] bytes = file.getBytes();
                if (Files.deleteIfExists(Path_1C)) {
                    System.out.println("delete file");
                }
                Files.write(Path_1C, bytes);

                return "redirect:/generateReport1";
            } catch (Exception e) {
                return "You failed to upload  => " + e.getMessage();
            }
//        } else {
//            return "Выберете фаил 1С-Техника в отделениях в формате XLSX. Произведена попытка загрузить фаил типа - " + file.getResource().getDescription();
//        }
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
                return "admin";

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
