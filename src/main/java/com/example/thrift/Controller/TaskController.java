package com.example.thrift.Controller;

import com.example.thrift.BidiMessageServer;
import com.example.thrift.Model.ConnectEndRepository;
import com.example.thrift.Model.Task;
import com.example.thrift.Model.TaskRepository;
import com.example.thrift.bidiMessageIface.Message;
import com.example.thrift.bidiMessageIface.MessageService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Controller
public class TaskController {

    @Resource(name="helloService02")
    private Map<String, TTransport> clients;

    @Resource(name="helloService03")
    private Map<String, Long> clientTask;

    @Resource(name="helloService04")
    private Map<TTransport, SocketAddress> transAddr;

    @Autowired
    public TaskRepository taskRepository;

    @RequestMapping("/task")
    public String showAll(Model model) {
        Iterable<Task> all = taskRepository.findAll();
        model.addAttribute("tasks", all);
        return "task";
    }


    @RequestMapping("/task/{id}/{client}")
    public String readPOI(@PathVariable("id") long id, @PathVariable("client") String clientName, Model model) {

        Optional<Task> task = taskRepository.findById(id);
        String content;
        try {
            content= new String ( Files.readAllBytes(Paths.get(task.get().getFileName())) );
        } catch(Exception e) {
            e.printStackTrace();
            return "redirect:/";
        }

//        String content = "hello world";

        TTransport trans = clients.get(clientName);
        MessageService.Client client = new MessageService.Client(new TBinaryProtocol(trans));

        try {
            client.sendMessage(new Message("server", content));
            task.get().setStatus(1);
            task.get().setClientName(clientName);
            clientTask.put(clientName, task.get().getId());
            taskRepository.save(task.get());
        } catch (TException e) {
            trans.close();
            clients.remove(clientName);
            e.printStackTrace();
        }
        return "redirect:/";
    }


    public void consumeTask(String clientName, int status) {
        Optional<Task> task = taskRepository.findById(clientTask.get(clientName));

        task.get().setStatus(status);
        task.get().setEndTime(LocalDateTime.now());
        task.get().setClientName(clientName);
        clientTask.remove(clientName);
        taskRepository.save(task.get());

    }
}
