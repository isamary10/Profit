package br.com.fiap.profit.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.profit.models.Simulador;

@RestController
public class SimuladorController {

    Logger log = LoggerFactory.getLogger(SimuladorController.class);

    List<Simulador> simuladores = new ArrayList<>();

    @GetMapping("/api/simuladores")
    public List<Simulador> getAll(){
        return simuladores;
    }

    @PostMapping("/api/simulador")
    public ResponseEntity<Simulador> create(@RequestBody Simulador simulador){
        log.info("Cadastrando simulacao " + simulador);
        simulador.setId(simuladores.size() + 1l);
        simuladores.add(simulador);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/simulador/{id}")
    public ResponseEntity<Simulador> findById(@PathVariable Long id){
        log.info("Buscando um somilador com o id " + id);
        var simuladorEncontrado = simuladores.stream().filter(s -> s.getId().equals(id)).findFirst();

        if(simuladorEncontrado.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(simuladorEncontrado.get());
    }

    @DeleteMapping("/api/simulador/{id}")
    public ResponseEntity<Simulador> destroy(@PathVariable Long id){
        var simuladorEncontrado = findById(id); //VERIFICAR PORQUE O NOTFOUND NÃO FUNCIONA

        simuladores.remove(simuladorEncontrado.getBody());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("api/simulador/{id}")
    public ResponseEntity<Simulador> update(@PathVariable Long id, @RequestBody Simulador simulador){
        log.info("Atualizando o usuario com o id " + id);
        var simuladorEncontrado = simuladores.stream().filter(s -> s.getId().equals(id)).findFirst();

        if(simuladorEncontrado.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        simuladores.remove(simuladorEncontrado.get());
        simulador.setId(id);
        simuladores.add(simulador);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //MÉTODO NÃO ESTÁ COMPLETO
    @GetMapping("api/simulador/{id}/calcSimulador")
    public double returnCalcById(@PathVariable Long id, @RequestBody Simulador simulador){
        log.info("Realizando o calculo do investimento " + id);
        var simuladorEncontrado = simuladores.stream().filter(s -> s.getId().equals(id)).findFirst();

        var calculo = simulador.calcularInvestimento(simuladorEncontrado.get().getValor(), 
        simuladorEncontrado.get().getAporte(), 
        simuladorEncontrado.get().getJuros(), 
        simuladorEncontrado.get().getTempoInvest());
        return calculo;
    }
}
