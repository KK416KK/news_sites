package com.example.demo.Service;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.ArticleBlock;
import com.example.demo.Entity.ArticleSouce;
import com.example.demo.Entity.BlockType;
import com.example.demo.Repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArticleListService {
    private final ArticleRepository articleRepository;

    public ArticleListService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public String getSummary(Article article) {
        return article.getBlocks().stream()
                .filter(b -> b.getType() == BlockType.TEXT)
                .map(ArticleBlock::getContent)
                .findFirst()
                .orElse("");
    }

    public Page<Article> Getart(ArticleSouce source, Pageable pageable){
        //ソース(enumのタイプ)からデータベースを取得
        return articleRepository.findBySourceOrderByCreatedAtDesc(source,pageable);
    }
    public Page<Article> getPopularLast31Days(Pageable pageable) {
        //３１日以内の記事
        LocalDateTime from = LocalDateTime.now().minusDays(31);
        return articleRepository.findPopularArticles(from, pageable);
    }
}
