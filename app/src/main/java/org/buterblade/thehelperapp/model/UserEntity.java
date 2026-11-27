package org.buterblade.thehelperapp.model;

import java.time.Instant;
import java.util.Date;

public class UserEntity {
    private long id;
    private String full_name;
    private String email;
    private String phone;
    private String password;

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
