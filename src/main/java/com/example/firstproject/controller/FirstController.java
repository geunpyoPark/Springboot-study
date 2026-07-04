package com.example.firstproject.controller;
import org.springframework.stereotype.Controller; //@Controller로 임포트
// URL 연결 요청(@GetMapping())과 동시에 자동으로 임포트
import org.springframework.ui.Model; // Model model 입력시 Model 클래스 패키지 자동 임포트
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model) { // model 객체 받아 오기
        model.addAttribute("username", "근표");
        return "greetings"; //서버가 알아서 templates 디렉토리의 greetings.mustache파일 찾아 웹 브라우저로 전송
    }
    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "홍길동");
        return "goodbye";
    }
}
