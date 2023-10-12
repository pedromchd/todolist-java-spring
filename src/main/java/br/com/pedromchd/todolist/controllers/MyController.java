package br.com.pedromchd.todolist.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MyController {
    
    @GetMapping("/")
    public String home() {
        return "Hello world!";
    }

}
