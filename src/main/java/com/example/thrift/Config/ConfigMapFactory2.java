package com.example.thrift.Config;


import org.springframework.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigMapFactory2 implements FactoryBean<Map<String, List<Long>>> {
    @Override
    public Map<String, List<Long>> getObject() throws Exception {
        Map<String,List<Long>> configMap = new HashMap<String, List<Long>>();
        System.out.println("map inited" + configMap.size());
        return configMap;
    }

    @Override
    public Class<?> getObjectType() {
        return Map.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
