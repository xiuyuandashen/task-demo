package com.example.demo.component;

import com.example.demo.entity.TbTask;
import com.example.demo.mapper.TbTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Slf4j
@Component
public class TaskScheduler {

    //数据库的任务
    public static ConcurrentHashMap<String, TbTask> tasks = new ConcurrentHashMap<>(10);

    //正在运行的任务
    public static ConcurrentHashMap<String, ScheduledFuture> runTasks = new ConcurrentHashMap<>(10);

    //线程池任务调度
    private final ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

    @Autowired
    private TbTaskMapper tbTaskMapper;

    /**
     * 初始化线程池任务调度
     */
    @Autowired
    public TaskScheduler() {
        this.threadPoolTaskScheduler.setPoolSize(10);
        this.threadPoolTaskScheduler.setThreadNamePrefix("task-thread-");
        this.threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        this.threadPoolTaskScheduler.initialize();
    }


    /**
     * 获取所有数据库里的定时任务
     */
    public void getAllTbTask() {
        //查询所有，并put到tasks
        TaskScheduler.tasks.clear();
        List<TbTask> list = tbTaskMapper.selectList(null);
        list.forEach((task) -> TaskScheduler.tasks.put(task.getTaskId(), task));
    }

    /**
     * 根据定时任务id，启动定时任务
     */
    public void start(String taskId) {
        try {
            //如果为空，重新获取
            if (TaskScheduler.tasks.size() <= 0) {
                this.getAllTbTask();
            }
            TbTask tbTask = TaskScheduler.tasks.get(taskId);
            if (tbTask == null) {
                tbTask = tbTaskMapper.selectById(taskId);
            }
            if (tbTask == null)
                throw new RuntimeException("task不存在");

            //获取并实例化Runnable任务类
            Class<?> clazz = Class.forName(tbTask.getTaskClass());
            Runnable runnable = (Runnable) clazz.newInstance();

            //Cron表达式
            CronTrigger cron = new CronTrigger(tbTask.getTaskExp());

            //执行，并put到runTasks
            TaskScheduler.runTasks.put(taskId, Objects.requireNonNull(this.threadPoolTaskScheduler.schedule(runnable, cron)));

            this.updateTaskStatus(taskId, 1);

            log.info("{}，任务启动！", taskId);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            log.error("{}，任务启动失败...", taskId);
            e.printStackTrace();
        }

    }

    public void start(String taskId,Runnable runnable) {
        try {
            //如果为空，重新获取
            if (TaskScheduler.tasks.size() <= 0) {
                this.getAllTbTask();
            }
            TbTask tbTask = TaskScheduler.tasks.get(taskId);
            if (tbTask == null) {
                tbTask = tbTaskMapper.selectById(taskId);
            }
            if (tbTask == null)
                throw new RuntimeException("task不存在");
            //Cron表达式
            CronTrigger cron = new CronTrigger(tbTask.getTaskExp());

            //执行，并put到runTasks
            TaskScheduler.runTasks.put(taskId, Objects.requireNonNull(this.threadPoolTaskScheduler.schedule(runnable, cron)));

            this.updateTaskStatus(taskId, 1);

            log.info("{}，任务启动！", taskId);
        } catch (RuntimeException e) {
            log.error("{}，任务启动失败...", taskId);
            e.printStackTrace();
        }

    }

    /**
     * 根据定时任务id，停止定时任务
     */
    public void stop(String taskId) {
        // 停止任务
        TaskScheduler.runTasks.get(taskId).cancel(true);

        TaskScheduler.runTasks.remove(taskId);

        this.updateTaskStatus(taskId, 0);

        log.info("{}，任务停止...", taskId);
    }

    /**
     * 更新数据库动态定时任务状态
     */
    public void updateTaskStatus(String taskId, int status) {
        TbTask task = tbTaskMapper.selectById(taskId);
        task.setTaskStatus(status);
        task.setUpdateTime(new Date());
        tbTaskMapper.updateById(task);
    }

}
