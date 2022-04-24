package com.lzyan.srpingbootquartz.ram.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.time.LocalDateTime;

/**
 * @Author lzyan
 * @Date 2021/11/26 22:05
 * @Version 1.0
 */
public class QuartzJob implements Job {

    private String jobName;
    private String jobGroup;
    private String todo;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("执行定时任务的实现逻辑");
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        String todo = getTodo();
        System.out.println("接收自定义参数,定时任务:{name:" + jobKey.getName() + ",group:" + jobKey.getGroup() + "}" + ",todo:" + todo + LocalDateTime.now());
    }
}
