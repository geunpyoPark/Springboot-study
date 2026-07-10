package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { //폼 데이터를 DTO로 받기
        System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지 확인
        Article article = form.toEntity();
        System.out.println(article.toString());
        Article saved = articleRepository.save(article); //article 엔티티를 저장해 saved 객체에 반환
        System.out.println(saved.toString());
        return "";
    }
}
