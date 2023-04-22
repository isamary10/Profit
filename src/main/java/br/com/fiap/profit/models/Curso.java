package br.com.fiap.profit.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.profit.controllers.CursoController;
import br.com.fiap.profit.controllers.UsuarioController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data //getter, setter, constructor, to string, equalsAndHashCode
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    private String nome;

    @NotNull
    private String duracao;

    @NotBlank
    @Size(min = 10)
    private String descricao;

    @NotNull
    private String link;

    @ManyToOne
    private Usuario usuario;

    public EntityModel<Curso> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(CursoController.class).findById(id)).withSelfRel(),
            linkTo(methodOn(CursoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(CursoController.class).getAll(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(UsuarioController.class).findById(this.getUsuario().getId())).withRel("usuario")
        );
    }

}
