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
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.JobQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
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
import java.lang.String;
import java.util.zip.ZipInputStream;

import static org.activiti.engine.impl.test.JobTestHelper.waitForJobExecutorToProcessAllJobs;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;


/**
 * Created by macmini-g1hw on 17/3/7.
 */
@RestController
public class ProcessController {
//    @Resource
//    private ProcessService processService;
//
//    @RequestMapping("/process/start")
//    public String startProcess(){
//        String key = "caseEntrustProcess";
//        processService.startProcessByKey(key,null);
//
//        return "开始流程";
//    }
//
//
//    @Resource
//    private ProcessEngine processEngine;
//
//    @RequestMapping("/test/process-list")
//    public ModelAndView processList(){
//        ModelAndView mav = new ModelAndView(("process-list"));
//
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
//        mav.addObject("processDefinitionList",list);
//
//        return  mav;
//    }
//
//    @RequestMapping("/test/deploy")
//    public ModelAndView deploy(@RequestParam(value = "file")MultipartFile file){
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        String filename = file.getOriginalFilename();
//        String extension =  filename.substring(filename.lastIndexOf(".")+1) ;
//
//        try {
//            InputStream inputStream = file.getInputStream();
//            DeploymentBuilder deployment = repositoryService.createDeployment();
//            if("zip".equals(extension)||"bar".equals(extension)){
//                ZipInputStream zip = new ZipInputStream(inputStream);
//                deployment.addZipInputStream(zip);
//            }else{
//                deployment.addInputStream(filename,inputStream);
//            }
//            deployment.deploy();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return processList();
//    }
//
//    @RequestMapping("/test/read-resource")
//    public void readResource(@RequestParam("pdid") String processDefinitionId, @RequestParam("resourceName") String resourceName, HttpServletResponse response) throws IOException {
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
//        ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionId(processDefinitionId).singleResult();
//        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
//        byte[] b = new byte[1024];
//        int len = -1;
//        while((len = resourceAsStream.read(b,0,1024))!=-1){
//            response.getOutputStream().write(b,0,len);
//        }
//    }
//
//    @RequestMapping("/test/delete-deployment")
//    public ModelAndView deleteProcessDefinition(@RequestParam("deploymentId") String deploymentId){
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        repositoryService.deleteDeployment(deploymentId,true);
//
//        return processList();
//    }
//
//
//    @RequestMapping("/test/process/leave")
//    public String startLeaveProcess(){
//
//        String currentUserId = "wang7liang";
//        IdentityService identityService = processEngine.getIdentityService();
//        identityService.setAuthenticatedUserId(currentUserId);
//
//        // 启动流程
//        ProcessDefinition leave = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("leave").singleResult();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Map<String,String> variables = new HashMap<>();
//        Calendar ca = Calendar.getInstance();
//        String startDate = sdf.format(ca.getTime());
//        ca.add(Calendar.DAY_OF_MONTH,2);
//        String endDate = sdf.format(ca.getTime());
//        variables.put("startDate",startDate);
//        variables.put("endDate",endDate);
//        variables.put("reason","公休");
//
//        FormService formService = processEngine.getFormService();
//        ProcessInstance processInstance = formService.submitStartFormData(leave.getId(), variables);
//
//
//        // 部门领导审批通过
//        TaskService taskService = processEngine.getTaskService();
//        Task departmentHeadTask = taskService.createTaskQuery().taskCandidateGroup("departmentHead").singleResult();
//        variables = new HashMap<>();
//        variables.put("deptLeaderApprove","true");
//        formService.submitTaskFormData(departmentHeadTask.getId(),variables);
//
//        // 人事审批通过
//        Task personnelTask = taskService.createTaskQuery().taskCandidateGroup("personnel").singleResult();
//        variables = new HashMap<>();
//        variables.put("hrApprove","true");
//        formService.submitTaskFormData(personnelTask.getId(),variables);
//
//        // 销假
//        Task reportBackTask = taskService.createTaskQuery().taskAssignee(currentUserId).singleResult();
//        variables = new HashMap<>();
//        variables.put("reportBackDate",sdf.format(ca.getTime()));
//        formService.submitTaskFormData(reportBackTask.getId(),variables);
//
//        // 验证流程是否已经结束
//        HistoryService historyService = processEngine.getHistoryService();
//
//
//        // 读取历史变量
//        Map<String, Object> historyVariables = packageVariables(processInstance);
//
//        return (String)historyVariables.get("result");
//    }
//
//    private Map<String,Object> packageVariables(ProcessInstance processInstance){
//        Map<String,Object> historyVariables = new HashMap<>();
//        HistoryService historyService = processEngine.getHistoryService();
//        List<HistoricDetail> list = historyService.createHistoricDetailQuery().processInstanceId(processInstance.getId()).list();
//        for(HistoricDetail historicDetail:list){
//            if(historicDetail instanceof HistoricFormProperty){
//                HistoricFormProperty field = (HistoricFormProperty)historicDetail;
//                historyVariables.put(field.getPropertyId(),field.getPropertyValue());
//                System.out.println("form field: taskId="+field.getTaskId()+", "
//                +field.getPropertyId()+" = "+field.getPropertyValue());
//            }else if(historicDetail instanceof HistoricVariableUpdate){
//                HistoricVariableUpdate variable = (HistoricVariableUpdate)historicDetail;
//                historyVariables.put(variable.getVariableName(),variable.getValue());
//                System.out.println("variable: "+variable.getVariableName()+" = "+variable.getVariableName());
//            }
//        }
//        return historyVariables;
//    }
//
//    @RequestMapping("/test/process/{processDefinitionId}")
//    public ModelAndView readStartForm(@PathVariable("processDefinitionId") String processDefinitionId){
//        ModelAndView mav = new ModelAndView(("start-process-form"));
//
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
//        boolean hasStartFormKey = processDefinition.hasStartFormKey();
//
//        FormService formService = processEngine.getFormService();
//        if(hasStartFormKey){
//
//            String startFormKey = formService.getStartFormKey(processDefinitionId);
//            if(startFormKey.endsWith(".action")){
//                mav.setViewName("redirect:/"+startFormKey);
//                return mav;
//            }else{
//                Object renderedStartForm = formService.getRenderedStartForm(processDefinitionId);
//                mav.addObject("startFormDate",renderedStartForm);
//                mav.addObject("processDefinition",processDefinition);
//            }
//
//        }else {
//            StartFormData startFormData = formService.getStartFormData(processDefinitionId);
//            mav.addObject("startFormDate",startFormData);
//        }
//
//        mav.addObject("hasStartFormKey",hasStartFormKey);
//        mav.addObject("processDefinitionId",processDefinitionId);
//
//        return mav;
//    }
//
//    @RequestMapping("/test/process-instance/start/{processDefinitionId}")
//    public ModelAndView startProcessInstance(@PathVariable("processDefinitionId") String processDefinitionId, HttpServletRequest request){
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
//        boolean hasStartFormKey = processDefinition.hasStartFormKey();
//
//        FormService formService = processEngine.getFormService();
//        Map<String,String> formValues = new HashMap<>();
//
//        if(hasStartFormKey){
//            Map<String, String[]> parameterMap = request.getParameterMap();
//            Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
//            for(Map.Entry<String,String[]> entry:entries){
//                String key = entry.getKey();
//                formValues.put(key,entry.getValue()[0]);
//            }
//        }else{
//            StartFormData startFormData = formService.getStartFormData(processDefinitionId);
//            List<FormProperty> formProperties = startFormData.getFormProperties();
//
//
//            for(FormProperty formProperty:formProperties){
//                String value = request.getParameter(formProperty.getId());
//                formValues.put(formProperty.getId(),value);
//            }
//        }
//
//        IdentityService identityService = processEngine.getIdentityService();
//        identityService.setAuthenticatedUserId("wangqiliang");
//
//        formService.submitStartFormData(processDefinitionId,formValues);
//
//        return processList();
//    }
//
//    @RequestMapping("/test/process/test")
//    public ModelAndView test(){
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//
//        DeploymentBuilder deployment = repositoryService.createDeployment();
//        deployment.addClasspathResource("processes/leave.bpmn20.xml");
//        deployment.addClasspathResource("processes/leave-start.form");
//        deployment.deploy();
//
//        ProcessDefinition leave1 = repositoryService.createProcessDefinitionQuery().processDefinitionKey("leave").latestVersion().singleResult();
//
//
//        FormService formService = processEngine.getFormService();
//        Object leave = formService.getRenderedStartForm(leave1.getId());
//        System.out.println(leave.toString());
//        String startFormKey = formService.getStartFormKey(leave1.getId());
//        System.out.println(startFormKey);
//
//
//        return processList();
//    }
//
//    @RequestMapping("/test/form/test.action")
//    public String test02(){
//        return "aaaaaccccccdddddd";
//    }
//
//
//    @RequestMapping("/test/event/test03")
//    public void test03(){
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//
//        ExecutionQuery executionQuery = runtimeService.createExecutionQuery();
//        Execution execution = executionQuery.messageEventSubscriptionName("测试消息").singleResult();
//
//        runtimeService.messageEventReceived("测试消息",execution.getId());
//    }


}
































