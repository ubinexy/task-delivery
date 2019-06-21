package com.example.thrift.Config;


import org.springframework.beans.factory.FactoryBean;

import org.apache.thrift.transport.TTransport;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ConfigMapFactory3 implements FactoryBean<Map<TTransport, SocketAddress>> {

    @Override
    public Map<TTransport, SocketAddress> getObject() throws Exception {
        Map<TTransport, SocketAddress> configMap = new HashMap<TTransport, SocketAddress>();
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
