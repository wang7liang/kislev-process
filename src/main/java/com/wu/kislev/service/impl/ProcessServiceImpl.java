package com.wu.kislev.service.impl;

import com.wu.kislev.service.ProcessService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by macmini-g1hw on 17/3/7.
 */
@Service
public class ProcessServiceImpl implements ProcessService {
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;

    @Override
    public void startProcessByKey(String key, Map<String,Object> variables) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, variables);

        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.claim(task.getId(),"wangqiliang");
        taskService.complete(task.getId());
    }
}


