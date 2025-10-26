package com.example.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

  @GetMapping
  public String findAll() {
    return "get users";
  }

  @PostMapping
  public String addUser() {
    return "post user";
  }

  @GetMapping("/{userId}")
  public String findUserById(@PathVariable String userId) {
    return "get user " + userId;
  }

  @PatchMapping("/{userId}")
  public String updateUserById(@PathVariable String userId) {
    return "update user " + userId;
  }

  @DeleteMapping("/{userId}")
  public String deleteUserById(@PathVariable String userId) {
    return "delete user " + userId;
  }

}
