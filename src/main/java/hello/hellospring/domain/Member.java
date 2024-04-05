package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//도메인: 비즈니스 도메인 객체, 예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨

//회원 객체
@Entity //jpa가 관리하는 엔티티
public class Member {

    //PK를 맵핑
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //IDENTITY:쿼리에 id를 넣는게 아니라 db에 값을 넣으면 db가 id를 자동생성해주는 이런 것을 IDENTITY전략
    private Long id; //시스템이 정해주는 id
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
