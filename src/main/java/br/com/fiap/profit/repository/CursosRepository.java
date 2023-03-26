package br.com.fiap.profit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.profit.models.Curso;

public interface CursosRepository extends JpaRepository<Curso, Long>{

}
