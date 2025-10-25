package com.example.servlet.domain.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

  MemberRepository memberRepository = MemberRepository.getInstance();

  @AfterEach
  void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void save() {
    // given
    Member savedMember = new Member("savedMember", 20);

    // when
    memberRepository.save(savedMember);

    // then
    Member foundMember = memberRepository.findById(savedMember.getId());
    assertThat(foundMember).isEqualTo(savedMember);
  }

  @Test
  void findAll() {
    // given
    Member foo = new Member("foo", 20);
    Member bar = new Member("bar", 20);

    memberRepository.save(foo);
    memberRepository.save(bar);

    // when
    List<Member> members = memberRepository.findAll();

    // then
    assertThat(members)
        .hasSize(2)
        .contains(foo, bar);
  }

}