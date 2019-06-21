package com.example.thrift;


import com.example.thrift.bidiMessageIface.MessageService;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import javax.annotation.Resource;
import java.net.SocketAddress;
import java.util.Map;


@SpringBootApplication
public class Application  {

    public static void main(String[] args) throws TTransportException {
//        ApplicationContext context = SpringApplication.run(Application.class);
//        server(context);

        client();
    }


    @Bean
    @ConditionalOnMissingBean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    public static void server(ApplicationContext context) throws TTransportException {
        Map<String, TTransport> table1 = (Map<String, TTransport>) context.getBean("helloService02");
        Map<TTransport, SocketAddress> table2 = (Map<TTransport, SocketAddress>) context.getBean("helloService04");

        try {
            BidiMessageServer server = new BidiMessageServer(table1, table2, context);

            server.start(9090);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void client() {
        BidiMessageClient client = new BidiMessageClient("MacPro", "localhost",9090);
    }

}