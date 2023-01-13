package ru.markelov.security.FirstSecurityApp.models;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileModel{

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
