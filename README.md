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
- Lombok으로 생성자, `toString()`, 로그 코드 리팩토링
- `@PathVariable`로 URL의 게시글 번호 받기
- Repository에서 게시글을 조회하고 Model로 View에 전달
- Mustache의 섹션 문법으로 게시글 상세 화면 출력
- `findAll()`로 게시글과 회원 목록 조회
- URL 경로에 따라 전체 목록 조회와 단건 조회 구분

## 사용 기술

- Java 17
- Spring Boot 3.1.0
- Spring MVC
- Mustache
- Gradle
- Lombok

## Lombok 리팩토링 정리

반복해서 직접 작성하던 생성자와 `toString()` 메서드를 Lombok 어노테이션으로 줄였다.

```java
@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title;
    private String content;
}
```

`@AllArgsConstructor`는 모든 필드를 받는 생성자를 자동으로 만들어준다.

```java
new ArticleForm(title, content)
```

같은 객체 생성이 가능하도록 생성자 코드를 대신 만들어주는 역할이다.

`@ToString`은 객체 내용을 확인할 수 있는 `toString()` 메서드를 자동으로 만들어준다.

```java
ArticleForm(title=제목, content=내용)
```

처럼 로그에서 객체 값을 확인할 수 있다.

Controller에서는 `System.out.println()` 대신 `@Slf4j`와 `log.info()`를 사용했다.

```java
@Slf4j
@Controller
public class ArticleController {
    log.info("form = {}", form);
}
```

`@Slf4j`는 로그를 찍기 위한 `log` 객체를 자동으로 만들어준다. `log.info()`는 실행 중 확인할 값을 콘솔에 로그 형태로 남긴다.

Entity에는 `@NoArgsConstructor`도 추가했다.

```java
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Article {
}
```

JPA는 Entity 객체를 만들 때 기본 생성자가 필요할 수 있으므로, Entity에는 `@NoArgsConstructor`를 함께 사용하는 것이 안전하다.

주의할 점은 `@ToString`이 비밀번호 같은 민감한 값까지 출력할 수 있다는 것이다. 지금은 학습용이라 출력 흐름을 확인하지만, 실무에서는 password 같은 값은 로그에 남기지 않도록 조심해야 한다.

## 게시글 단건 조회 정리

`/articles/{id}` 요청이 들어오면 URL의 `{id}` 값을 `@PathVariable`로 받는다.

```java
@GetMapping("/articles/{id}")
public String show(@PathVariable Long id, Model model) {
    Article articleEntity = articleRepository.findById(id).orElse(null);
    model.addAttribute("article", articleEntity);
    return "articles/show";
}
```

`articleRepository.findById(id)`는 해당 번호의 게시글을 DB에서 조회한다. 조회 결과가 없을 수도 있으므로 `orElse(null)`을 사용해 게시글이 없으면 `null`을 반환한다.

조회한 게시글은 `model.addAttribute("article", articleEntity)`로 Mustache 화면에 전달한다. `return "articles/show"`는 `templates/articles/show.mustache` 파일을 화면으로 사용한다는 뜻이다.

Mustache에서는 다음처럼 `article` 데이터가 있을 때만 표의 행을 출력한다.

```mustache
{{#article}}
<tr>
    <th>{{id}}</th>
    <td>{{title}}</td>
    <td>{{content}}</td>
</tr>
{{/article}}
```

## 목록 조회와 Model 연결 정리

`/articles` 요청은 Repository의 `findAll()`로 모든 게시글을 조회한다.

```java
@GetMapping("/articles")
public String index(Model model) {
    ArrayList<Article> articleEntityList = articleRepository.findAll();
    model.addAttribute("articleList", articleEntityList);
    return "articles/index";
}
```

`articleEntityList`는 Java 메서드 안에서 사용하는 변수 이름이다. Mustache에서 사용하는 이름은 `model.addAttribute()`의 첫 번째 값인 `articleList`와 맞춰야 한다.

```text
model.addAttribute("articleList", articleEntityList)
                   ↓
{{#articleList}} ... {{/articleList}}
```

`ArticleRepository`에서는 `CrudRepository`의 `findAll()` 반환 자료형을 `ArrayList<Article>`로 재정의했다.

```java
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}
```

`MemberRepository`는 재정의하지 않았기 때문에 기본 반환 자료형인 `Iterable<Member>`로 받는다.

```java
Iterable<Member> members = memberRepository.findAll();
```

두 자료형 모두 여러 데이터를 담을 수 있고 Mustache에서 반복 출력할 수 있다. 이후 실제 프로젝트에서는 `JpaRepository`를 상속해 `List`로 통일하는 방법도 사용할 수 있다.

## 목록 조회와 단건 조회 URL

목록 조회는 특정 번호가 필요하지 않지만, 단건 조회는 URL의 회원 번호를 `@PathVariable`로 받는다.

```text
GET /articles       → 전체 게시글 목록
GET /articles/{id}  → 특정 게시글 조회
GET /members        → 전체 회원 목록
GET /members/{id}   → 특정 회원 조회
```

`index`, `show`는 개발자가 정한 메서드 이름이다. Spring은 메서드 이름이 아니라 `@GetMapping`의 URL을 기준으로 실행할 메서드를 선택한다.

## H2 데이터베이스 접속

애플리케이션을 실행한 뒤 아래 주소로 접속한다.

```text
http://localhost:8080/h2-console
```

사용자 이름은 기본값인 `sa`, 비밀번호는 빈칸을 사용한다. JDBC URL은 애플리케이션 실행 로그에 표시된 `jdbc:h2:mem:...` 값을 입력한다.

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
http://localhost:8080/articles
http://localhost:8080/articles/{id}
http://localhost:8080/members
http://localhost:8080/members/{id}
http://localhost:8080/h2-console
```

회원 목록과 상세 화면을 사용하려면 다음 Mustache 파일이 필요하다.

```text
src/main/resources/templates/members/index.mustache
src/main/resources/templates/members/show.mustache
```

현재 두 파일은 아직 작성하지 않았으므로, Controller가 해당 화면을 반환할 때 템플릿을 찾지 못하는 오류가 발생할 수 있다.

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

### 2026-07-10 Member 저장 코드 컴파일 오류

`MemberController`, `MemberForm`, `Member`, `Repository`를 Article 코드 흐름을 참고해서 만들었지만 컴파일 오류가 발생했다.

주요 원인은 세 가지였다.

```text
1. MemberController에서 MemberForm, Member, MemberRepository import가 빠짐
2. join(MemberForm memberForm)로 받아놓고 form.toEntity()처럼 다른 변수명을 사용함
3. ArticleRepository가 Article이 아니라 Member를 저장하도록 잘못 작성됨
```

잘못된 변수명 사용 예시는 다음과 같다.

```java
Member member = form.toEntity();
```

`join()` 메서드에서 실제로 받은 변수 이름은 `memberForm`이므로 아래처럼 수정했다.

```java
Member member = memberForm.toEntity();
```

또한 `ArticleRepository`는 Article 저장용이므로 아래처럼 수정했다.

```java
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
```

Member 저장을 위해서는 별도의 `MemberRepository`를 만들었다.

```java
public interface MemberRepository extends CrudRepository<Member, Long> {
}
```

정리하면, Controller에서 사용하는 클래스는 import가 필요하고, DTO 변수명은 메서드 안에서 일관되게 사용해야 하며, Repository의 저장 대상 Entity 타입을 정확히 맞춰야 한다.

### 2026-07-21 게시글 상세 조회 오류

게시글 상세 조회 기능을 작성하는 과정에서 코드의 빨간 밑줄과 500 오류가 발생했다.

빨간 밑줄은 기존 `show()` 메서드 안에 동일한 `show()` 메서드를 다시 선언해서 발생했다. Java에서는 메서드 안에 다른 메서드를 선언할 수 없으므로 중복된 메서드 선언을 제거하고 `model.addAttribute()`만 남겼다.

500 오류는 Controller가 `articles/show`를 반환하지만 `templates/articles/show.mustache` 파일이 없어서 발생했다. 반환 문자열과 Mustache 파일 경로는 다음처럼 서로 일치해야 한다.

```text
return "articles/show"
→ src/main/resources/templates/articles/show.mustache
```

화면 파일이 있어도 요청한 번호의 게시글이 DB에 없으면 출력할 데이터가 없다. 따라서 `/articles/1000`처럼 임의의 번호보다 실제 저장된 게시글 번호로 확인해야 한다.

### 2026-07-22 게시글 목록 주소 404 오류

게시글 목록 메서드의 `@GetMapping`에 URL을 지정하지 않아 `/articles` 요청에서 404 오류가 발생했다.

```java
@GetMapping
public String index(Model model) {
}
```

목록 주소를 명시해 해결했다.

```java
@GetMapping("/articles")
public String index(Model model) {
}
```

`@GetMapping`에 경로가 없으면 `/articles`가 아니라 기본 경로 `/`에 연결된다.

### 2026-07-22 회원 단건 조회 문법 오류

Repository에서 회원을 조회할 때 `new`를 잘못 붙여 오류가 발생했다.

```java
Member member = new memberRepository.findById(id).orElse(null);
```

`memberRepository`는 이미 Spring이 만들어 준 객체이고, `findById()`가 조회한 객체를 반환하므로 `new`가 필요하지 않다.

```java
Member member = memberRepository.findById(id).orElse(null);
```

## 참고

이 저장소는 포트폴리오 완성 프로젝트가 아니라, 백엔드 개발 학습 과정을 기록하기 위한 저장소입니다.
