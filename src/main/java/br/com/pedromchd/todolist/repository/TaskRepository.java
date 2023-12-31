package br.com.pedromchd.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pedromchd.todolist.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
