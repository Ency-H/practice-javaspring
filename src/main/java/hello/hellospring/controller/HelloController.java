package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute( "data", "hello?!!");
        return "hello"; //templates 디렉토리에 hello.html로 데이터를 넘겨준다.
    }

    @GetMapping("hello-mvc")
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
}
