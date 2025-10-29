package com.example.springmvc.basic.response;

import com.example.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Controller
public class ResponseBodyController {

  @GetMapping("/response-body-string-v1")
  public void responseBodyV1(HttpServletResponse resp) throws IOException {
    resp.getWriter().write("OK");
  }

  /***
   * HttpEntity, ResponseEntity(Http Status 추가)
   * @return
   */
  @GetMapping("/response-body-string-v2")
  public ResponseEntity<String> responseBodyV2() {
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @GetMapping("/response-body-string-v3")
  @ResponseBody
  public String responseBodyV3() {
    return "OK";
  }

  @GetMapping("/response-body-json-v1")
  public ResponseEntity<HelloData> responseBodyJsonV1() {
    HelloData helloData = new HelloData();
    helloData.setUsername("foo");
    helloData.setAge(10);
    return new ResponseEntity<>(helloData, HttpStatus.OK);
  }

  @GetMapping("/response-body-json-v2")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public HelloData responseBodyJsonV2() {
    HelloData helloData = new HelloData();
    helloData.setUsername("foo");
    helloData.setAge(10);
    return helloData;
  }

}
