package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//Controller 어노테이션은 어노테이션된 객체를 스프링 컨테이너에 저장해 관리한다.
@Controller
public class MemberController {

    private final MemberService memberService;

    //Autowired 어노테이션은 스프링컨테이너에서 관리하는 값을 불러올 수 있음(컨테이너에 저장된 변수와 내부 클래스의 변수를 연결)
    @Autowired
    public MemberController(MemberService memberService ) {
        this.memberService = memberService;
    }

    //Get방식은 url로 직접접근하는 방식, 조회할때 주로 씀
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    //데이터를 등록할때 주로 PostMapping을 쓴다.
    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //초기 html로 파일로 이동
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memeberList";
    }
}
