package com.shunminchang.controller;

import com.shunminchang.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TaskController {
    @Autowired
    TaskRepository taskRepository;
}
