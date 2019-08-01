package com.example.thrift;

import com.example.thrift.bidiMessageIface.Message;
import com.example.thrift.bidiMessageIface.MessageService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TTransport;

import java.io.*;

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
        try {
            File file = new File("/Users/shiqi/parameter.xml");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(msg.message);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int status = runScirpt();
        new Thread(new sender(trans, status)).start();
    }

    @Override
    public void sendGreeting(String name) throws TException {
        System.out.println("Got msg: " + name);
    }

    @Override
    public void messageCallback(String name, int status) throws TException {

    }

    private class sender implements Runnable {
        private final int status;
        private TTransport trans;

        public sender(TTransport transport, int status) {
            this.trans = transport;
            this.status = status;
        }

        @Override
        public void run() {
            MessageService.Client client = new MessageService.Client(new TBinaryProtocol(trans));

            try {
                client.messageCallback("MacPro", status);
            } catch (TException e) {
                trans.close();
                e.printStackTrace();
            }
        }
    }

    private int runScirpt() {
        String scriptPath, logPath;
        scriptPath = "/Users/shiqi/log_analysis.sh";
        logPath = "";

        Runtime runtime = Runtime.getRuntime();
        String[] cmdarray = {scriptPath, logPath};
        BufferedReader br = null;
        BufferedReader bre = null;

        int exitValue = -1;
        try {
            Process process = runtime.exec(cmdarray);

            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            bre = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            StringBuffer linesBuffer = new StringBuffer();
            StringBuffer errorBuffer = new StringBuffer();
            String line = null;
            String errorline = null;

            while(null != (line = br.readLine())) {
                linesBuffer.append(line).append("\n");
            }

            System.out.println("output: \n" + linesBuffer.toString());

            while(null != (errorline = bre.readLine())) {
                errorBuffer.append(errorline).append('\n');
            }
            System.out.println("error output:\n" + errorBuffer.toString());

            exitValue = process.waitFor();
            if( 0 == exitValue) {
                System.out.println("execute script success.");
            } else {
                System.out.println("execute script failed: exitValue:" + exitValue);
            }

        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("execute script failed\n" + e);
            exitValue = -12;
        } catch(InterruptedException e) {
            e.printStackTrace();
            System.out.println("execute script failed" + e);
        } finally {
            if(null != br) {
                try {
                    br.close();
                    bre = null;
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return exitValue;
    }
}




