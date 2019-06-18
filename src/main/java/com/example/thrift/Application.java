package com.example.thrift;


import com.example.thrift.bidiMessageIface.MessageService;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import javax.annotation.Resource;
import java.util.Map;


@SpringBootApplication
public class Application  {

    public static void main(String[] args) throws TTransportException {
//        ApplicationContext context = SpringApplication.run(Application.class);
//        server(context);

        client();
    }



    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    public static void server(ApplicationContext context) throws TTransportException {
        Map<String, TTransport> clients = (Map<String, TTransport>) context.getBean("helloService02");

        try {

            BidiGreetingServiceImpl handler2 = new BidiGreetingServiceImpl();

            MessageService.Processor processor = new MessageService.Processor<>(handler2);

            BidiMessageServer server = new BidiMessageServer(clients, context);

            server.start(processor);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void client() {
        BidiMessageClient client = new BidiMessageClient("client", "localhost",9090);
    }

}