package br.com.fiap.profit.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.profit.exception.RestNotFoundException;
import br.com.fiap.profit.models.Simulador;
import br.com.fiap.profit.repository.SimuladorRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/simuladores")
public class SimuladorController {

    Logger log = LoggerFactory.getLogger(SimuladorController.class);

    @Autowired
    SimuladorRepository repository;

    @GetMapping()
    public Page<Simulador> getAll(@RequestParam(required = false) String tipoInvest, @PageableDefault(size = 5) Pageable pageable){
        if (tipoInvest == null)
            return repository.findAll(pageable);
        return repository.findBytipoInvestContaining(tipoInvest, pageable);
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody @Valid Simulador simulador){
        // if(result.hasErrors()) return ResponseEntity.badRequest().body(new RestValidationError("erro de validação"));
        log.info("Cadastrando simulacao " + simulador);

        simulador.calcularInvestimento(simulador.getValor(), simulador.getAporte(), simulador.getJuros(), simulador.getTempoInvest());
        repository.save(simulador);
        return ResponseEntity.status(HttpStatus.CREATED).body(simulador);
    }

    @GetMapping("{id}")
    public ResponseEntity<Simulador> findById(@PathVariable Long id){
        log.info("Buscando um simulador com o id " + id);
        return ResponseEntity.ok(getSimulador(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Simulador> destroy(@PathVariable Long id){
        log.info("Apagando o simulador com o id " + id);
        repository.delete(getSimulador(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Simulador> update(@PathVariable Long id, @RequestBody @Valid Simulador simulador){
        log.info("Atualizando o usuario com o id " + id);
        getSimulador(id);
        simulador.setId(id);
        repository.save(simulador);
        return ResponseEntity.ok(simulador);
    }

    private Simulador getSimulador(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Simulador não encontrado"));
    }

}
