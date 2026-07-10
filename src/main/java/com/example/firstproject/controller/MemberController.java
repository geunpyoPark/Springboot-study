package com.example.firstproject.controller; // controller 패키지에 속한 클래스라는 뜻

import com.example.firstproject.dto.MemberForm; // 회원가입 폼 데이터를 받을 DTO 클래스
import com.example.firstproject.entity.Member; // DB에 저장할 회원 Entity 클래스
import com.example.firstproject.repository.MemberRepository; // Member를 DB에 저장할 Repository
import org.springframework.beans.factory.annotation.Autowired; // Spring이 Repository 객체를 자동으로 넣어주게 하는 어노테이션
import org.springframework.stereotype.Controller; // 이 클래스를 Controller로 등록하는 어노테이션
import org.springframework.web.bind.annotation.GetMapping; // GET 요청 주소를 연결하는 어노테이션
import org.springframework.web.bind.annotation.PostMapping; // POST 요청 주소를 연결하는 어노테이션

@Controller // Spring에게 "이 클래스는 요청을 받는 Controller입니다"라고 알려줌
public class MemberController { // 회원가입 관련 요청을 처리하는 Controller 클래스
    /*
     * 전체 흐름
     * 1. 사용자가 /signup 주소로 접속한다.
     * 2. signUpPage()가 실행되고 members/new.mustache 화면을 보여준다.
     * 3. 사용자가 이메일과 비밀번호를 입력하고 Submit을 누른다.
     * 4. /join 주소로 POST 요청이 간다.
     * 5. join() 메서드가 MemberForm으로 입력값을 받는다.
     * 6. MemberForm을 Member Entity로 바꾼다.
     * 7. MemberRepository로 DB에 저장한다.
     */

    @Autowired // Spring이 MemberRepository 객체를 자동으로 넣어줌
    MemberRepository memberRepository; // DB에 Member를 저장할 때 사용하는 객체

    @GetMapping("/signup") // 브라우저에서 /signup 주소로 GET 요청이 오면 아래 메서드 실행
    public String signUpPage(){ // 회원가입 화면을 보여주는 메서드
        return "members/new"; // templates/members/new.mustache 파일을 화면으로 보여줌
    }

    @PostMapping("/join") // 회원가입 폼에서 /join 주소로 POST 요청이 오면 아래 메서드 실행
    public String join(MemberForm memberForm){ // 폼에서 넘어온 email, password 값을 MemberForm 객체로 받음
        System.out.println(memberForm.toString()); // DTO에 입력값이 잘 담겼는지 콘솔에 출력
        Member member = memberForm.toEntity(); // DTO를 DB에 저장할 수 있는 Entity 객체로 변환
        System.out.println(member.toString()); // 저장 전 Entity 상태를 콘솔에 출력, 아직 id는 null일 수 있음
        Member saved = memberRepository.save(member); // Entity를 DB에 저장하고, 저장된 결과를 saved에 담음
        System.out.println(saved.toString()); // 저장 후 Entity 상태를 콘솔에 출력, DB가 id를 만들어줌
        return ""; // 아직 다음 화면을 정하지 않은 상태, 교재 진행에 따라 나중에 수정될 수 있음
    }
}
