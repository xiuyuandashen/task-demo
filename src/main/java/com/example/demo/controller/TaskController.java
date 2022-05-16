package com.example.demo.controller;

import com.example.demo.Task.parameterTask;
import com.example.demo.component.TaskScheduler;
import com.example.demo.component.classGenerate;
import com.example.demo.entity.TbTask;
import com.example.demo.entity.VO.ResultVo;
import com.example.demo.service.ITbTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/demo/task")
public class TaskController {

    @Autowired
    TaskScheduler taskScheduler;

    @Autowired
    ITbTaskService tbTaskService;

    /**
     * 添加并启动任务
     * @param tbTask
     * @return
     */
    @PostMapping
    public ResultVo addTask(@RequestBody TbTask tbTask){
        tbTaskService.save(tbTask);
        String taskId = tbTask.getTaskId();
        taskScheduler.start(taskId);
        return ResultVo.SUCCESS("添加并启动成功").data("taskId",taskId);
    }

    /**
     * 添加并启动任务，含参任务
     * @param tbTask
     * @param name
     * @return
     */
    @PostMapping("/{name}")
    public ResultVo addTask2(@RequestBody TbTask tbTask,@PathVariable("name") String name){
        tbTaskService.save(tbTask);
        String taskId = tbTask.getTaskId();
        parameterTask parameterTask = new parameterTask();
        parameterTask.setName(name);
        taskScheduler.start(taskId,parameterTask);
        return ResultVo.SUCCESS("添加并启动成功").data("taskId",taskId);
    }

    /**
     * 启动任务
     * @param taskId
     * @return
     */
    @GetMapping("/start/{taskId}")
    public ResultVo start(@PathVariable("taskId") String taskId){
        taskScheduler.start(taskId);
        return ResultVo.SUCCESS("启动成功").data("taskId",taskId);
    }

    /**
     * 停止任务
     * @param taskId
     * @return
     */
    @GetMapping("/stop/{taskId}")
    public ResultVo stop(@PathVariable("taskId") String taskId){
        taskScheduler.stop(taskId);
        return ResultVo.SUCCESS("停止任务").data("taskId",taskId);
    }

    @PostMapping("/generate")
    public ResultVo generateJava(@RequestBody HashMap<String,String> map) throws Exception {
        String path = map.get("path");
        String javaCode = map.get("javaCode");
        classGenerate.createStudentByFile(path,javaCode);
        return ResultVo.SUCCESS().message("代码生成成功");
    }
}
