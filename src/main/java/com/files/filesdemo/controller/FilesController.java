package com.files.filesdemo.controller;


import com.files.filesdemo.entity.Files;
import com.files.filesdemo.service.FilesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FilesController {

    private FilesService filesService;

    @PostMapping("/upload")
    public Files uploadFiles(@RequestParam("file") MultipartFile file){
        String fileName = filesService.saveFile(file);

        String filePath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
        return new Files(fileName, filePath, file.getContentType(), file.getSize());
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable String fileName, HttpServletRequest request){
       Resource resource = filesService.downloadFile(fileName);
       String contentType = filesService.getContentType(request, resource);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
