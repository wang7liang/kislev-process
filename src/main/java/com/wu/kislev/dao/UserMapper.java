package com.wu.kislev.dao;

import com.wu.kislev.model.User;

import java.util.List;

/**
 * Created by macmini-g1hw on 17/3/10.
 */
public interface UserMapper {

    List<User> getUserByGroupName(String groupName);
}
