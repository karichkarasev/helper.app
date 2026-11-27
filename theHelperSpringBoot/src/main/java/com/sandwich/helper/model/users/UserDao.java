package com.sandwich.helper.model.users;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDao {

    @Autowired
    private UserEntityRepository userEntityRepository;

    public void save(UserEntity userEntity) {
        userEntityRepository.save(userEntity);
    }

    public List<UserEntity> getAllUsers() {
        List<UserEntity> userEntities = new ArrayList<>();
        Streamable.of(userEntityRepository.findAll())
                .forEach(userEntities::add);
        return userEntities;
    }

    public void delete(UserEntity userEntity) {
        userEntityRepository.delete(userEntity);
    }
}
