package br.com.fiap.profit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.profit.models.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Long>{

}
