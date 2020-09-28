package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute( "data", "hello?!!");
        return "hello"; //templates 디렉토리에 hello.html로 데이터를 넘겨준다.
    }
    //@RequestMapping은 클래스 레벨에서 사용한다.
    @GetMapping("hello-mvc") // GetMapping은 메소드에만 적용한다.
                             //@GetMapping은 데이터가 URL에 노출된다. 즉, 개인정보 데이터를 다룰땐 사용할 수 없다.(개인정보는 Post방식 사용)
                             //URL에 변수를 포함시켜 요청함려 데이터를 header에 포함하여 전송한다.
                             //캐싱이 가능하다.(한번 접근후, 같은 요청을 할 시 빠른 접근을 위해 레지스터에 데이터를 저장시킴)
    public String helloMvc(@RequestParam("name") String name, Model model) {
        //@RequestParam의 파라미터에 할당할 데이터를 넘겨 주어야한다.(여기에선 https get방식으로 테스트 해봄)
        //tip: 'cmd+p'단축키로 파라미터의 require 목록을 볼 수있다.
        //helloMvc의 첫번째 파라미터 String name에 전달받은 데이터를 할당한다.
        model.addAttribute("name", name);
        //model.addAttribute의 attribute에 객체화된 파라미터를 할당한다.(모델에 담긴다.)
        //parameter는 사용자의 요청(request)로 넘겨받는 값으로 Sting 타입이다.
        //attributes는 키값과 벨류값으로 이루어진 object이다.
        return "hello-template"; //마찬가지로 templates 디렉토리의 hello-template.html로 데이터를 넘겨준다.
    }

    @GetMapping("hello-spring")
    @ResponseBody // RespnseBody를 사용하면 뷰 리졸버를 사용하지 않는다. 위 모델 객체를 통해 전달되는 것과 다름
                  // 대신에 HTTP의 BODY에 문자 내용을 직접 반환(BODY 태그 내용이 아니다!)
                  // BODY의 문자 내용을 객체로 반환하면 컨버터에의해 객체가 JSON으로 변환된다.
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
