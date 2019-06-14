package com.example.thrift;

import com.example.thrift.bidiMessageIface.Message;
import com.example.thrift.bidiMessageIface.MessageService;
import org.apache.thrift.TException;

public class BidiMessageClientServiceImpl implements MessageService.Iface {

    @Override
    public void sendMessage(Message msg) throws TException {
        System.out.println("Got msg: " + msg.clientName);
    }

    @Override
    public void sendGreeting(String name) throws TException {
        System.out.println("Got msg: " + name);
    }

}




