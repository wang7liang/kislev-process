package com.ws.kislev.service.impl;

import com.ws.kislev.dao.UserMapper;
import com.ws.kislev.model.User;
import com.ws.kislev.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional("readDataSource1")
    @Override
    public List<String> getGroupUsers(String groupName) {
        List<String> usernameList = new ArrayList<>();

        List<User> userList = userMapper.getUserByGroupName(groupName);
        for(User user : userList){
            usernameList.add(user.getUsername());
        }

        User user = new User();
        user.setId(userList.get(userList.size()-1).getId()+1);
        user.setUsername("wang"+user.getId());
        user.setPassword("111111");
        userMapper.add(user);

        int s = 1/0;

        user.setId(userList.get(userList.size()-1).getId()+1);
        user.setUsername("wang"+user.getId());
        user.setPassword("111111");
        userMapper.add(user);

        return usernameList;
    }
}
