package com.lzyan.srpingbootquartz.ram.scheduler;

import com.lzyan.srpingbootquartz.ram.entity.Task;
import com.lzyan.srpingbootquartz.ram.job.QuartzJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 管理调度的任务
 *
 * @Author lzyan
 * @Date 2021/11/26 21:51
 * @Version 1.0
 */
@Configuration
public class SchedulerJobManager {

    @Autowired
    private Scheduler scheduler;

    /**
     * 开启定时任务
     *
     * @param task
     */
    public void startJob(Task task) throws SchedulerException {
        // 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
        // JobDetail 是具体Job实例
        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).withIdentity(task.getJobName(), task.getJobGroup())
                .usingJobData("jobName", task.getJobName()) //传值，在实现定时任务逻辑的时候取
                .usingJobData("jobGroup", task.getJobGroup())
                .usingJobData("todo", task.getTodo())
                .build();
        // 根据corn表达式创建触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ? *");//每5秒执行一次
        // CronTrigger表达式触发器继承于Trigger,常用的还有SimpleTrigger
        // TriggerBuilder 用于构建触发器实例
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(task.getJobName(), task.getJobGroup()).withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 获取job的信息
     */
    public String getJobInfo(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (cronTrigger == null) {
            return "cronTrigger is null";
        }
        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(), scheduler.getTriggerState(triggerKey).name());
    }

    /**
     * 暂停定时任务
     */
    public void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail != null) {
            scheduler.pauseJob(jobKey);
        }
    }

    /**
     * 暂停所有任务
     */
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    /**
     * 恢复所有任务
     */
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    /**
     * 恢复某个任务
     */
    public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail != null) {
            scheduler.resumeJob(jobKey);
        }
    }

    /**
     * 修改某个任务的执行时间
     */
    public boolean modifyJob(String name, String group, String time) throws SchedulerException {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldCron = cronTrigger.getCronExpression();
        if (!oldCron.equalsIgnoreCase(time)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(cronScheduleBuilder).build();
            date = scheduler.rescheduleJob(triggerKey, trigger);
        }
        return date != null;
    }

    /**
     * 删除某个任务
     *
     * @throws SchedulerException
     */
    public void deleteJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail != null) {
            scheduler.deleteJob(jobKey);
        }
    }
}
