package com.rodrigolopes.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rodrigolopes.todolist.user.IUserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class filterTaskAuth implements Filter {

//   @Override
//   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//       throws IOException, ServletException {
//     System.out.println("CHEGOU NO doFilter");
//     chain.doFilter(request, response);

//   }

// }
@Component
public class filterTaskAuth extends OncePerRequestFilter {

  @Autowired
  private IUserRepository iUserRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    // pegar autenticação (usuario e senha)

    var authorization = request.getHeader("Authorization");

    System.out.println("\n IMPRIMINDO authorization: \n" + authorization);

    var newAuth = authorization.substring("Basic".length()).trim();

    System.out.println("\n IMPRIMIDO newAuth: \n" + newAuth);

    byte[] authDecode = Base64.getDecoder().decode(newAuth);

    System.out.println("IMPRINDO authDecode: \n" + authDecode);

    var authString = new String(authDecode);

    System.out.println("IMPRIMINDO authString" + authString);

    String[] credentials = authString.split(":");
    String username = credentials[0];
    String password = credentials[1];

    System.out.println("username: " + username);
    System.out.println("password: " + password);

    // validar usuario

    var validateUSer = this.iUserRepository.findByUsername(username);

    if (validateUSer == null) {
      response.sendError(401, "Usuário sem autorização");
    } else {
      // validar senha
      var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), validateUSer.getPassword());
      if (passwordVerify.verified) {
        filterChain.doFilter(request, response);
      } else {
        response.sendError(403);
      }

    }

  }

}
