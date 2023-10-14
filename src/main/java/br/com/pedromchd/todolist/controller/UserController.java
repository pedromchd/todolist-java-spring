package br.com.pedromchd.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedromchd.todolist.dto.UserDTO;
import br.com.pedromchd.todolist.model.User;
import br.com.pedromchd.todolist.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody UserDTO userDTO) {
        User user = userService.save(userDTO);
        if (user == null) {
            return new ResponseEntity<String>("Usuário informado já existe", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<String>("Usuário criado com sucesso", HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        User user = userService.update(id, userDTO);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        Boolean deleted = userService.delete(id);
        if (deleted) {
            return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(deleted, HttpStatus.NOT_FOUND);
        }
    }

}