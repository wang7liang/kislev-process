package com.ws.kislev.dao;

import com.ws.kislev.model.User;

import java.util.List;

/**
 * Created by macmini-g1hw on 17/3/10.
 */
public interface UserMapper {

    public List<User> getUserByGroupName(String groupName);

    public void add(User user);
}
