package com.example.demo.Repository;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByArticleOrderByIdDesc(Article article);
}
