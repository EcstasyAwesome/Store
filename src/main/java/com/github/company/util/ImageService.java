package com.github.company.util;

import com.github.company.util.template.FileService;
import com.github.company.util.template.Folder;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.github.company.config.WebAppConfig.RESOURCES_PREFIX;

@Component
public class ImageService implements FileService {

    private static final Logger LOGGER = Logger.getLogger(ImageService.class);
    private final Environment env;
    private File storage;

    @Autowired
    public ImageService(Environment env) {
        this.env = env;
    }

    @PostConstruct
    public void init() {
        storage = new File(env.getProperty("application.storage"));
        for (Folder folder : Folder.values()) {
            File tmp = new File(storage, folder.getFolder());
            if (!tmp.exists())
                if (tmp.mkdirs()) LOGGER.info("Create folder " + tmp.getAbsolutePath());
                else {
                    LOGGER.fatal("Can`t create folder " + tmp.getAbsolutePath());
                    throw new ExceptionInInitializerError();
                }
        }
    }

    @Override
    public String upload(@NotNull MultipartFile file, @NotNull Folder folder) {
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File image = new File(storage, folder.getFolder() + File.separator + new Date().getTime() + type);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), image);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        return RESOURCES_PREFIX + image.getAbsolutePath().substring(storage.getAbsolutePath().length())
                .replaceAll(File.separator + File.separator, "/");
    }

    @Override
    public void delete(@NotNull String image) {
        File file = new File(storage, image.substring(RESOURCES_PREFIX.length()));
        if (file.exists())
            if (!file.delete()) LOGGER.error("Can`t delete file " + file.getAbsolutePath());
    }
}