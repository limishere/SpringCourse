package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //회원서비스를 만드려면 우선 회원리포지토리가 필요
    private final MemberRepository memberRepository;

    //생성자
    //memberRepository를 new로 생성하는 것이 아니라, 외부에서 넣어주도록 생성자를 만듦
    //=>DI(Dependency injection):의존성 주입
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     */
    public Long join(Member member){

        validateDuplicateMember(member);//중복회원검증(같은 이름이 있는 중복 회원X)
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
//        Optional<Member> result = memberRepository.findByName(member.getName());
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // .ifPresent: 값이 있으면
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    //Optional이기 때문에 가능(기존에는 if문으로 null이 아니면~,,)
    //findByName한 결과값이 Optional이라 바로 .ifPresent로 가능


    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }



}