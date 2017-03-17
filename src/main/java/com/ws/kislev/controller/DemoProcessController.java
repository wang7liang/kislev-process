package com.ws.kislev.controller;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;


/**
 * Created by macmini-g1hw on 17/3/7.
 */
@RestController
@RequestMapping("/demo/process")
public class DemoProcessController {
    @Resource
    private ProcessEngine processEngine;

    /**
     * 流程列表
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView processList(){
        ModelAndView mav = new ModelAndView(("demo/process-list"));

        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        mav.addObject("processDefinitionList",list);

        return  mav;
    }

    /**
     * 流程部署
     * @param file
     * @return
     */
    @RequestMapping("/deploy")
    public ModelAndView deploy(@RequestParam(value = "file")MultipartFile file){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String filename = file.getOriginalFilename();
        String extension =  filename.substring(filename.lastIndexOf(".")+1) ;

        try {
            InputStream inputStream = file.getInputStream();
            DeploymentBuilder deployment = repositoryService.createDeployment();
            if("zip".equals(extension)||"bar".equals(extension)){
                ZipInputStream zip = new ZipInputStream(inputStream);
                deployment.addZipInputStream(zip);
            }else{
                deployment.addInputStream(filename,inputStream);
            }
            deployment.deploy();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return processList();
    }

    /**
     * 读取资源
     * @param processDefinitionId
     * @param resourceName
     * @param response
     * @throws IOException
     */
    @RequestMapping("/read-resource")
    public void readResource(@RequestParam("pdid") String processDefinitionId, @RequestParam("resourceName") String resourceName, HttpServletResponse response) throws IOException {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionId(processDefinitionId).singleResult();
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        byte[] b = new byte[1024];
        int len = -1;
        while((len = resourceAsStream.read(b,0,1024))!=-1){
            response.getOutputStream().write(b,0,len);
        }
    }

    /**
     * 删除部署
     * @param deploymentId
     * @return
     */
    @RequestMapping("/delete-deployment")
    public ModelAndView deleteProcessDefinition(@RequestParam("deploymentId") String deploymentId){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment(deploymentId,true);

        return processList();
    }


    /**
     * 读取开始表单
     * @param processDefinitionId
     * @return
     */
    @RequestMapping("/{processDefinitionId}")
    public ModelAndView readStartForm(@PathVariable("processDefinitionId") String processDefinitionId){
        ModelAndView mav = new ModelAndView(("demo/commonStart-form"));

        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        boolean hasStartFormKey = processDefinition.hasStartFormKey();

        FormService formService = processEngine.getFormService();
        if(hasStartFormKey){
            String startFormKey = formService.getStartFormKey(processDefinitionId);
            if(startFormKey.endsWith(".action")){
                mav.addObject("processDefinitionId",processDefinitionId);
                mav.setViewName("redirect:/"+startFormKey+"?processDefinitionId="+processDefinitionId);
                return mav;
            }else{
                Object renderedStartForm = formService.getRenderedStartForm(processDefinitionId);
                mav.addObject("startFormDate",renderedStartForm);
                mav.addObject("processDefinition",processDefinition);
            }

        }else {
            StartFormData startFormData = formService.getStartFormData(processDefinitionId);
            mav.addObject("startFormDate",startFormData);
        }

        mav.addObject("hasStartFormKey",hasStartFormKey);
        mav.addObject("processDefinitionId",processDefinitionId);

        return mav;
    }


    @RequestMapping("/start/{id}")
    public ModelAndView startProcessInstance(@PathVariable("id") String processDefinitionId, HttpServletRequest request){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        boolean hasStartFormKey = processDefinition.hasStartFormKey();

        FormService formService = processEngine.getFormService();
        Map<String,String> formValues = new HashMap<>();

        if(hasStartFormKey){
            Map<String, String[]> parameterMap = request.getParameterMap();
            Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
            for(Map.Entry<String,String[]> entry:entries){
                String key = entry.getKey();
                formValues.put(key,entry.getValue()[0]);
            }
        }else{
            StartFormData startFormData = formService.getStartFormData(processDefinitionId);
            List<FormProperty> formProperties = startFormData.getFormProperties();


            for(FormProperty formProperty:formProperties){
                String value = request.getParameter(formProperty.getId());
                formValues.put(formProperty.getId(),value);
            }
        }

        IdentityService identityService = processEngine.getIdentityService();
        identityService.setAuthenticatedUserId("wangqiliang");

        formService.submitStartFormData(processDefinitionId,formValues);

        return processList();
    }




}
































