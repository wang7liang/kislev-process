package com.ws.kislev.service.impl;

import com.ws.kislev.dao.UserMapper;
import com.ws.kislev.model.User;
import com.ws.kislev.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini-g1hw on 17/3/7.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    
    @Override
    public List<String> getGroupUsers(String groupName) {
        List<String> usernameList = new ArrayList<>();

        List<User> userList = userMapper.getUserByGroupName(groupName);
        for(User user : userList){
            usernameList.add(user.getUsername());
        }

        return usernameList;
    }
}
