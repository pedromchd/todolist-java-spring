package br.com.pedromchd.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedromchd.todolist.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
