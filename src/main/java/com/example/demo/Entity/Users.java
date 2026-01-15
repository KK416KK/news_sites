package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Setter
@Getter
@Entity
public class Users {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String userId;
    private String email;
    private String password;
    private String username;
    //個人:PUBLIC/登録業者:CERTIF/管理者:ADOMIN
    private String role;

}
