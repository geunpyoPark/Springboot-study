package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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
//PathVariable: URL요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져오는 어노테이션!
    @GetMapping("/articles/{id}") //데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model){ //매개변수로 id 받아 오기
        log.info("id = {}", id);
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }
}
