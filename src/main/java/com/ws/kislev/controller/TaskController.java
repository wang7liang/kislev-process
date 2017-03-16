package com.ws.kislev.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ws.kislev.service.ProcessService;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
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
public class TaskController {
//    @Resource
//    private ProcessEngine processEngine;
//
//
//    @RequestMapping("/test/task-list")
//    public ModelAndView taskList(){
//        ModelAndView mav = new ModelAndView(("task-list"));
//
//        TaskService taskService = processEngine.getTaskService();
//
////        List<Task> doingTasks = taskService.createTaskQuery().taskAssignee("wangqiliang").list();
////        List<Task> waitClaimTasks = taskService.createTaskQuery().taskCandidateUser("wangqiliang").list();
////
////        List<Task> allTasks = new ArrayList<>();
////        allTasks.addAll(doingTasks);
////        allTasks.addAll(waitClaimTasks);
//
//        // 替代简便方法
//        List<Task> allTasks = taskService.createTaskQuery().taskCandidateOrAssigned("wangqiliang").list();
//
//        mav.addObject("tasks",allTasks);
//
//
//        return  mav;
//    }
//
//    @RequestMapping("/test/task/claim/{taskId}")
//    public ModelAndView taskClaim(@PathVariable("taskId") String taskId){
//        TaskService taskService = processEngine.getTaskService();
//        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//        taskService.claim(taskId,"wangqiliang");
//
//        return taskList();
//    }
//
//    @RequestMapping("test/task/getform/{taskId}")
//    public ModelAndView readTaskForm(@PathVariable("taskId") String taskId){
//        ModelAndView mav = new ModelAndView(("task-form"));
//
//        FormService formService = processEngine.getFormService();
//        TaskFormData taskFormData = formService.getTaskFormData(taskId);
//        mav.addObject("taskId",taskId);
//        mav.addObject("taskFormData",taskFormData);
//
//
//        return mav;
//    }
//
//    @RequestMapping("test/task/complete/{taskId}")
//    public ModelAndView completeTask(@PathVariable("taskId") String taskId, HttpServletRequest request){
//        FormService formService = processEngine.getFormService();
//        TaskFormData taskFormData = formService.getTaskFormData(taskId);
//        List<FormProperty> formProperties = taskFormData.getFormProperties();
//        Map<String,String> formValues = new HashMap<>();
//
//        for(FormProperty formProperty: formProperties){
//            if(formProperty.isWritable()){
//                String value = request.getParameter(formProperty.getId());
//                formValues.put(formProperty.getId(),value);
//            }
//        }
//        formService.submitTaskFormData(taskId,formValues);
//
//
//        return taskList();
//    }


}
