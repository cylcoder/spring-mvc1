package com.example.servlet.web.frontcontroller.v3;

import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
* HttpServletRequest로 받은 값들을 ParamMap에 넣어서 controller에 전달
* 결과로 model과 logical view name이 있는 ModelView 반환
* view name을 통해 view Path를 찾음
* model에 담긴 값을 다시 HttpServletRequest에 담아 forward 시킴
* */
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

  private Map<String, ControllerV3> controllerMap = new HashMap<>();
  private ViewResolver viewResolver = new ViewResolver();

  public FrontControllerServletV3() {
    controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
    controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
    controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String requestURI = req.getRequestURI();

    ControllerV3 controller = controllerMap.get(requestURI);
    if (controller == null) {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    Map<String, String> paramMap = createParamMap(req);
    ModelView modelView = controller.process(paramMap);
    MyView myView = viewResolver.resolveViewName(modelView.getViewName());
    myView.render(modelView.getModel(), req, resp);
  }

  private Map<String, String> createParamMap(HttpServletRequest req) {
    HashMap<String, String> paramMap = new HashMap<>();

    req
        .getParameterNames()
        .asIterator()
        .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));

    return paramMap;
  }

  private static class ViewResolver {
    MyView resolveViewName(String viewName) {
      return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
  }

}
