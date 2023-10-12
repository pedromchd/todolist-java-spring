package br.com.pedromchd.todolist.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedromchd.todolist.models.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/")
    public void create(@RequestBody User user) {
        System.out.println(user.fullname);
    }

}
