package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //인터페이스가 인터페이스를 받을 땐 extends
//    스프링 데이터 JPA가 SpringDataJpaMemberRepository 를 스프링 빈으로 자동 등록해준다.

    //JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
