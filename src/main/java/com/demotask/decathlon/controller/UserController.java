package com.demotask.decathlon.controller;

import com.demotask.decathlon.dto.UserDTO;
import com.demotask.decathlon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(){
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return save(userDTO);
    }

    @PutMapping()
    public UserDTO update(@RequestBody UserDTO userDTO) {
        return save(userDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id){
        userService.delete(id);
    }

    @GetMapping("{id}")
    public UserDTO getById(@PathVariable int id) {
        return userService.getDTOById(id);
    }

    @GetMapping()
    public List<UserDTO> getAll() {
        return userService.getAllDTOs();
    }

    private UserDTO save(UserDTO userDTO) {
        return userService.save(userDTO);
    }
}