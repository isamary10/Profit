package br.com.fiap.profit.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.profit.models.Curso;
import br.com.fiap.profit.models.Simulador;
import br.com.fiap.profit.models.Usuario;
import br.com.fiap.profit.repository.CursosRepository;
import br.com.fiap.profit.repository.SimuladorRepository;
import br.com.fiap.profit.repository.UsuariosRepository;

@Configuration
public class DataBaseSeeder implements CommandLineRunner {

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    CursosRepository cursosRepository;

    @Autowired
    SimuladorRepository simuladorRepository;

    @Override
    public void run(String... args) throws Exception {

        Usuario user1 = new Usuario(1L, "Isa Mary", "31525876592", "isa@gmail.com", "11987543256");
        Usuario user2 = new Usuario(2L, "Diogo", "75894652136", "diogo@gmail.com", "11978543265");
        Usuario user3 = new Usuario(3L, "Luiza", "52698745612", "luiza@gmail.com", "11945782652");

        usuariosRepository.saveAll(List.of(user1, user2, user3));

        cursosRepository.saveAll(List.of(
            Curso.builder().nome("Debentures").duracao("4h").descricao("Curso de Renda Fixa")
                .link("www.curso.com.br.debenture").usuario(user1).build(),
            Curso.builder().nome("Debentures").duracao("4h").descricao("Curso de Renda Variável")
                .link("www.curso.com.br.debenture").usuario(user2).build(),
            Curso.builder().nome("Debentures").duracao("4h").descricao("Curso de Day Trade")
                .link("www.curso.com.br.debenture").usuario(user3).build(),
            Curso.builder().nome("Debentures").duracao("4h").descricao("Curso de Renda Fixa")
                .link("www.curso.com.br.debenture").usuario(user1).build(),
            Curso.builder().nome("Debentures").duracao("4h").descricao("Curso de Renda Variável")
                .link("www.curso.com.br.debenture").usuario(user2).build(),
            Curso.builder().nome("Debentures").duracao("4h").descricao("Curso de Day Trade")
                .link("www.curso.com.br.debenture").usuario(user3).build(),
            Curso.builder().nome("Debentures").duracao("4h").descricao("Curso de Renda Fixa")
                .link("www.curso.com.br.debenture").usuario(user1).build(),
            Curso.builder().nome("Debentures").duracao("4h").descricao("Curso de Renda Variável")
                .link("www.curso.com.br.debenture").usuario(user2).build(),
            Curso.builder().nome("Debentures").duracao("4h").descricao("Curso de Day Trade")
                .link("www.curso.com.br.debenture").usuario(user3).build(),
            Curso.builder().nome("Debentures").duracao("4h").descricao("Curso de Renda Fixa")
                .link("www.curso.com.br.debenture").usuario(user1).build(),
            Curso.builder().nome("Debentures").duracao("4h").descricao("Curso de Renda Variável")
                .link("www.curso.com.br.debenture").usuario(user2).build(),
            Curso.builder().nome("Debentures").duracao("4h").descricao("Curso de Day Trade")
                .link("www.curso.com.br.debenture").usuario(user3).build()
        ));

        simuladorRepository.saveAll(List.of(
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("CDB").tempoInvest(24).juros(10).usuario(user1).build(),
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("Tesouro Direto").tempoInvest(36).juros(10).usuario(user2).build(),
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("Debenture").tempoInvest(36).juros(10).usuario(user3).build(),
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("CDB").tempoInvest(24).juros(10).usuario(user1).build(),
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("Tesouro Direto").tempoInvest(36).juros(10).usuario(user2).build(),
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("Debenture").tempoInvest(36).juros(10).usuario(user3).build(),
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("CDB").tempoInvest(24).juros(10).usuario(user1).build(),
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("Tesouro Direto").tempoInvest(36).juros(10).usuario(user2).build(),
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("Debenture").tempoInvest(36).juros(10).usuario(user3).build(),
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("CDB").tempoInvest(24).juros(10).usuario(user1).build(),
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("Tesouro Direto").tempoInvest(36).juros(10).usuario(user2).build(),
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("Debenture").tempoInvest(36).juros(10).usuario(user3).build(),
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("CDB").tempoInvest(24).juros(10).usuario(user1).build(),
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("Tesouro Direto").tempoInvest(36).juros(10).usuario(user2).build(),
            Simulador.builder().valor(10000.00).aporte(500.00).tipoInvest("Debenture").tempoInvest(36).juros(10).usuario(user3).build()
        ));

    }

}