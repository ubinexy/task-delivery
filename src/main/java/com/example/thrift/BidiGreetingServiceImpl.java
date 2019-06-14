package com.example.thrift;

import com.example.thrift.bidiMessageIface.Message;
import com.example.thrift.bidiMessageIface.MessageService;

public class BidiGreetingServiceImpl implements MessageService.Iface  {

    @Override
    public void sendGreeting(String name) {
        System.out.println("Got greeting from: " +name);
    }

    @Override
    public void sendMessage(Message name) {
        System.out.println("Got message");
    }
}
