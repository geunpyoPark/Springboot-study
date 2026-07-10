package com.example.firstproject.repository; // repository 패키지에 속한 인터페이스라는 뜻

import com.example.firstproject.entity.Member; // 저장할 대상인 Member Entity
import org.springframework.data.repository.CrudRepository; // 기본 저장, 조회, 삭제 기능을 제공하는 Spring Data 인터페이스

public interface MemberRepository extends CrudRepository<Member, Long> { // Member Entity를 DB에 저장하고 조회하는 Repository
    /*
     * MemberRepository의 역할
     * 1. Controller에서 memberRepository.save(member)를 호출한다.
     * 2. Spring Data JPA가 save 기능을 자동으로 제공한다.
     * 3. <Member, Long>은 "Member를 저장하고, id 타입은 Long"이라는 뜻이다.
     */
}
