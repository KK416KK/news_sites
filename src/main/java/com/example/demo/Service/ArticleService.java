package com.example.demo.Service;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.Comments;
import com.example.demo.Entity.Users;
import com.example.demo.Repository.ArticleRepository;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final CommentRepository repository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public ArticleService(CommentRepository repository, UserRepository userRepository, ArticleRepository articleRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    public Comments comments(String comment,String userid,Long articleId){
        Comments comments = new Comments();
        comments.setTexts(comment);

        Users user = userRepository.findByUserId(userid).orElseThrow();
        comments.setUsers(user);

        Article article = articleRepository.findById(articleId).orElseThrow();
        comments.setArticle(article);

        return repository.save(comments);
    }
    public List<Comments> coo(Article article){
        return repository.findByArticleOrderByIdDesc(article);
    }
}
