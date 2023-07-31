package com.files.filesdemo.controller;

import com.files.filesdemo.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/async")
public class ASyncController {

    @Autowired
    private ReportService reportService;

    @GetMapping()
    public ResponseEntity<String> executeASyncJob() throws InterruptedException {
        reportService.doJob();
        return ResponseEntity.ok("A Sync job done");
    }
}
