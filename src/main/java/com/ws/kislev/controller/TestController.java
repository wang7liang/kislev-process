package com.ws.kislev.controller;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by macmini-g1hw on 17/3/7.
 */
@RestController
public class TestController {

    @Resource
    private ProcessEngine processEngine;

    /**
     * Hello World测试
     * @return
     */
    @RequestMapping("/test/hello")
    public String hello(){
        // 可以从processEngine中取得所有服务
        HistoryService historyService = processEngine.getHistoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        TaskService taskService = processEngine.getTaskService();

        // 查询历史流程实例数据
        // XXXService.createYYYQuery()
        List<HistoricProcessInstance> finishList = historyService.createHistoricProcessInstanceQuery().finished().list();

        // 启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("helloworld");


        // 查询运行中流程实例数据
        List<ProcessInstance> runtimeList = runtimeService.createProcessInstanceQuery().list();


        // 查询任务
        Task task = taskService.createTaskQuery().taskAssignee("wangqiliang").singleResult();
        // 完成任务
        taskService.complete(task.getId());

        // 再次查询运行中流程实例数据
        runtimeList = runtimeService.createProcessInstanceQuery().list();

        // 再次查询历史流程数据
        finishList = historyService.createHistoricProcessInstanceQuery().finished().list();

        return "success";
    }


}
































