package com.rodrigolopes.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @PostMapping("/")
  public void create(@RequestBody UserModel userModel) {
    System.out.println("IMPRIMINDO usermodel" + userModel.getName());
    System.out.println("IMPRIMINDO usermodel" + userModel.getUsername());
    System.out.println("IMPRIMINDO usermodel" + userModel.getPassword());
    System.out.println("IMPRIMINDO usermodel" + userModel.getClass());
  }
}
