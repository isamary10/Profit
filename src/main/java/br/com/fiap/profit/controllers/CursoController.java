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
import br.com.fiap.profit.models.Curso;
import br.com.fiap.profit.repository.CursosRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    Logger log = LoggerFactory.getLogger(CursoController.class);

    @Autowired
    CursosRepository repository;

    @GetMapping
    public List<Curso> getAll(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Curso curso){
        log.info("cadastrando curso " + curso);

        repository.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @GetMapping("{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id){
        log.info("Buscando um curso com o id " + id);

        var curso = getCurso(id);

        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Curso> destroy(@PathVariable Long id){
        log.info("Apagando o curso com o id " + id);

        var curso = getCurso(id);
        repository.delete(curso);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody @Valid Curso curso){
        log.info("Atualizando o curso com o id " + id);

        getCurso(id);
        curso.setId(id);
        repository.save(curso);

        return ResponseEntity.ok(curso);
    }

    private Curso getCurso(Long id){
        return repository.findById(id).orElseThrow(() -> new RestNotFoundException("Curso não encontrado"));
    }
}