package com.github.company.util.template;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(@NotNull MultipartFile file, @NotNull Folder folder);

    void delete(@NotNull String path);
}