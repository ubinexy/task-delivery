package com.example.thrift;

import com.example.thrift.bidiMessageIface.MessageService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class BidiMessageServer {

    private TServer server;


    public void start(MessageService.Processor processor) throws TTransportException {

        TServerTransport serverTransport = new TServerSocket(9090);

        server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

        System.out.print("Starting to server...");

        server.serve();

        System.out.println("done.");
    }

    private void stop() {
        if(server != null && server.isServing()) {
            System.out.println("Stopping the server");

            server.stop();

            System.out.println("done.");
        }
    }
}
