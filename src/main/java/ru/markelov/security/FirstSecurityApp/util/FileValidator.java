package ru.markelov.security.FirstSecurityApp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.markelov.security.FirstSecurityApp.models.UploadFileModel;
@Component
public class FileValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UploadFileModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UploadFileModel uploadFile = (UploadFileModel) target;
//        || uploadFile.getFile().getOriginalFilename().endsWith("xlsx")
        if(uploadFile.getFile().getSize() == 0){
            errors.rejectValue("file","Выберете файл");
        }
        if(!uploadFile.getFile().getOriginalFilename().endsWith("xlsx")){
            errors.rejectValue("file","Не верный формат!");
        }
    }
}
