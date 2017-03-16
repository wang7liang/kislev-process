package com.ws.kislev.controller;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by macmini-g1hw on 17/3/7.
 */
@RestController
@RequestMapping("/demo/task")
public class DemoTaskController {
    @Resource
    private ProcessEngine processEngine;


    @RequestMapping("/list")
    public ModelAndView taskList(){
        ModelAndView mav = new ModelAndView(("demo/task-list"));

        TaskService taskService = processEngine.getTaskService();

        // 替代简便方法
        List<Task> allTasks = taskService.createTaskQuery().taskCandidateOrAssigned("wangqiliang").list();

        mav.addObject("tasks",allTasks);


        return  mav;
    }

    @RequestMapping("/claim/{taskId}")
    public ModelAndView taskClaim(@PathVariable("taskId") String taskId){
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        taskService.claim(taskId,"wangqiliang");

        return taskList();
    }

    @RequestMapping("/getform/{taskId}")
    public ModelAndView readTaskForm(@PathVariable("taskId") String taskId){
        ModelAndView mav = new ModelAndView(("demo/commonTask-form"));

        FormService formService = processEngine.getFormService();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        String formKey = task.getFormKey();
        if(formKey!=null &&!formKey.equals("")){
            mav.addObject("taskId",taskId);
            mav.setViewName("redirect:/"+formKey+"?taskId="+taskId);
            return mav;
        }else{
            String taskFormKey = formService.getTaskFormKey("", "");

            TaskFormData taskFormData = formService.getTaskFormData(taskId);
            mav.addObject("taskId",taskId);
            mav.addObject("taskFormData",taskFormData);
        }

        return mav;
    }

    @RequestMapping("/complete/{taskId}")
    public ModelAndView completeTask(@PathVariable("taskId") String taskId, HttpServletRequest request){

        FormService formService = processEngine.getFormService();
        TaskFormData taskFormData = formService.getTaskFormData(taskId);
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        Map<String,String> formValues = new HashMap<>();


        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for(Map.Entry<String,String[]> entry:entries){
            String key = entry.getKey();
            formValues.put(key,entry.getValue()[0]);
        }


        formService.submitTaskFormData(taskId,formValues);




        return taskList();
    }


}
