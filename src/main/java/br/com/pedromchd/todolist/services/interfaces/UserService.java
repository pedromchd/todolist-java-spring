package br.com.pedromchd.todolist.services.interfaces;

import br.com.pedromchd.todolist.dtos.UserDTO;
import br.com.pedromchd.todolist.models.User;

public interface UserService extends CrudService<User, UserDTO> {
    
}
