package com.lzyan.srpingbootquartz.ram.controller;

import com.lzyan.srpingbootquartz.ram.entity.Task;
import com.lzyan.srpingbootquartz.ram.scheduler.SchedulerJobManager;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author lzyan
 * @Date 2021/11/26 22:58
 * @Version 1.0
 */
@RestController
@RequestMapping("/quartz")
public class QuartzJobController {

    @Autowired
    private SchedulerJobManager schedulerJobManager;

    /**
     * 开启定时任务
     * 通过传入的数据，实现自定义定时任务的内容或者定时的时间等
     *
     * @param task
     */
    @PostMapping("/start")
    public boolean startQuartzJob(@RequestBody Task task) {
        try {
            schedulerJobManager.startJob(task);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取定时任务的信息
     *
     * @param task
     * @return
     */
    @PostMapping("/info")
    public String getQuartzJob(@RequestBody Task task) {
        String info = null;
        try {
            info = schedulerJobManager.getJobInfo(task.getJobName(), task.getJobGroup());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * 修改某个任务信息
     *
     * @param task
     * @return
     */
    @PostMapping("/modify")
    public boolean modifyQuartzJob(@RequestBody Task task) {
        boolean flag = true;
        try {
            flag = schedulerJobManager.modifyJob(task.getJobName(), task.getJobGroup(), task.getTime());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 暂停某个任务
     *
     * @param task
     * @return
     */
    @PostMapping("/pause")
    public boolean pauseQuartzJob(@RequestBody Task task) {
        try {
            schedulerJobManager.pauseJob(task.getJobName(), task.getJobGroup());
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 暂停所有任务
     *
     * @return
     */
    @PostMapping("/pauseAllJob")
    public boolean pauseAllQuartzJob() {
        try {
            schedulerJobManager.pauseAllJob();
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 恢复某个任务
     *
     * @param task
     * @return
     */
    @PostMapping("/resumeJob")
    public boolean resumeJob(@RequestBody Task task) {
        try {
            schedulerJobManager.resumeJob(task.getJobName(), task.getJobGroup());
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 恢复所有任务
     *
     * @return
     */
    @PostMapping("/resumeAllJob")
    public boolean resumeAllJob() {
        try {
            schedulerJobManager.resumeAllJob();
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }


    @PostMapping("/deleteJob")
    public boolean deleteJob(@RequestBody Task task) {
        try {
            schedulerJobManager.deleteJob(task.getJobName(), task.getJobGroup());
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

}
