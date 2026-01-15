package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/Home")
    public String Home(){
        return "Home";
    }
    @GetMapping("/")
    public String home() {
        return "Home"; // Home.html
    }
}
