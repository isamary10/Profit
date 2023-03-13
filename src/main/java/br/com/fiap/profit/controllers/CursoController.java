package br.com.fiap.profit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.profit.Services.CursoService;
import br.com.fiap.profit.models.Curso;

@RestController
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping("/api/user/{userId}/curso")
    public Curso cadastrar(Curso curso){
        return cursoService.cadastrarCurso(curso);
    }

    @GetMapping("/profit/api/curso/{id}")
    public Curso exibir(Integer id){
        return cursoService.buscarPorId(id);
    }


    @GetMapping("/profit/api/cursos")
    public List<Curso> listar(){
        return cursoService.listarCurso();
    }

    @PutMapping("/profit/api/user/{userId}/curso/{cursoId}")
    public Curso atualizar(Integer id, Curso curso){
        curso.setId(id);
        return cursoService.atualizarCurso(curso);
    }

    @DeleteMapping("/profit/api/user/{userId}/curso/{cursoId}")
    public void deletar(Integer id){
        cursoService.deletarCurso(id);
    }

}