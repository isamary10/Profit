package br.com.fiap.profit.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.profit.models.Usuario;
import br.com.fiap.profit.repository.UsuariosRepository;

@Configuration
public class DataBaseSeeder implements CommandLineRunner {

    @Autowired
    UsuariosRepository usuariosRepository;

    @Override
    public void run(String... args) throws Exception {
        usuariosRepository.saveAll(List.of(
            new Usuario(1L, "Isa Mary", "31525876592", "isa@gmail.com", "11987543256"),
            new Usuario(2L, "Diogo", "75894652136", "diogo@gmail.com", "11978543265"),
            new Usuario(3L, "Luiza", "52698745612", "luiza@gmail.com", "11945782652")
        ));
    }

}
