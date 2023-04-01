package br.com.fiap.profit.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<Simulador> getAll(){
        return repository.findAll();
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody @Valid Simulador simulador){
        // if(result.hasErrors()) return ResponseEntity.badRequest().body(new RestValidationError("erro de validação"));
        log.info("Cadastrando simulacao " + simulador);

        repository.save(simulador);
        return ResponseEntity.status(HttpStatus.CREATED).body(simulador);
    }

    @GetMapping("{id}")
    public ResponseEntity<Simulador> findById(@PathVariable Long id){
        log.info("Buscando um simulador com o id " + id);

        var simulador = getSimulador(id);

        return ResponseEntity.ok(simulador);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Simulador> destroy(@PathVariable Long id){
        log.info("Apagando o simulador com o id " + id);

        var simulador = getSimulador(id);
        repository.delete(simulador);

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

    //MÉTODO NÃO ESTÁ COMPLETO
    @GetMapping("/{id}/calcSimulador")
    public double returnCalcById(@PathVariable Long id, @RequestBody Simulador simulador){
        log.info("Realizando o calculo do investimento " + id);
        var simuladorEncontrado = getSimulador(id);

        var calculo = simulador.calcularInvestimento(simuladorEncontrado.getValor(), 
        simuladorEncontrado.getAporte(),
        simuladorEncontrado.getJuros(),
        simuladorEncontrado.getTempoInvest());
        return calculo;
    }
}
