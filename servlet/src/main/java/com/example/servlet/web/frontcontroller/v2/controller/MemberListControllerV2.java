package com.example.servlet.web.frontcontroller.v2.controller;

import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;
import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

  private final MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public MyView process(HttpServletRequest req, HttpServletResponse response) {
    List<Member> members = memberRepository.findAll();
    req.setAttribute("members", members);

    return new MyView("/WEB-INF/views/save-result.jsp");
  }

}
