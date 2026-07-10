package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j //로깅 기능을 위한 어노테이션 추가
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
        log.info("form = {}", form); // DTO에 폼 데이터가 잘 담겼는지 로그로 확인
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info("article = {}", article);

        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article); //article 엔티티를 저장해 saved 객체에 반환
        log.info("saved = {}", saved);
        return "";
    }
}
