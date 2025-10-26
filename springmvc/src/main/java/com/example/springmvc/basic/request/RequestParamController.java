package com.example.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestParamController {

  // 반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면 view 조회 X
  @RequestMapping("/request-param-v1")
  public void requestParamV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String username = req.getParameter("username");
    int age = Integer.parseInt(req.getParameter("age"));
    log.info("username={}, age={}", username, age);
    resp.getWriter().write("OK");
  }

  



}
