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
- 게시글 작성 폼에서 입력값을 `ArticleForm`으로 전달

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
http://localhost:8080/articles/new
```

## 트러블슈팅

### 2026-07-05 form action 경로 문제

게시글 작성 폼에서 Submit을 눌렀을 때 기대한 요청 주소는 `/articles/create`였지만, 실제 브라우저 주소는 `/articles/articles/create`로 이동했다.

원인은 `form` 태그의 `action` 값이 `/` 없이 작성된 상대경로였기 때문이다.

```html
<form action="articles/create" method="POST">
```

현재 페이지 주소가 `/articles/new`였기 때문에 브라우저가 현재 경로를 기준으로 `articles/create`를 붙여 `/articles/articles/create`로 요청했다.

해결 방법은 `action` 앞에 `/`를 붙여 루트 기준 경로로 작성하는 것이다.

```html
<form action="/articles/create" method="POST">
```

정리하면, `articles/create`는 현재 주소 기준 상대경로이고 `/articles/create`는 서버 루트 기준 경로이다.

## 참고

이 저장소는 포트폴리오 완성 프로젝트가 아니라, 백엔드 개발 학습 과정을 기록하기 위한 저장소입니다.
