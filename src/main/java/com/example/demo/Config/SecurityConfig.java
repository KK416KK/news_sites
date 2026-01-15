package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //ログインせずに見られる
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/Home", "/official/**","/css/**","/Userconfig/**","/Articles").permitAll()
                        .anyRequest().authenticated()
                )
                //ログイン画面
                .formLogin(form -> form
                        .loginPage("/Userconfig/Login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/Userconfig/Login?error")
                        .permitAll()
                )
                //ログアウト後
                .logout(logout -> logout
                        .logoutSuccessUrl("/Userconfig/Login")
                );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
