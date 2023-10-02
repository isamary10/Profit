package br.com.fiap.profit.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.profit.exception.RestNotFoundException;
import br.com.fiap.profit.models.Curso;
import br.com.fiap.profit.models.Usuario;
import br.com.fiap.profit.repository.CursosRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    Logger log = LoggerFactory.getLogger(CursoController.class);

    @Autowired
    CursosRepository repository; 

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> getAll(
        @RequestParam(required = false) String nome,
        @ParameterObject @PageableDefault(size = 5) Pageable pageable) {
        Page<Curso> cursos = (nome == null) ?
            repository.findAll(pageable) :
            repository.findByNomeContaining(nome, pageable);

        return assembler.toModel(cursos.map(Curso::toEntityModel));
    }

    @PostMapping
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "curso cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "dados invalidos, a validação falhou")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid Curso curso){
        log.info("cadastrando curso " + curso);

        var auth = SecurityContextHolder.getContext().getAuthentication();
        curso.setUsuario((Usuario) auth.getPrincipal());

        repository.save(curso);
        return ResponseEntity
            .created(curso.toEntityModel().getRequiredLink("self").toUri())
            .body(curso.toEntityModel());
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Detalhes do curso",
        description = "Retorna os dados de um curso passado pelo parâmetrode path id"
    )
    public EntityModel<Curso> findById(@PathVariable Long id){
        log.info("Buscando um curso com o id " + id);

        return getCurso(id).toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Curso> destroy(@PathVariable Long id){
        log.info("Apagando o curso com o id " + id);

        repository.delete(getCurso(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public EntityModel<Curso> update(@PathVariable Long id, @RequestBody @Valid Curso curso){
        log.info("Atualizando o curso com o id " + id);
        getCurso(id);
        curso.setId(id);
        repository.save(curso);
        return curso.toEntityModel();
    }

    private Curso getCurso(Long id){
        return repository.findById(id).orElseThrow(() -> new RestNotFoundException("Curso não encontrado"));
    }
}