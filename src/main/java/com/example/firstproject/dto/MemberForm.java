package com.example.firstproject.dto; // dto 패키지에 속한 클래스라는 뜻

import com.example.firstproject.entity.Member; // DTO를 Entity로 바꿀 때 필요한 Member 클래스

public class MemberForm { // 회원가입 폼에서 넘어온 데이터를 담는 DTO 클래스
    /*
     * MemberForm의 역할
     * 1. HTML form의 name="email" 값을 email 필드에 받는다.
     * 2. HTML form의 name="password" 값을 password 필드에 받는다.
     * 3. Controller에서 받은 값을 확인할 수 있게 toString()을 제공한다.
     * 4. DB 저장을 위해 Member Entity로 변환한다.
     */

    private String email; // 사용자가 입력한 이메일 값을 저장하는 필드
    private String password; // 사용자가 입력한 비밀번호 값을 저장하는 필드

    public MemberForm(String email, String password) { // Spring이 폼 데이터를 넣어서 MemberForm을 만들 때 사용하는 생성자
        this.email = email; // 전달받은 email 값을 현재 객체의 email 필드에 저장
        this.password = password; // 전달받은 password 값을 현재 객체의 password 필드에 저장
    }

    @Override // 부모 클래스의 toString()을 내가 원하는 출력 형태로 바꾼다는 뜻
    public String toString() { // 객체를 출력했을 때 사람이 읽기 쉬운 문자열로 보여주는 메서드
        return "MemberForm{" + // 출력 문자열 시작
                "email='" + email + '\'' + // email 필드 값을 출력 문자열에 포함
                ", password='" + password + '\'' + // password 필드 값을 출력 문자열에 포함
                '}'; // 출력 문자열 끝
    }

    public Member toEntity() { // DTO를 DB 저장용 Entity로 바꾸는 메서드
        return new Member(null, email, password); // id는 DB가 만들기 때문에 null로 두고 email, password만 전달
    }
}


