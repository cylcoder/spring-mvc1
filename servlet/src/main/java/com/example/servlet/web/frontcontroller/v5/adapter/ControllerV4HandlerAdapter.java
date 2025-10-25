package com.example.servlet.web.frontcontroller.v5.adapter;

import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.v4.ControllerV4;
import com.example.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

  @Override
  public boolean supports(Object handler) {
    return (handler instanceof ControllerV4);
  }

  @Override
  public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
    ControllerV4 controller = (ControllerV4) handler;
    Map<String, String> paramMap = createParamMap(req);
    Map<String, Object> model = new HashMap<>();
    String viewName = controller.process(paramMap, model);
    ModelView modelView = new ModelView(viewName);
    modelView.setModel(model);
    return modelView;
  }

  private Map<String, String> createParamMap(HttpServletRequest req) {
    HashMap<String, String> paramMap = new HashMap<>();

    req
        .getParameterNames()
        .asIterator()
        .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));

    return paramMap;
  }

}
