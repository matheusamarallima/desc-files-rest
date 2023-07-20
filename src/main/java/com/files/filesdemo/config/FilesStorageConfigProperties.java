package com.files.filesdemo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FilesStorageConfigProperties {

    @Value("${file.uploadDir}")
    private String uploadDir;
}
