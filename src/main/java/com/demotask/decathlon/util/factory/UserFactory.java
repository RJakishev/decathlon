package com.demotask.decathlon.util.factory;

import com.demotask.decathlon.dto.UserDTO;
import com.demotask.decathlon.model.User;
import com.demotask.decathlon.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserFactory {

    private static final Logger log = LoggerFactory.getLogger(UserFactory.class);

    @Autowired
    private UserService userService;

    public UserFactory(){
    }

    public UserDTO toDto(User user) {

        if (user == null) {
            return null;
        }

        log.debug("Converting User entity to DTO.");

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        if(user.getFirstname() != null) userDTO.setFirstname(user.getFirstname());
        if(user.getLastname() != null) userDTO.setLastname(user.getLastname());

        return userDTO;
    }
      
    public User toEntity(UserDTO userDTO) {

        if (userDTO == null) {
            return null;
        }

        log.debug("Converting UserDTO to user.");

        User user = userService.getById(userDTO.getId());

        if(user == null) user = new User();

        if(userDTO.getFirstname() != null){
            String firstname = userDTO.getFirstname().isBlank() ? null : userDTO.getFirstname();
            user.setFirstname(firstname);
        }
        if(userDTO.getFirstname() != null){
            String lastname = userDTO.getLastname().isBlank() ? null : userDTO.getLastname();
            user.setLastname(lastname);
        }

        return user;
    }

    public List<UserDTO> toDto(List<User> users){

        List<UserDTO> userDTOs = new ArrayList<>();

        for (User user : users) {
            userDTOs.add(toDto(user));
        }

        return userDTOs;
    }
}