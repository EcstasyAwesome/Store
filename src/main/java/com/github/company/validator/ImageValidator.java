package com.github.company.validator;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return MultipartFile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        MultipartFile file = ((MultipartFile) target);
        long maxFileSize = 1024 * 1024;
        if (file.isEmpty()) errors.reject("Изображение обязательно");
        else if (file.getSize() > maxFileSize)
            errors.reject("Превышен максимальный размер файла " + maxFileSize / 1024 + " КБ");
    }
}
