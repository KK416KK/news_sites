package com.example.demo.Controller;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.Comments;
import com.example.demo.Repository.ArticleRepository;
import com.example.demo.Service.ArticleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    public ArticleController(ArticleRepository articleRepository, ArticleService articleService) {
        this.articleRepository = articleRepository;
        this.articleService = articleService;
    }

    @GetMapping("official/Official/{id_a}")
    public String articles(
            @PathVariable Long id_a,
            Model model
    ){
        //IDから記事を探す
        Article article = articleRepository.findById(id_a).orElseThrow(() -> new RuntimeException("記事がありません"));
        model.addAttribute("article",article);

        //viewcountを増やす　bot等対策予定
        article.incrementViewCount();
        articleRepository.save(article);
        return "Articles";
    }
    @GetMapping("official/Personal/{id_a}")
    public String articles_personal(
            @PathVariable Long id_a,
            Model model
    ){
        //IDから記事を探す
        Article article = articleRepository.findById(id_a).orElseThrow(() -> new RuntimeException("記事がありません"));
        model.addAttribute("article",article);

        //viewcountを増やす　bot等対策予定
        article.incrementViewCount();
        articleRepository.save(article);
        return "Articles";
    }
    @GetMapping("article/{id_a}")
    public String article(
            @PathVariable Long id_a,
            HttpSession session,
            Model model
    ){
        //IDから記事を探す
        Article article = articleRepository.findById(id_a).orElseThrow(() -> new RuntimeException("記事がありません"));
        model.addAttribute("article",article);
        session.setAttribute("ID",id_a);

        List<Comments> comments = articleService.coo(article);
        model.addAttribute("Comments",comments);

        //viewcountを増やす　bot等対策予定
        article.incrementViewCount();
        articleRepository.save(article);
        return "Articles";
    }
    @PostMapping("/article")
    public String art(
            @RequestParam String comments,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpSession session,
            Model model
    ){
        //ユーザーID
        String loginId = userDetails.getUsername();
        //記事id
        Long artId = (Long) session.getAttribute("ID");

        articleService.comments(comments,loginId,artId);
        return "redirect: /Articles";
    }
}
