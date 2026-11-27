package com.sandwich.helper.model.users;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String full_name;
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    @Column(nullable = false)
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
