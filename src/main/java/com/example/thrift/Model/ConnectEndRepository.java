package com.example.thrift.Model;

import org.apache.thrift.transport.TTransport;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@DependsOn("helloService02")
public class ConnectEndRepository {

    @Resource(name="helloService02")
    private Map<String, TTransport> clients;

    public List<ConnectEnd> findAll() {
        System.out.println();
        List<ConnectEnd> all = clients.keySet().stream().map(ConnectEnd::new).collect(Collectors.toList());

        return all;
    }

    public ConnectEnd findByName(String name) {
        if(clients.containsKey(name)) {
            return new ConnectEnd(name);
        } else {
            return null;
        }
    }
}

