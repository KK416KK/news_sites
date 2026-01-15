package com.example.demo.Controller;

import com.example.demo.Entity.Article;
import com.example.demo.Repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("official/Official/{id_a}")
    public String articles(
            @PathVariable Long id_a,
            Model model
    ){
        //IDから記事を探す
        Article article = articleRepository.findById(id_a).orElseThrow(() -> new RuntimeException("記事がありません"));
        model.addAttribute("article",article);

        article.incrementViewCount();
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

        article.incrementViewCount();
        articleRepository.save(article);
        return "Articles";
    }
}
