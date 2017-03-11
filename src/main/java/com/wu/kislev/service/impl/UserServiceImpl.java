package com.wu.kislev.service.impl;

import com.wu.kislev.dao.UserMapper;
import com.wu.kislev.model.User;
import com.wu.kislev.service.UserService;
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
