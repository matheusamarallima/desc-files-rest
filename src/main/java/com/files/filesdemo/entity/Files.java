package com.files.filesdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Files {

    private String fileName;
    private String downloadLink;
    private String fileType;
    private long size;

}
