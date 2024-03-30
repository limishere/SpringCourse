package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

//test는 public으로 안해도 된다
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
    * @AfterEach : 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다.
     * 이렇게되면 다음 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다.
     * @AfterEach 를 사용하면 각 테스트가 종료될 때 마다 이 기능을 실행한다.
     * 여기서는 메모리 DB에 저장된 데이터를 삭제한다.
    * */

    //테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.
    //그러기 위해선, 하나의 테스트가 끝날때마다 리포지토리(저장소)를 깔끔하게 지워주는 코드가 필요!
    //-> test 전체 실행 시, test순서로 인한 에러발생x
    @AfterEach //아래 메서드가 실행이 끝날때마다 어떤 동작을 하는 콜백 메서드
    public void afterEach(){
        repository.clearStore();//테스트가 실행되고 끝날때마다 한번씩 저장소를 다 지우게 된다
    }

    //Test 어노테이션을 붙여주면 아래 메서드를 실행할 수 있게된다.
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //Optional에서 값을 꺼낼때는 get()사용
        Member result = repository.findById(member.getId()).get();
        //생성한 member와 가져온 result값이 같은지 확인하는 방법
        //1
//      System.out.println("result = " + (result == member));
        //2
        //org.junit.jupiter.api.Assertions;
//      Assertions.assertEquals(member, result);//실패하면 에러가 뜸
        //3
        //org.assertj.core.api.Assertions;
        //import static org.assertj.core.api.Assertions.*; 하면 Assertions.생략가능
        assertThat(member).isEqualTo(result);
    }

    @Test
    void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
        //"spring1"을 넣었기때문에 member2와 비교하면 에러남

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        //저장된 member가 2개인지 확인함
    }


}
