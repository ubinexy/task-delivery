package com.example.thrift.Controller;

import com.example.thrift.Model.ConnectEndRepository;
import com.example.thrift.Model.Task;
import com.example.thrift.Model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    public TaskRepository taskRepository;


    @PostMapping("/fileUpload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

        String fileName = file.getOriginalFilename();

        File currDir = new File(".");
        String absolutePath = currDir.getAbsolutePath();
        String path = absolutePath.substring(0, absolutePath.length() - 1);
        File dest = new File(path + fileName);

        file.transferTo(dest);
        LOGGER.info("上传成功");
        taskRepository.save(new Task(path, fileName, LocalDateTime.now()));
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + fileName + "!");
        return "redirect:/";
    }

    @GetMapping("/abc")
    public ModelAndView upload() {
        return new ModelAndView("fileUploadForm");
    }

    @GetMapping("/fileUpload")
    public String displayForm(Model model) {
        return "fileUploadForm";
    }
}
