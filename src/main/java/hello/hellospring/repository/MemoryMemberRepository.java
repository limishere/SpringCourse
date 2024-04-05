package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음,
 * 실무에서는 HashMap 대신 ConcurrentHashMap, AtomicLong 사용 고려
 */

//MemberRepository인터페이스 구현체(회원 리포지토리 메모리 구현체)
public class MemoryMemberRepository implements MemberRepository{

    //save 하기 위해 저장할 곳은 Map을 사용
    private static Map<Long, Member> store = new HashMap<>();
    //0,1,2,,등 key값을 생성해주기 위한 sequence
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id를 세팅하고
        store.put(member.getId(), member); //store에 저장함
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //id값이 없어 null이 반환될 가능성이 있으면,Optional로 감싸서 반환해준다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //stream 객체 생성
                .filter(member -> member.getName().equals(name)) //filter로 가공
                .findAny(); // 조건에 일치하는 요소 1개를 리턴
        //store Map에서 돌려서,
        //member.getName()이 파라미터로 넘어온 name과 같은지 확인(같은경우에만 필터링됨), 찾으면 반환
        //끝까지 돌렸는데 없으면, Optional에 null이 포함되어 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();//store를 비움
    }

}
