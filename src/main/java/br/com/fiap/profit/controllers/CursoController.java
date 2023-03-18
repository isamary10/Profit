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

import br.com.fiap.profit.models.Curso;

@RestController
public class CursoController {

    Logger log = LoggerFactory.getLogger(CursoController.class);

    List<Curso> cursos = new ArrayList<>();

    @GetMapping("/api/cursos")
    public List<Curso> getAll(){
        return cursos;
    }

    @PostMapping("/api/curso")
    public ResponseEntity<Curso> create(@RequestBody Curso curso){
        log.info("cadastrando curso " + curso);
        curso.setId(cursos.size() + 1l);
        cursos.add(curso);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/curso/{id}")
    public ResponseEntity<Curso> fidById(@PathVariable Long id){
        log.info("Buscando um curso com o id " + id);
        var cursoEncontrado = cursos.stream().filter(c -> c.getId().equals(id)).findFirst();

        if(cursoEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(cursoEncontrado.get());
    }

    @DeleteMapping("/api/curso/{id}")
    public ResponseEntity<Curso> destroy(@PathVariable Long id){
        log.info("Apagando o curso com o id " + id);
        var cursoEncontrado = cursos.stream().filter(c -> c.getId().equals(id)).findFirst();

        if(cursoEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        cursos.remove(cursoEncontrado.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/api/curso/{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso curso){
        log.info("Atualizando o curso com o id " + id);
        var cursoEncontrado = cursos.stream().filter(c -> c.getId().equals(id)).findFirst();

        if(cursoEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        cursos.remove(cursoEncontrado.get());
        curso.setId(id);
        cursos.add(curso);

        return ResponseEntity.status(HttpStatus.OK).body(curso);
    }
}