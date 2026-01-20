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

    //記事の最初の文章を取得
    public String getSummary(Article article) {
        return article.getBlocks().stream()
                .filter(b -> b.getType() == BlockType.TEXT)
                .map(ArticleBlock::getContent)
                .findFirst()
                .orElse("");
    }

    //表示する記事を取得
    public Page<Article> Getart(ArticleSouce source, Pageable pageable){
        //ソース(enumのタイプ)からデータベースを取得
        return articleRepository.findBySourceOrderByCreatedAtDesc(source,pageable);
    }
    //１か月以内に投稿された記事を取得
    public Page<Article> getPopularLast31Days(Pageable pageable) {
        //３１日以内の記事
        LocalDateTime from = LocalDateTime.now().minusDays(31);
        return articleRepository.findPopularArticles(from, pageable);
    }
}
