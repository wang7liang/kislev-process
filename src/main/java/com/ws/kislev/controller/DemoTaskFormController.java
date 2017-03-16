package com.ws.kislev.controller;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用与根据每个任务的formKey生成每个任务的表单
 * Created by macmini-g1hw on 17/3/16.
 */
@RestController
@RequestMapping("/demo/form")
public class DemoTaskFormController {
    @Resource
    private ProcessEngine processEngine;


    @RequestMapping("/start_form")
    public ModelAndView startFrom(HttpServletRequest request){
        String processDefinitionId = request.getParameter("processDefinitionId");
        ModelAndView mav = new ModelAndView(("demo/start-form"));
        mav.addObject("processDefinitionId",processDefinitionId);

        return  mav;
    }

    @RequestMapping("/deptLeaderApproval_form")
    public ModelAndView deptLeaderApprovalForm(HttpServletRequest request){
        String taskId = request.getParameter("taskId");
        ModelAndView mav = new ModelAndView(("demo/deptLeaderApproval-form"));
        mav.addObject("taskId",taskId);


        TaskService taskService = processEngine.getTaskService();
        Object startDate = taskService.getVariable(taskId, "startDate");
        Object endDate = taskService.getVariable(taskId, "endDate");
        Object reason = taskService.getVariable(taskId, "reason");

        mav.addObject("startDate",startDate);
        mav.addObject("endDate",endDate);
        mav.addObject("reason",reason);


        return  mav;
    }

    @RequestMapping("/hrApproval_form")
    public ModelAndView hrApprovalForm(HttpServletRequest request){
        String taskId = request.getParameter("taskId");
        ModelAndView mav = new ModelAndView(("demo/hrApproval-form"));
        mav.addObject("taskId",taskId);

        TaskService taskService = processEngine.getTaskService();
        Object startDate = taskService.getVariable(taskId, "startDate");
        Object endDate = taskService.getVariable(taskId, "endDate");
        Object reason = taskService.getVariable(taskId, "reason");

        mav.addObject("startDate",startDate);
        mav.addObject("endDate",endDate);
        mav.addObject("reason",reason);

        return  mav;
    }

    @RequestMapping("/modifyApply_form")
    public ModelAndView modifyApplyForm(HttpServletRequest request){
        String taskId = request.getParameter("taskId");
        ModelAndView mav = new ModelAndView(("demo/modifyApply-form"));
        mav.addObject("taskId",taskId);

        TaskService taskService = processEngine.getTaskService();
        Object startDate = taskService.getVariable(taskId, "startDate");
        Object endDate = taskService.getVariable(taskId, "endDate");
        Object reason = taskService.getVariable(taskId, "reason");

        mav.addObject("startDate",startDate);
        mav.addObject("endDate",endDate);
        mav.addObject("reason",reason);

        return  mav;
    }

    @RequestMapping("/reportBack_form")
    public ModelAndView reportBackForm(HttpServletRequest request){
        String taskId = request.getParameter("taskId");
        ModelAndView mav = new ModelAndView(("demo/reportBack-form"));
        mav.addObject("taskId",taskId);

        return  mav;
    }


}
