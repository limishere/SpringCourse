package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

// 회원 리포지토리 인터페이스
// repository (저장소): 회원객체를 저장하는 저장소
public interface MemberRepository {
    //repository에 아래 4가지 기능을 만듦
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll(); //지금까지 저장된 모든 회원 리스트 반환
}
