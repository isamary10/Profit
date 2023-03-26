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

import br.com.fiap.profit.models.Usuario;


@RestController
public class UsuarioController {

    Logger log = LoggerFactory.getLogger(UsuarioController.class);

    List<Usuario> usuarios = new ArrayList<>();

    @GetMapping("/api/usuarios")
    public List<Usuario> getAll(){
        return usuarios;
    }

    @PostMapping("/api/usuario")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        log.info("Cadastrando usuario " + usuario);
        usuario.setId(usuarios.size() + 1l);
        usuarios.add(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("api/usuario/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        log.info("Buscando um usuario com o id " + id);
        var usuarioEncontrado = usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();

        if(usuarioEncontrado.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(usuarioEncontrado.get());
    }

    @DeleteMapping("/api/usuario/{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id){
        var usuarioEncontrado = findById(id); //VERIFICAR PORQUE O NOTFOUND NÃO FUNCIONA
        log.info("Apagando o usuario com o id " + id);

        usuarios.remove(usuarioEncontrado.getBody());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/api/usuario/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario){
        log.info("Atualizando o usuario com o id " + id);
        var usuarioEncontrado = usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();

        if(usuarioEncontrado.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        usuarios.remove(usuarioEncontrado.get());
        usuario.setId(id);
        usuarios.add(usuario);

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }
}
