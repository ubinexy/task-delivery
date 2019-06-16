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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Controller
public class TaskController {

    @Resource(name="helloService02")
    private Map<String, TTransport> clients;

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

//        taskRepository.findById(id);
//
//        String content = new String ( Files.readAllBytes(Paths.get(filePath)));
//
        String content = "hello world";

        TTransport trans = clients.get(clientName);
        MessageService.Client client = new MessageService.Client(new TBinaryProtocol(trans));

        try {
            client.sendMessage(new Message("server", content));
        } catch (TException e) {
            trans.close();
            clients.remove(clientName);
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
