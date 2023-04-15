package br.com.fiap.profit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.profit.models.Simulador;

public interface SimuladorRepository extends JpaRepository<Simulador, Long> {

  Page<Simulador> findBytipoInvestContaining(String tipoInvest, Pageable pageable);

}
