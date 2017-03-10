package com.wu.kislev.service.impl;

import com.wu.kislev.service.GroupService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini-g1hw on 17/3/7.
 */
@Service("groupService")
public class GroupServiceImpl implements GroupService {

    @Override
    public List<String> getGroupUsers(String groupName) {
        List<String> userList = new ArrayList<>();

        if("testGroup".equals(groupName)){
            // 通过查询数据库或者调用其他服务接口获取组中的用户userId
            userList.add("wang1liang");
            userList.add("wang2liang");
            userList.add("wang3liang");

        }else{
            userList.add("ssssssss");
        }

        return userList;
    }
}
