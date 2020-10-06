package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//(ctrl + shift + T)로 자동으로 테스트 틀을 만들 수 있다.

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    /**
     메모리를 비우기위해 아래같이 하나의 객체를 새로 생성하는 문제가 발생할 수 있음
     즉 테스트 코드와 실제 코드가 다른 객체를 생성해 사용하므로 적절한 테스트를 하지 못함
     MemoryMemberRepository memberRepository = new MemoryMemberRepository();

     이를 위해 Dependency Injection(의존성주입)기법을 사용한다.
    **/

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void join() {
        //given, when, then 패턴을 사용하면 기초적인 테스트 구조를 짤 수 있다.

        //given -> 주어지는 값
        Member member = new Member();
        member.setName("Hello");

        //when -> 값으로 검증하는 로직
        Long saveId = memberService.join(member);

        //then -> 결과값
        Member findMember = memberService.findOne(saveId).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //중복회원 예외처리
    //테스트코드는 실제코드에 포함되지 않기 때문에 한글로 작성가능
    // 테스트코드는 파악할 수 있는 모든 예외 처리 코드를 작성한다.
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring"); //member1과 중복된 이름

        //when
        //try catch 대신 사용 가능
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /* try catch는 대규모 코드에 쓰기 힘듬듬
       try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.")
        }
        */
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}