package com.ws.kislev.controller;

import com.ws.kislev.service.ProcessService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.lang.String;

/**
 * Created by macmini-g1hw on 17/3/7.
 */
@RestController
public class ProcessController {
    @Resource
    private ProcessService processService;

    @RequestMapping("/process/start")
    public String startProcess(){
        String key = "caseEntrustProcess";
        processService.startProcessByKey(key,null);

        return "开始流程";
    }
}
