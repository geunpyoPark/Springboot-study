# Spring Boot Study

Spring Boot와 MVC 구조를 학습하기 위한 책 실습 저장소입니다.

## 학습 목적

- Spring Boot 프로젝트 기본 구조 이해
- Controller와 URL 연결 연습
- Model을 사용해 View에 데이터 전달
- Mustache 템플릿으로 화면 출력

## 지금까지 실습한 내용

- `/hi` 요청을 받아 `greetings.mustache` 화면 출력
- `/bye` 요청을 받아 `goodbye.mustache` 화면 출력
- `/random-quote` 요청을 받아 랜덤 명언 화면 출력
- `model.addAttribute()`로 Controller에서 View로 데이터 전달

## 사용 기술

- Java 17
- Spring Boot 3.1.0
- Spring MVC
- Mustache
- Gradle

## 실행 방법

```bash
./gradlew bootRun
```

실행 후 브라우저에서 아래 주소로 확인할 수 있습니다.

```text
http://localhost:8080/hi
http://localhost:8080/bye
http://localhost:8080/random-quote
```

## 참고

이 저장소는 포트폴리오 완성 프로젝트가 아니라, 백엔드 개발 학습 과정을 기록하기 위한 저장소입니다.
