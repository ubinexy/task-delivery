package com.example.thrift;

import com.example.thrift.bidiMessageIface.Message;
import com.example.thrift.bidiMessageIface.MessageService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TTransport;

public class BidiMessageClientServiceImpl implements MessageService.Iface {

    private TTransport trans;
    String name;

    public BidiMessageClientServiceImpl(String name, TTransport transport) {
        this.name = name;
        this.trans = transport;
    }

    @Override
    public void sendMessage(Message msg) throws TException {
        // receive from Server
        System.out.println("Got msg: " + msg.clientName);
        new Thread(new sender(trans)).start();
    }

    @Override
    public void sendGreeting(String name) throws TException {
        System.out.println("Got msg: " + name);
    }

    @Override
    public void messageCallback(String name, int status) throws TException {

    }

    public class sender implements Runnable {

        private TTransport trans;

        public sender(TTransport transport) {
            this.trans = transport;
        }

        @Override
        public void run() {
            MessageService.Client client = new MessageService.Client(new TBinaryProtocol(trans));

            try {
                client.messageCallback(name, 0);
            } catch (TException e) {
                trans.close();
                e.printStackTrace();
            }
        }
    }
}




