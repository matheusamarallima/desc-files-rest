package com.files.filesdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReportService {

    @Async
    public void doJob() throws InterruptedException {
        log.info("Job initiated with success");
        Thread.sleep(5000);
        log.info("Job done");
    }
}
