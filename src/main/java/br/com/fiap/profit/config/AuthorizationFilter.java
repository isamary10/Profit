package br.com.fiap.profit.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.fiap.profit.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

  @Autowired
  TokenService tokenService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    // pegar o token autenticar
    var token = getToken(request);
    System.out.println(token);

    // se for valido, autenticar
    if (token != null){
      var usuario = tokenService.getValidateUser(token);

      // cria o objeto de autenticação com os dados do usuário autenticado
      Authentication auth = new UsernamePasswordAuthenticationToken(usuario.getEmail(), null, usuario.getAuthorities());

      // seta o usuario autenticado
      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    //chama o proximo filter chain
    filterChain.doFilter(request, response);
  }

  private String getToken(HttpServletRequest request) {
    var header = request.getHeader("Authorization");

    if (header == null || header.isEmpty() || !header.startsWith("Bearer ")){
      return null;
    }

    // ("Bearer ", "") substitui o Bearer + espaço, com a string vazia
    return header.replace("Bearer ", "");
  }
}
