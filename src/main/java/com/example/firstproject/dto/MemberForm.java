package com.example.firstproject.dto; // dto 패키지에 속한 클래스라는 뜻

import com.example.firstproject.entity.Member; // DTO를 Entity로 바꿀 때 필요한 Member 클래스
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 모든 필드를 받는 생성자를 Lombok이 자동으로 만들어줌
@ToString // 객체 내용을 확인할 수 있는 toString()을 Lombok이 자동으로 만들어줌
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

    public Member toEntity() { // DTO를 DB 저장용 Entity로 바꾸는 메서드
        return new Member(null, email, password); // id는 DB가 만들기 때문에 null로 두고 email, password만 전달
    }
}
