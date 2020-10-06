package hello.hellospring.service;
/**
 * 스프링 컨테이너에 직접 Bean 구성 클래스를 등록하는 방법
 * @Controller, @Service, @Repository 구성의 컴포넌트 스캔은 편하지만
 * 정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야 하는 경우 스프링빈을 직접 등록한다.
 * 이 외 다양한 의존성 주입방법이 있으므로 학습해야함
 * 하지만 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다.
 */
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Configuration 어노테이션은 스프링 컨테이너에 해당 클래스가 Bean 구성 클래스임을 알려준다.
@Configuration
public class SpringConfig {

    //Bean 어노테이션은 스프링 컨테이너에 Bean구성 클래스를 등록하도록 하는 어노테이션이다.
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
