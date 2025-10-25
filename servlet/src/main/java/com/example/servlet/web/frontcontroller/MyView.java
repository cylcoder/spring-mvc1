package com.example.servlet.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyView {

  private String viewPath;

  public void render(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
    dispatcher.forward(req, resp);
  }

  public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    setRequestFromModel(model, req);
    RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
    dispatcher.forward(req, resp);
  }

  private void setRequestFromModel(Map<String, Object> model, HttpServletRequest req) {
    model.forEach(req::setAttribute);
  }

}
