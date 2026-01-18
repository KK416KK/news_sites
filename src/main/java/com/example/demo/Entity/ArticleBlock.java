package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ArticleBlock {
    @Id
    @GeneratedValue
    private Long id;

    //リレーション
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Enumerated(EnumType.STRING)
    private BlockType type;

    //記事ブロックの順番
    private int orderNo;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String url;

}
