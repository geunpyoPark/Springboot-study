package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor //기본 생성자 추가 어노테이션
@ToString
@Entity //엔티티 선언
public class Article {

    @Id //엔티티의 대푯값 지정
    @GeneratedValue //자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private Long id;
    @Column // title 필드 선언, DB 테이블의 title열과 연결됨
    private String title;
    @Column     // contetn 필드 선언, DB 테이블의 content 열과 연결됨
    private String content;
}
