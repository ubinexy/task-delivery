package com.example.thrift.Config;

import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

public class ConfigMapFactory implements FactoryBean<Map<String, TTransport>> {

    @Override
    public Map<String, TTransport> getObject() throws Exception {
        Map<String,TTransport> configMap = new HashMap<String, TTransport>();
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
