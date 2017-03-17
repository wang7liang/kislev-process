package com.ws.kislev.conf.activiti;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
import org.springframework.context.annotation.Configuration;

/**
 * Created by macmini-g1hw on 17/3/15.
 */
public class WsUserEntityManagerFactory implements SessionFactory {
    private WsUserEntityManager wsUserEntityManager;

    @Override
    public Class<?> getSessionType() {
        return UserIdentityManager.class;
    }

    @Override
    public Session openSession() {
        return wsUserEntityManager;
    }
}
