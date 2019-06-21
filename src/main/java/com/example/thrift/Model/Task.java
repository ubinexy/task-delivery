package com.example.thrift.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String path;

    @Column
    private String fileName;

    @Column
    private String clientName;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private LocalDateTime startTime;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private LocalDateTime endTime;

    @Column
    private int status;

    @Transient
    public List<String> clientList;

    public Task() {}

    public Task(String path, String fileName, LocalDateTime create) {
        this.path = path;
        this.fileName = fileName;
        this.status = -11;
        this.startTime = create;
    }

    public long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClientName() {
        return clientName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int statusCode) {
        this.status = statusCode;
    }
}
