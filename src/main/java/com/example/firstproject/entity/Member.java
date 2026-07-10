package com.example.firstproject.entity; // entity 패키지에 속한 클래스라는 뜻

import jakarta.persistence.*; // @Entity, @Id, @GeneratedValue, @Column 등을 사용하기 위한 import

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

    public Member(Long id, String email, String password) { // Member 객체를 만들 때 id, email, password를 받는 생성자
        this.id = id; // 전달받은 id 값을 현재 객체의 id 필드에 저장
        this.email = email; // 전달받은 email 값을 현재 객체의 email 필드에 저장
        this.password = password; // 전달받은 password 값을 현재 객체의 password 필드에 저장
    }

    @Override // 부모 클래스의 toString()을 내가 원하는 출력 형태로 바꾼다는 뜻
    public String toString() { // 객체를 출력했을 때 필드 값을 확인하기 위한 메서드
        return "Member{" + // 출력 문자열 시작
                "id=" + id + // id 필드 값을 출력 문자열에 포함
                ", email='" + email + '\'' + // email 필드 값을 출력 문자열에 포함
                ", password='" + password + '\'' + // password 필드 값을 출력 문자열에 포함
                '}'; // 출력 문자열 끝
    }
}
