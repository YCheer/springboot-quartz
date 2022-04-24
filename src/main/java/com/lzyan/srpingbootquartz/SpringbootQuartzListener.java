package com.lzyan.srpingbootquartz;

import com.lzyan.srpingbootquartz.ram.entity.Task;
import com.lzyan.srpingbootquartz.ram.scheduler.SchedulerJobManager;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * spring 容器加载完毕后，启动定时任务
 *
 * @Author lzyan
 * @Date 2021/11/27 11:22
 * @Version 1.0
 */
@Configuration
public class SpringbootQuartzListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SchedulerJobManager schedulerJobManager;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        Task task = new Task();
//        task.setId(1);
//        task.setJobName("name1");
//        task.setJobGroup("group1");
//        task.setTodo("everything");
//        task.setTime("ddd");
//        try {
//            schedulerJobManager.startJob(task);
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
    }
}
