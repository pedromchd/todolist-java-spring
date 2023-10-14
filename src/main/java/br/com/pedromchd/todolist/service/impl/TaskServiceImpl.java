package br.com.pedromchd.todolist.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedromchd.todolist.dto.TaskDTO;
import br.com.pedromchd.todolist.model.Task;
import br.com.pedromchd.todolist.model.User;
import br.com.pedromchd.todolist.repository.TaskRepository;
import br.com.pedromchd.todolist.service.TaskService;
import br.com.pedromchd.todolist.service.UserService;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getById(Integer id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public Task save(TaskDTO taskDTO) {
        Task task = new Task();

        User user = userService.getById(taskDTO.user_id());
        task.setUser(user);

        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setStartAt(taskDTO.startAt());
        task.setEndAt(taskDTO.endAt());
        task.setPriority(taskDTO.priority());

        return taskRepository.save(task);
    }

    @Override
    public Task update(Integer id, TaskDTO taskDTO) {
        Task task = getById(id);

        User user = userService.getById(taskDTO.user_id());
        task.setUser(user);

        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setStartAt(taskDTO.startAt());
        task.setEndAt(taskDTO.endAt());
        task.setPriority(taskDTO.priority());

        return taskRepository.save(task);
    }

    @Override
    public boolean delete(Integer id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
