package com.example.servlet.web.servlet;

import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

  MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    System.out.println("MemberSaveServlet.service");

    String username = req.getParameter("username");
    int age = Integer.parseInt(req.getParameter("age"));

    Member member = new Member(username, age);
    System.out.println("member = " + member);
    memberRepository.save(member);

    resp.setContentType("text/html");
    resp.setCharacterEncoding("utf-8");

    PrintWriter w = resp.getWriter();
    w.write("""
        <html>
        <head>
            <meta charset="UTF-8">
        </head>
        <body>
            성공
            <ul>
                <li>id=%d</li>
                <li>username=%s</li>
                <li>age=%d</li>
            </ul>
            <a href="/index.html">메인</a>
        </body>
        </html>
        """.formatted(member.getId(), member.getUsername(), member.getAge()));
  }

}
