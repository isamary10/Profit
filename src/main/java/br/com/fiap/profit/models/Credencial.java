package br.com.fiap.profit.models;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public record Credencial(String email, String senha) {

  public Authentication toAuhentication() {
    return new UsernamePasswordAuthenticationToken(email, senha);
  }

}
