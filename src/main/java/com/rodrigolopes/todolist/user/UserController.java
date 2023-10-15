package com.rodrigolopes.todolist.user;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@CrossOrigin(origins = "http://localhost:11444/")
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IUserRepository userRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody UserModel userModel) {

    var user = this.userRepository.findByUsername(userModel.getUsername());

    if (user != null) {
      System.out.println("Usuário já existe");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!!!");

    }

    var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

    userModel.setPassword(passwordHashred);

    System.out.println("IMPRIMINDO usermodel name:  " + userModel.getName());
    System.out.println("IMPRIMINDO usermodel username:  " + userModel.getUsername());
    System.out.println("IMPRIMINDO usermodel password:  " + userModel.getPassword());
    System.out.println("IMPRIMINDO usermodel class:  " + userModel.getClass());

    var userCreated = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }

}
