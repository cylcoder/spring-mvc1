package com.example.servlet.web.frontcontroller.v2.controller;

import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;
import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberSaveControllerV2 implements ControllerV2 {

  private final MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public MyView process(HttpServletRequest req, HttpServletResponse response) {
    String username = req.getParameter("username");
    int age = Integer.parseInt(req.getParameter("age"));
    Member member = new Member(username, age);
    memberRepository.save(member);

    req.setAttribute("member", member);
    return new MyView("/WEB-INF/views/save-result.jsp");
  }

}
