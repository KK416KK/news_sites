package com.example.demo.Repository;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.ArticleSouce;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findBySourceOrderByCreatedAtDesc(
            ArticleSouce source,
            Pageable pageable
    );
    //JPQLのためこの書き方
    @Query("""
        SELECT a FROM Article a
        WHERE a.createdAt >= :from
        ORDER BY a.viewCount DESC
    """)
    Page<Article> findPopularArticles(
            @Param("from") LocalDateTime from,
            Pageable pageable
    );
}
