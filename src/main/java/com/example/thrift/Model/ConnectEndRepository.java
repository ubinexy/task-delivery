package com.example.thrift.Model;

import org.apache.thrift.transport.TTransport;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Repository
@DependsOn("helloService02")
public class ConnectEndRepository {

    @Resource(name="helloService02")
    private Map<String, TTransport> clients;

    @Resource(name="helloService04")
    private Map<TTransport, SocketAddress> TransAddrTable;

    public List<ConnectEnd> findAll() {
        //System.out.println();

        List<ConnectEnd> all = new ArrayList<>();
        for (Entry<String, TTransport> entry : clients.entrySet()) {
            ConnectEnd connectEnd = new ConnectEnd(entry.getKey(), TransAddrTable.get(entry.getValue()).toString());
            all.add(connectEnd);
        }

        return all;
    }

    public ConnectEnd findByName(String name) {
        if(clients.containsKey(name)) {
            return new ConnectEnd(name, TransAddrTable.get(name).toString());
        } else {
            return null;
        }
    }
}
