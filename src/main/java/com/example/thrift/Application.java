package com.example.thrift;


import com.example.thrift.bidiMessageIface.MessageService;
import org.apache.thrift.transport.TTransportException;

public class Application {

    public static void main(String[] args) throws TTransportException {

        BidiGreetingServiceImpl handler2 = new BidiGreetingServiceImpl();

        MessageService.Processor processor = new MessageService.Processor<>(handler2);

//        BidiMessageServer server = new BidiMessageServer();
//        server.start(processor);


        BidiMessageClient client = new BidiMessageClient("client", "localhost",9090);
    }
}