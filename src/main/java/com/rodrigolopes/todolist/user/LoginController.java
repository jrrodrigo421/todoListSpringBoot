package com.rodrigolopes.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/login")
public class LoginController {

  @Autowired
  private IUserRepository userRepository;

  @PostMapping("/")
  public ResponseEntity login(@RequestBody LoginRequest loginRequest) {

    System.out.println("chegou aqui ");
    UserModel user = userRepository.findByUsername(loginRequest.getUsername());
    System.out.println("loginRequest: " + loginRequest);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
    }

    if (BCrypt.verifyer().verify(loginRequest.getPassword().toCharArray(),
        user.getPassword()).verified) {
      // As credenciais são válidas; você pode gerar um token JWT e incluí-lo na
      // resposta
      // String token = generateToken(user);
      return ResponseEntity.ok(user);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
  }

  // private String generateToken(UserModel user) {
  // return "teste";
  // }

}
