package com.files.filesdemo.service;

import com.files.filesdemo.config.FilesStorageConfigProperties;
import com.files.filesdemo.exception.UploadFilesException;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FilesService {

    private final Path fileStorageLocation;


    public FilesService(FilesStorageConfigProperties filesStorageConfigProperties){
        this.fileStorageLocation = Paths.get(filesStorageConfigProperties.getUploadDir())
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(this.fileStorageLocation );
        } catch (IOException e) {
            throw new UploadFilesException("Something went wrong while creating the directory", e);
        }
    }
//    public String getContentType(HttpServletRequest request, Resource resource){
//        String contentType = null;
//        try{
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        }catch (Exception e){
//            log.error("");
//        }
//    }
}
