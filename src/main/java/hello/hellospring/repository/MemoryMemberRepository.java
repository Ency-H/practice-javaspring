package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//Repository 어노테이션은 외부I/O를 처리하기위한 객체를 스츠링컨테이너에 생성한다.
//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //실무에서는 동시성 문제로 다르게 쓰임
    private static long sequence = 0L; // 실무에선 동시성 문제로 다르게 쓰임

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null값 반환되는 경우를 생각해 Optional로 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //실무에선 List를 많이 씀
    }

    //테스트 후 repository 메모리를 비우기 위함
    public void clearStore() {
        store.clear();
    }
}
