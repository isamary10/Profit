package br.com.fiap.profit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Simulador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 100, message = "Deve ser igual ou maior que 100")
    @NotNull
    private double valor;

    private double aporte;

    @NotNull
    private String tipoInvest;

    @NotNull
    private double juros;

    @NotNull
    private int tempoInvest;

    private double rendimento;

    @ManyToOne
    private Usuario usuario;

    public double calcularInvestimento(double valor, double aporte, double juros, int meses){
        this.rendimento = valor;

        for(int i = 0; i < meses; i++){
            this.rendimento += aporte;
            this.rendimento *= 1 + (juros / 100);
        }

        return this.rendimento;
    }


}
