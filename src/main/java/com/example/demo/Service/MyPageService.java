package com.example.demo.Service;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.Users;
import com.example.demo.Repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageService {
    public List<Article> article_(Pageable pageable, String id){
        Users user = new Users();
        return user.getArticle();
    }
}
