package com.example.firstproject.entity; // entity 패키지에 속한 클래스라는 뜻

import jakarta.persistence.*; // @Entity, @Id, @GeneratedValue, @Column 등을 사용하기 위한 import
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 모든 필드를 받는 생성자를 Lombok이 자동으로 만들어줌
@NoArgsConstructor // JPA가 Entity를 만들 때 필요한 기본 생성자를 Lombok이 자동으로 만들어줌
@ToString // 객체 내용을 확인할 수 있는 toString()을 Lombok이 자동으로 만들어줌
@Entity // 이 클래스가 DB 테이블과 연결되는 Entity라는 뜻
public class Member { // DB에 저장될 회원 데이터를 표현하는 클래스
    /*
     * Member Entity의 역할
     * 1. DB에 저장할 회원 데이터를 담는다.
     * 2. id, email, password 필드가 DB 테이블의 열과 연결된다.
     * 3. Repository의 save()를 통해 DB에 저장된다.
     */

    @Id // 이 필드가 테이블의 대표값, 즉 기본키라는 뜻
    @GeneratedValue // id 값을 직접 넣지 않아도 DB가 자동으로 만들어준다는 뜻
    Long id; // 회원 한 명을 구분하는 번호

    @Column // 이 필드를 DB 테이블의 email 열과 연결한다는 뜻
    String email; // 회원 이메일

    @Column // 이 필드를 DB 테이블의 password 열과 연결한다는 뜻
    String password; // 회원 비밀번호
}
