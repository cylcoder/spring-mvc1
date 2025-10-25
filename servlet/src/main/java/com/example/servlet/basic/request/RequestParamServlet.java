package com.example.servlet.basic.req;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletreq;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    System.out.println("[전체 파라미터 조회] - start");

    /*
    Enumeration<String> parameterNames = req.getParameterNames();
    while (parameterNames.hasMoreElements()) {
        String paramName = parameterNames.nextElement();
        System.out.println(paramName + "=" + req.getParameter(paramName));
    }
    */

    req
        .getParameterNames()
        .asIterator()
        .forEachRemaining(paramName ->
            System.out.println(paramName + "=" + req.getParameter(paramName))
        );

    System.out.println("[전체 파라미터 조회] - end");
    System.out.println();

    System.out.println("[단일 파라미터 조회]");
    String username = req.getParameter("username");
    System.out.println("req.getParameter(username) = " + username);

    String age = req.getParameter("age");
    System.out.println("req.getParameter(age) = " + age);
    System.out.println();

    System.out.println("[이름이 같은 복수 파라미터 조회]");
    System.out.println("req.getParameterValues(username)");
    String[] usernames = req.getParameterValues("username");
    for (String name : usernames) {
      System.out.println("username=" + name);
    }

    resp.getWriter().write("ok");
  }
}
