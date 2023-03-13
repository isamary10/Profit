package br.com.fiap.profit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.profit.Services.UsuarioService;
import br.com.fiap.profit.models.Usuario;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/api/usuario")
    public Usuario cadastrar(Usuario usuario){
        return usuarioService.cadastrarUsuario(usuario);
    }

    @GetMapping("/api/usuario/{id}")
    public Usuario exibir(Integer id){
        return usuarioService.buscarPorId(id);
    }

    @GetMapping("/api/usuarios")
    public List<Usuario> listar(){
        return usuarioService.listarUsuario();
    }

    @PutMapping("/api/usuario/{id}")
    public Usuario atualizar(Integer id, Usuario usuario){
        usuario.setId(id);
        return usuarioService.atualizarUsuario(usuario);
    }

    @DeleteMapping("/api/usuario/{id}")
    public void deletar(Integer id){
        usuarioService.deletarUsuario(id);
    }
}
