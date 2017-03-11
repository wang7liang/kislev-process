package com.wu.kislev.service;

import java.util.List;

/**
 * Created by macmini-g1hw on 17/3/7.
 */
public interface UserService {
    /**
     * 根据组名取得组成员userId
     * @param groupName
     * @return
     */
    public List<String> getGroupUsers(String groupName);
}
