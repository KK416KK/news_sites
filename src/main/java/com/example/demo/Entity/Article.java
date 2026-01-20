package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Article {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private int viewCount;

    @Enumerated(EnumType.STRING)
    private ArticleSouce source;

    //記事ブロックとのリレーション
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderNo ASC")
    private List<ArticleBlock> blocks = new ArrayList<>();

    //コメント
    @OneToMany(mappedBy = "article")
    private List<Comments> comments;

    @ManyToOne
    private Users users;

    //記事にタグなど

    public void addBlock(ArticleBlock block) {
        blocks.add(block);
        block.setArticle(this);
    }
    public void incrementViewCount() {
        this.viewCount++;
    }

    @Transient
    public String summary;
}
