package com.example.thrift;

import com.example.thrift.bidiMessageIface.Message;
import com.example.thrift.bidiMessageIface.MessageService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;

public class BidiMessageClient {

    private final String name;

    private final TTransport transport;
    private final TProtocol protocol;

    public BidiMessageClient(String name, String server, int port) {
        this.name = name;
        this.transport = new TSocket(server, port);
        this.protocol = new TBinaryProtocol(transport);

        try {
            transport.open();
            new Thread(new MessageReceiver(protocol, new BidiMessageClientServiceImpl() {
                @Override
                public void sendMessage(Message message) {
                    System.out.println(message.message);
                }
            })).start();
            sendtoServer("client-0");
        } catch(TTransportException e){
            e.printStackTrace();
        } catch(TException e) {
            e.printStackTrace();
        }
    }

    public void sendtoServer(String msg) throws TException {
        MessageService.Client client = new MessageService.Client(protocol);
        System.out.println("sending greeting to server");
        client.sendGreeting(msg);
        System.out.println("sending greeting finished");
    }

    public class MessageReceiver implements Runnable {
        private final MessageService.Processor processor;
        private final TProtocol protocol;

        public MessageReceiver(TProtocol protocol, MessageService.Iface messageService) {
            this.protocol = protocol;
            this.processor = new MessageService.Processor(messageService);
        }

        @Override
        public void run() {
            while(true){
                try {
                    while (processor.process(protocol, protocol) == true) { }
                } catch(TException e) {
                    transport.close();
                    break;
                }
            }
        }
    }
}
