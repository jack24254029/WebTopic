package com.shunminchang.controller;

import com.shunminchang.model.TaskEntity;
import com.shunminchang.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @RequestMapping(value = "/task/addTaskP", method = RequestMethod.POST)
    public String addTaskP(@ModelAttribute("task") TaskEntity taskEntity) {
        return "";
    }
}
