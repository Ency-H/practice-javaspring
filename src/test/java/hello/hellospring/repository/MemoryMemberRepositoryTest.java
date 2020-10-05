package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

//모든 테스트 케이스는 순서에 의존적으로 설계하면 안된다.
//테스트 케이스가 하나 끝나면 반드시 클리어해줘야 한다.

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //케이스별로 테스트가 끝난후 repository를 비운다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    //각 기능별로 케이스 작성하여 테스트
    public void save() {
        Member member = new Member();
        member.setName("HDP");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();//get으로 optional로 감싸진 값을 꺼낼수 있지만 좋은 방법은 아님, 테스트에서만 간단히 사용
        //Assertions기능을 이용해 기대값과 실제 반환값이 일치하는지 테스트할 수 있다.
        //Assertions.assertEquals(member, result);

        //junit이 아닌 assertj에서 지원하는 assertThat을 이용하면 더욱 편하게 테스트 가능
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("HDP1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("HDP2");
        repository.save(member2);

        Member result = repository.findByName("HDP1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("HDP1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("HDP2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
//테스트 케이스를 먼저 작성후 클래스를 개발하는 기법도 있음 -> TDD개발
//케이스가 수백가지인 경우 패키지 단위로 빌드, 런하면 됨
//테스트 케이스 없는 개발은 협업에서 불가능!!!
//테스트 관련한 공부는 꼭 깊이 있게 공부 할 것
