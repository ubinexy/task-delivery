package com.example.thrift.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class ApplicationConfig {
    @Bean
    public ConfigMapFactory helloService02(){
        System.out.println("配置类@Bean给容器中添加组件1了...");
        return new ConfigMapFactory();
    }

    @Bean
    public ConfigMapFactory2 helloService03(){
        System.out.println("配置类@Bean给容器中添加组件2了...");
        return new ConfigMapFactory2();
    }

    @Bean
    public ConfigMapFactory3 helloService04(){
        System.out.println("配置类@Bean给容器中添加组件3了...");
        return new ConfigMapFactory3();
    }
}
