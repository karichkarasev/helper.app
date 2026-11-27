package com.sandwich.helper.controller;

import com.sandwich.helper.model.users.UserDao;
import com.sandwich.helper.model.users.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserEntityController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/users/get-all")
    public List<UserEntity> getAllUsers() {
        return userDao.getAllUsers();
    }
}
