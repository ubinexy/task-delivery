package com.example.thrift;

import com.example.thrift.bidiMessageIface.Message;
import com.example.thrift.bidiMessageIface.MessageService;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;

import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;


import java.util.Map;


public class BidiMessageServer {

    private TServer server;

    private Map<String, TTransport> clients;

    public BidiMessageServer(Map<String, TTransport> clients) {
        this.clients = clients;
    }

    public void start(MessageService.Processor processor) throws TTransportException {

        TServerTransport serverTransport = new TServerSocket(9090);
        TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);

        TProcessorFactory processorFactory = new TProcessorFactory(null) {
            @Override
            public TProcessor getProcessor(TTransport trans) {

                TProcessor p = new MessageService.Processor(new MessageService.Iface() {
                    @Override
                    public void sendGreeting(String name) {
                        System.out.println("~~~~" + name + clients.size());
                        clients.put(name, trans);
                    }

                    @Override
                    public void sendMessage(Message message) {
                        System.out.println("Got message");
                    }
                });
                return p;
            }
        };
        TServer server = new TThreadPoolServer(args.processorFactory(processorFactory));

        System.out.print("Starting to server...");

        server.serve();

        System.out.println("done.");
    }

    public void pushToClient(String clientName) {
        TTransport trans = clients.get(clientName);
        MessageService.Client client = new MessageService.Client(new TBinaryProtocol(trans));
        try {
            client.sendMessage(new Message("server","hi, " + clientName));
        } catch (TException e) {
            trans.close();
            clients.remove(clientName);
            e.printStackTrace();
        }
    }


    private void stop() {
        if(server != null && server.isServing()) {
            System.out.println("Stopping the server");

            server.stop();

            System.out.println("done.");
        }
    }
}
