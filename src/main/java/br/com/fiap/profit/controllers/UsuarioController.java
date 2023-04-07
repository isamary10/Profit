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
import br.com.fiap.profit.models.Usuario;
import br.com.fiap.profit.repository.UsuariosRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    UsuariosRepository repository;

    @GetMapping
    public List<Usuario> getAll(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Usuario usuario){
        log.info("Cadastrando usuario " + usuario);

        repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        log.info("Buscando um usuario com o id " + id);
        return ResponseEntity.ok(getUsuario(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id){
        log.info("Apagando o usuario com o id " + id);
        repository.delete(getUsuario(id));

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody @Valid Usuario usuario){
        log.info("Atualizando o usuario com o id " + id);
        getUsuario(id);
        usuario.setId(id);
        repository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    private Usuario getUsuario(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));
    }
}
