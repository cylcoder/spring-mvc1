package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        logUserInfo(username, age);
        response.getWriter().write("ok");
    }

    @ResponseBody // HTTP 응답 메시지에 반환값을 넣어서 반환(RestController와 같음, ViewResolver 작동 X)
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        logUserInfo(memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        logUserInfo(username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        logUserInfo(username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamV5(@RequestParam(required = true) String username,
                                 @RequestParam(required = false) int age) {
        /*
        * username은 필수지만 age는 선택이다.
        * 이 때, 아래와 같은 요청을 하면 예외가 발생한다.
        * http://localhost:8080/request-param-required?username=usr
        * 왜냐하면 age의 값이 없기 때문에 null이 주어지는데, int형은 null을 가질 수 없기 때문이다.
        * 만약 age의 타입을 String으로 바꾸고 로그를 찍어보면 아래와 같은 결과를 확인할 수 있다.
        * username=usr, age=null
        * */

        logUserInfo(username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            // age에 defulatValue를 정수형으로 지정했으므로 int형을 사용할 수 있다.
            @RequestParam(required = false, defaultValue = "-1") int age) {
        logUserInfo(username, age);
        return "ok";
    }

    /**
     * @RequestParam Map, MultiValueMap
     * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        System.out.println(paramMap.get("username").toString());
        System.out.println(Integer.parseInt(paramMap.get("age").toString()));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        logUserInfo(helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        logUserInfo(helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    private static void logUserInfo(String username, int age) {
        log.info("username={}, age={}", username, age);
    }
}
