package br.com.fiap.profit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Data //getter, setter, constructor, to string, equalsAndHashCode
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
    @Size(min = 50)
    private String descricao;

    @NotNull
    private String link;

    @ManyToOne
    private Usuario usuario;

}
