package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name); //"name" : 파라미터로 넘어온 name
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http의 body부분에 아래 내용을 직접 넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //return값이 요청한 클라이언트에 그대로 내려감
    }
    // 템플릿엔진과의 차이점은 템플릿엔진은 템플릿엔진은 view라는 템플릿이 있는 상태에서 조작하는 방식이고,
    // 위의 작성한 방식은 view 등이 없이 문자가 그대로 내려간다는 점이다.
    // ->웹에서 소스를 확인하면 html태그가 없음

    //API방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //JSON방식으로 화면에 출력된다
    }

    //static클래스로 만들면 HelloController클래스안에서 이 클래스를 또 쓸 수 있다.
    static class Hello{
        private String name;

        //getter, setter
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}


