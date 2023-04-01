package br.com.fiap.profit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
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

    protected Simulador(){};

    public Simulador(Long id, double valor, double aporte, String tipoInvest, double juros, int tempoInvest,
            double rendimento) {
        this.id = id;
        this.valor = valor;
        this.aporte = aporte;
        this.tipoInvest = tipoInvest;
        this.juros = juros;
        this.tempoInvest = tempoInvest;
        this.rendimento = rendimento;
    }

    public double calcularInvestimento(double valor, double aporte, double juros, int meses){
        this.rendimento = valor;

        for(int i = 0; i < meses; i++){
            rendimento += aporte;
            rendimento *= 1 + (juros / 100);
        }

        return rendimento;
    }

    @Override
    public String toString() {
        return "Simulador [id=" + id + ", valor=" + valor + ", aporte=" + aporte + ", tipoInvest=" + tipoInvest
                + ", juros=" + juros + ", tempoInvest=" + tempoInvest + ", rendimento=" + rendimento + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getAporte() {
        return aporte;
    }

    public void setAporte(double aporte) {
        this.aporte = aporte;
    }

    public String getTipoInvest() {
        return tipoInvest;
    }

    public void setTipoInvest(String tipoInvest) {
        this.tipoInvest = tipoInvest;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public int getTempoInvest() {
        return tempoInvest;
    }

    public void setTempoInvest(int tempoInvest) {
        this.tempoInvest = tempoInvest;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }

}
