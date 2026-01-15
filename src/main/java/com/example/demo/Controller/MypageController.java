package com.example.demo.Controller;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.ArticleBlock;
import com.example.demo.Entity.ArticleSouce;
import com.example.demo.Entity.BlockType;
import com.example.demo.Repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Mypage")
public class MypageController {
    private final ArticleRepository articleRepository;

    public MypageController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/Mypage")
    public String mypage(){
        return "/Mypage/Mypage";
    }
    @GetMapping("/Posting")
    public String post(){
        return "/Mypage/Posting";
    }

    @PostMapping("/Posting")
    public String posting(
            @RequestParam String title,
            @RequestParam List<String> types,
            @RequestParam List<Integer> orders,
            @RequestParam List<String> contents
    ){
        Article article = new Article();

        if (title == null){
            throw new RuntimeException("タイトルがありません");
        }else{
            article.setTitle(title);
        }
        //article.setType(user.)
        article.setSource(ArticleSouce.PERSONAL);

        //記事を要素に分けて保存
        for (int i = 0; i < types.size(); i++) {
            ArticleBlock block = new ArticleBlock();
            block.setType(BlockType.valueOf(types.get(i)));
            block.setOrderNo(orders.get(i));
            block.setContent(contents.get(i));
            article.addBlock(block);
        }

        articleRepository.save(article);
        return "redirect:/Mypage/Mypage";
    }

}
