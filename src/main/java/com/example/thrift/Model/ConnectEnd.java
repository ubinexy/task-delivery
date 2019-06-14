package com.example.thrift.Model;

import org.apache.thrift.transport.TTransport;
import org.springframework.stereotype.Component;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


@Entity
public class ConnectEnd {

    @Id
    private String name;

    public ConnectEnd() {}

    public ConnectEnd(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
