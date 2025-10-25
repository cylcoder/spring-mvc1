package com.example.servlet.web.frontcontroller.v2;

import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v1.ControllerV1;
import com.example.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.example.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.example.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import com.example.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import com.example.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import com.example.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
* 각 controller에서 view 관련 코드를 없애고 view path만 리턴하도록 수정
* controller.process()로 반환되는 MyView 객체의 render()는 지정된 view path로 forward 시키는 메서드
* */
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

  private Map<String, ControllerV2> controllerMap = new HashMap<>();

  public FrontControllerServletV2() {
    controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
    controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
    controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String requestURI = req.getRequestURI();
    ControllerV2 controller = controllerMap.get(requestURI);

    if (controller == null) {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    MyView view = controller.process(req, resp);
    view.render(req, resp);
  }

}
