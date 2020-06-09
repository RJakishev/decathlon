package com.demotask.decathlon.service;

import com.demotask.decathlon.dto.UserDTO;
import com.demotask.decathlon.model.User;
import com.demotask.decathlon.repository.UserRepository;
import com.demotask.decathlon.util.factory.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    public UserService() {
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public UserDTO save(UserDTO userDTO) {
        return userFactory.toDto(save(userFactory.toEntity(userDTO)));
    }

    public User save(User user) {

        User savedUser = userRepository.save(user);

        return savedUser;
    }

    public UserDTO getDTOById(int id) { return userFactory.toDto(getById(id)); }

    public User getById(int id) { return userRepository.findById(id).orElse(null); }

    public List<UserDTO> getAllDTOs() { return userFactory.toDto(getAll()); }

    public List<User> getAll() { return userRepository.findAll(); }
}
