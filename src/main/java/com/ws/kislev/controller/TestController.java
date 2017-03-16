package com.ws.kislev.controller;

import com.ws.kislev.service.ProcessService;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipInputStream;


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
































