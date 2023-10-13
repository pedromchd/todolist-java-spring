package br.com.pedromchd.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pedromchd.todolist.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    boolean existsByUsername(String username);

}
