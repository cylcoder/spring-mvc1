package com.example.servlet.web.springmvc.v1;

import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberListControllerV1 {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @RequestMapping("/springmvc/v1/members/members")
  public ModelAndView process(HttpServletRequest req, HttpServletResponse resp) {
    List<Member> members = memberRepository.findAll();
    ModelAndView modelAndView = new ModelAndView("members");
    modelAndView.addObject("members", members);
    return modelAndView;
  }

}
