package com.ws.kislev.conf.activiti;

import org.activiti.engine.impl.persistence.entity.UserEntityManager;

/**
 * Created by macmini-g1hw on 17/3/15.
 */
public class WsUserEntityManager extends UserEntityManager {
    @Override
    public Boolean checkPassword(String userId, String password) {
        return super.checkPassword(userId, password);
    }
}
