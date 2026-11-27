package com.sandwich.helper;

import com.sandwich.helper.model.users.UserDao;
import com.sandwich.helper.model.users.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;

@SpringBootTest
class HelperApplicationTests {

    @Autowired
    UserDao userDao;

	@Test
	void addUser() {
        UserEntity user = new UserEntity();
        user.setFullName("Damir Ermukhanov");
        user.setEmail("example@email.com");
        user.setPhone("+77777777777");
        user.setPassword("adminadmin");
        user.setDate(Date.from(Instant.now()));
        userDao.save(user);
    }

    @Test
    void getAllUsers() {
        for(UserEntity user : userDao.getAllUsers()) {
            System.out.println(user);
        }
    }

}
