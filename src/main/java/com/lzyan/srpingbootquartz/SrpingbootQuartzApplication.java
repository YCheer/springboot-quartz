package com.lzyan.srpingbootquartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SrpingbootQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrpingbootQuartzApplication.class, args);
    }

}
