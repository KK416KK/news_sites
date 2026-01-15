package com.example.demo.Service;

import com.example.demo.Entity.Users;
import com.example.demo.Repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository a, PasswordEncoder passwordEncoder){
        this.userRepository = a;
        this.passwordEncoder = passwordEncoder;
    }
    public Users user_register(String id, String pass,String email, String name){
        if(userRepository.findByUserId(id).isPresent()){
            throw new RuntimeException("そのIDはすでに存在します");
        }
        Users users = new Users();
        users.setUserId(id);
        users.setPassword(passwordEncoder.encode(pass));
        users.setEmail(email);
        users.setUsername(name);
        users.setRole("PUBLIC");

        return userRepository.save(users);
    }

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException{
        Users user = userRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("not found"));

        return User.builder()
                .username(user.getUserId())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
