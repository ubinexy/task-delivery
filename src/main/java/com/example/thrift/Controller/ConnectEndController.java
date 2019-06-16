package com.example.thrift.Controller;
//
//
import com.example.thrift.Model.ConnectEnd;
import com.example.thrift.Model.ConnectEndRepository;
import com.example.thrift.Model.TaskRepository;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConnectEndController {

    @Autowired
    private ConnectEndRepository connectEndRepository;

    @Autowired
    public TaskRepository taskRepository;

    @GetMapping("/")
    public String show(Model model) {
        model.addAttribute("users", connectEndRepository.findAll());
        model.addAttribute("tasks", taskRepository.findAll());
        return "index";
    }


//
////    @GetMapping("/signup")
////    public String showSignUpForm(ConnectEnd user) {
////        return "add-user";
////    }
//
////    @PostMapping("/adduser")
////    public String addConnectEnd(@Valid ConnectEnd user, BindingResult result, Model model) {
////        if (result.hasErrors()) {
////            return "add-user";
////        }
////
////        connectEndRepository.save(user);
////        model.addAttribute("users", connectEndRepository.findAll());
////        return "index";
////    }
//
////    @GetMapping("/edit/{id}")
////    public String showUpdateForm(@PathVariable("id") long id, Model model) {
////        ConnectEnd user = connectEndRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
////        model.addAttribute("user", user);
////        return "update-user";
////    }
//
////    @PostMapping("/update/{id}")
////    public String updateConnectEnd(@PathVariable("id") long id, @Valid ConnectEnd user, BindingResult result, Model model) {
////        if (result.hasErrors()) {
////            user.setId(id);
////            return "update-user";
////        }
////
////        connectEndRepository.save(user);
////        model.addAttribute("users", connectEndRepository.findAll());
////        return "index";
////    }
//
////    @GetMapping("/delete/{id}")
////    public String deleteConnectEnd(@PathVariable("id") long id, Model model) {
////        ConnectEnd user = connectEndRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
////        connectEndRepository.delete(user);
////        model.addAttribute("users", connectEndRepository.findAll());
////        return "index";
////    }
}

