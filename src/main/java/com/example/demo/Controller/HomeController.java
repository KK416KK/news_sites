package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/Home")
    public String Home(){
        //ホーム画面に人気の記事などを表示
        return "Home";
    }
    @GetMapping("/")
    public String home() {
        //ホーム画面に人気の記事などを表示
        return "Home"; // Home.html
    }
}
