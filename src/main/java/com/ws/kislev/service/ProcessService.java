package com.wu.kislev.service;

import java.util.Map;

/**
 * Created by macmini-g1hw on 17/3/7.
 */
public interface ProcessService {
    public void startProcessByKey(String key, Map<String, Object> variables);
}
