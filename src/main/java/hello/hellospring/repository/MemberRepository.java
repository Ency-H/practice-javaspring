package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //저장소에 회원 저장
    Optional<Member> findById(Long id); //id값으로 회원찾기
    Optional<Member> findByName(String name); //이름으로 회원찾기
    //Optional은 null이 될 수도 있는 객체를 감싸는 일종의 래퍼 클래스, Optional 변수는 할당되는 값의 타입이 특정되지 않는 상황에서 null값 처리를 위해 사용
    List<Member> findAll(); //저장소에 저장된 모든 회원 반환
}
