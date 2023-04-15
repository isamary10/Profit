package br.com.fiap.profit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.profit.models.Curso;

public interface CursosRepository extends JpaRepository<Curso, Long>{

  Page<Curso> findByNomeContaining(String nome, Pageable pageable);

}
