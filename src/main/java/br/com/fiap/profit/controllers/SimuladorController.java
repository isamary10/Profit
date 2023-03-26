package br.com.fiap.profit.controllers;

import java.util.ArrayList;
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

import br.com.fiap.profit.models.Simulador;
import br.com.fiap.profit.repository.SimuladorRepository;

@RestController
@RequestMapping("/api/simuladores")
public class SimuladorController {

    Logger log = LoggerFactory.getLogger(SimuladorController.class);

    List<Simulador> simuladores = new ArrayList<>();

    @Autowired
    SimuladorRepository repository;

    @GetMapping()
    public List<Simulador> getAll(){
        return repository.findAll();
    }

    @PostMapping()
    public ResponseEntity<Simulador> create(@RequestBody Simulador simulador){
        log.info("Cadastrando simulacao " + simulador);

        repository.save(simulador);

        return ResponseEntity.status(HttpStatus.CREATED).body(simulador);
    }

    @GetMapping("{id}")
    public ResponseEntity<Simulador> findById(@PathVariable Long id){
        log.info("Buscando um somilador com o id " + id);
        var simuladorEncontrado = repository.findById(id);

        if(simuladorEncontrado.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(simuladorEncontrado.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Simulador> destroy(@PathVariable Long id){
        log.info("Apagando o simulador com o id " + id);
        var simuladorEncontrado = repository.findById(id);

        if(simuladorEncontrado.isEmpty())
            return ResponseEntity.notFound().build();

        repository.delete(simuladorEncontrado.get());

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Simulador> update(@PathVariable Long id, @RequestBody Simulador simulador){
        log.info("Atualizando o usuario com o id " + id);
        var simuladorEncontrado = repository.findById(id);

        if(simuladorEncontrado.isEmpty())
            return ResponseEntity.notFound().build();

        simulador.setId(id);
        repository.save(simulador);

        return ResponseEntity.ok(simulador);
    }

    //MÉTODO NÃO ESTÁ COMPLETO
    @GetMapping("/{id}/calcSimulador")
    public double returnCalcById(@PathVariable Long id, @RequestBody Simulador simulador){
        log.info("Realizando o calculo do investimento " + id);
        var simuladorEncontrado = repository.findById(id);

        var calculo = simulador.calcularInvestimento(simuladorEncontrado.get().getValor(), 
        simuladorEncontrado.get().getAporte(),
        simuladorEncontrado.get().getJuros(), 
        simuladorEncontrado.get().getTempoInvest());
        return calculo;
    }
}
