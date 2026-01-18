package com.example.demo.Controller;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.ArticleSouce;
import com.example.demo.Repository.ArticleRepository;
import com.example.demo.Service.ArticleListService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.PageFormat;
import java.util.List;

@Controller
@RequestMapping("/official")
public class officialController {
    public officialController(ArticleListService service) {
        this.service = service;
    }
    private final ArticleListService service;

    //公式ニュース一覧
    @GetMapping("/Official")
    public String official(@PageableDefault(size = 30) Pageable pageable,
                           Model model){
        Page<Article> articles = service.Getart(ArticleSouce.OFFICIAL,pageable);
        List<Article> list = articles.getContent();

        //ニュースのあらすじを取得
        //String summary = service.getSummary(articles.)
        model.addAttribute("article", articles);
        model.addAttribute("list", list);
        model.addAttribute("summaries","ニュースあらすじ");
        return "/official/Official";
    }
    //個人投稿のニュース
    @GetMapping("/Personal")
    public String personal(@PageableDefault(size = 30) Pageable pageable,
            Model model){
        Page<Article> articles = service.Getart(ArticleSouce.PERSONAL,pageable);
        List<Article> list = articles.getContent();

        //ニュースのあらすじを取得
        //String summary = service.getSummary(articles.)
        model.addAttribute("article", articles);
        model.addAttribute("list", list);
        model.addAttribute("summaries","ニュースあらすじ");
        return "/official/Personal";
    }

    @GetMapping("/recognize")
    public String recognize(@PageableDefault(size = 30) Pageable pageable,
                            Model model){
        Page<Article> articles = service.Getart(ArticleSouce.RECOGNIZE,pageable);
        List<Article> list = articles.getContent();

        //ニュースのあらすじを取得
        //String summary = service.getSummary(articles.)
        model.addAttribute("article", articles);
        model.addAttribute("list", list);
        model.addAttribute("summaries","ニュースあらすじ");
        return "official/recognize";
    }
    @GetMapping("/vertificate")
    public String vertificate(@PageableDefault(size = 30) Pageable pageable,
                              Model model){
        Page<Article> articles = service.Getart(ArticleSouce.VERTIFICATE,pageable);
        List<Article> list = articles.getContent();

        //ニュースのあらすじを取得
        //String summary = service.getSummary(articles.)
        model.addAttribute("article", articles);
        model.addAttribute("list", list);
        model.addAttribute("summaries","ニュースあらすじ");
        return "official/vertificate";
    }
}
