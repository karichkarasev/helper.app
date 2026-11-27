package com.sandwich.helper.controller;

import com.sandwich.helper.model.users.UserDao;
import com.sandwich.helper.model.users.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserEntityController {

    @Autowired
    private UserDao userDao;

    @PostMapping("/users/save")
    public UserEntity save(@RequestBody UserEntity userEntity) {
        return userDao.save(userEntity);
    }

    @GetMapping("/users/get-all")
    public List<UserEntity> getAllUsers() {
        return userDao.getAllUsers();
    }

    @GetMapping("/users/get-firstUser")
    public UserEntity getFirstUser() {
        return userDao.getAllUsers().getFirst();
    }
}
