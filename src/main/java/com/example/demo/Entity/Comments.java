package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Comments {
    @Id
    @GeneratedValue
    private Long id;
    //内容
    private String texts;

    @ManyToOne
    private Article article;
    @ManyToOne
    private Users users;
}
