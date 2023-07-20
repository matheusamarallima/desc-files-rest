package com.files.filesdemo.service;

import com.files.filesdemo.config.FilesStorageConfigProperties;
import com.files.filesdemo.exception.FileNotFoundException;
import com.files.filesdemo.exception.UploadFilesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
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

    public String getContentType(HttpServletRequest request, Resource resource){
        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (Exception e){
            log.error("File type not determined");
        }

        if(contentType == null){
            contentType = "application/octet-stream";
        }
        return contentType;
    }

    public String saveFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            if(fileName.contains("..")){
                throw new UploadFilesException("Invalid file");
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("Error while saving", e);
        }
    }

    public Resource downloadFile (String fileName){
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }else{
                throw new FileNotFoundException("File not found");
            }
        }catch (Exception e){
            throw new FileNotFoundException("File not found");
        }
    }
}
