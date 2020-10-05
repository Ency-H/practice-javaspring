package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        validateDuplicateMember(member);//중복 회원 검사

       memberRepository.save(member);
        return member.getId();
    }

    //같은 이름이 있는 중복 회원 X
    //optional값을 바로 반환하는 것은 안좋음
    //따라서 반환하는 값이 있는 함수식에 붙여 검증식으로 사용한다.
    //로직이 생기는 구문은 팩토링해서 관리
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        //멤버 아이디 넘겨서 특정 회원 조회
        return memberRepository.findById(memberId);
    }
}