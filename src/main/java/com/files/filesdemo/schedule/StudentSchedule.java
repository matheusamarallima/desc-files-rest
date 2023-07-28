package com.files.filesdemo.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StudentSchedule {

    @Scheduled(cron = "0 0 12 * * *") /* = todo dia, mes e dia da semana */
    public void executeJob(){
        log.info("Job executed with success");
    }
}
