package com.example.servlet.web.frontcontroller.v4;

import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.example.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.example.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 각 Controller에서 ModelView를 생성해서 안에 데이터와 논리적 뷰 이름을 넣어 리턴하던 방식에서
 * FrontController에서 Model 객체를 넘겨주면 비어있는 model 객체에 데이터를 넣은 뒤 논리적 뷰 이름만 반환한다.
 * FrontController에 있는 model 객체에 데이터가 채워졌고, 논리적 뷰 이름을 기반으로 물리적 뷰 이름을 생성해서 forward 시키면 된다.
 */
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

  private final Map<String, ControllerV4> controllerMap = new HashMap<>();
  private final ViewResolver viewResolver = new ViewResolver();

  public FrontControllerServletV4() {
    controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
    controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
    controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String requestURI = req.getRequestURI();

    ControllerV4 controller = controllerMap.get(requestURI);
    if (controller == null) {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    Map<String, String> paramMap = createParamMap(req);
    Map<String, Object> model = new HashMap<>();
    String viewName = controller.process(paramMap, model);
    MyView myView = viewResolver.resolveViewName(viewName);
    myView.render(model, req, resp);
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
