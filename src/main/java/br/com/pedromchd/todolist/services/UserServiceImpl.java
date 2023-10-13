package br.com.pedromchd.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.com.pedromchd.todolist.dtos.UserDTO;
import br.com.pedromchd.todolist.models.User;
import br.com.pedromchd.todolist.repositories.UserRepository;
import br.com.pedromchd.todolist.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User save(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.username())) {
            return null;
        }

        User user = new User();
        user.setUsername(userDTO.username());
        user.setFullname(userDTO.fullname());

        String hash = hashPassword(userDTO.password());
        user.setPassword(hash);

        return userRepository.save(user);
    }

    @Override
    public User update(Integer id, UserDTO userDTO) {
        User user = getById(id);
        user.setUsername(userDTO.username());
        user.setFullname(userDTO.fullname());

        String hash = hashPassword(userDTO.password());
        user.setPassword(hash);

        return userRepository.save(user);
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt(12);
        String hash = BCrypt.hashpw(password, salt);
        return hash;
    }

    @Override
    public boolean delete(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
