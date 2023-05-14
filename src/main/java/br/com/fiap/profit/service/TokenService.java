package br.com.fiap.profit.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.profit.models.Credencial;
import br.com.fiap.profit.models.Token;
import br.com.fiap.profit.models.Usuario;
import br.com.fiap.profit.repository.UsuariosRepository;
import jakarta.validation.Valid;

@Service
public class TokenService {

  @Autowired
  UsuariosRepository usuariosRepository;

  @Value("${jwt.secret}")
  String secret;

  public Token generateToken(@Valid Credencial credencial) {
    Algorithm alg = Algorithm.HMAC256(secret);
    String token = JWT.create()
              .withSubject(credencial.email())
              .withIssuer("Profit")
              .withExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
              .sign(alg);

    return new Token(token, "JWT", "Bearer");
  }

  public Usuario getValidateUser(String token) {
    Algorithm alg = Algorithm.HMAC256(secret);
    var email =  JWT.require(alg)
              .withIssuer("Profit")
              .build()
              .verify(token)
              .getSubject();

              return usuariosRepository.findByEmail(email)
                      .orElseThrow(() -> new JWTVerificationException("Usuario nao encontrado"));
  }

}
