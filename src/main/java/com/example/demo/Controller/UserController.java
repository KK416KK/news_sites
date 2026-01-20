package com.example.demo.Controller;

import com.example.demo.Service.ArticleService;
import com.example.demo.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Userconfig")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService, ArticleService articleService) {
        this.userService = userService;
    }

    @GetMapping("/NewUser")
    public String users(){
        return "/Userconfig/NewUser";
    }
    @GetMapping("/Login")
    public String Logins(){
        return "/Userconfig/Login";
    }
    @PostMapping("/NewUser")
    public String user_conf(@RequestParam String id,
                            @RequestParam String password,
                            @RequestParam String name,
                            @RequestParam String email,
                            Model model){
        userService.user_register(id,password,email,name);

        return "redirect: Userconfig/Login";
    }
}
