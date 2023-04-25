package br.com.fiap.profit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.profit.models.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Long>{

  Optional<Usuario> findByEmailAndSenha(String email, String senha);

  void findById(String username);

  Optional<Usuario> findByEmail(String email);
}
