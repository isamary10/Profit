package br.com.fiap.profit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Simulador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valor;
    private double aporte;
    private String tipoInvest;
    private double juros;
    private int tempoInvest;

    public Simulador(){};

    public Simulador(Long id, double valor, double aporte, String tipoInvest, double juros, int tempoInvest) {
        this.id = id;
        this.valor = valor;
        this.aporte = aporte;
        this.tipoInvest = tipoInvest;
        this.juros = juros;
        this.tempoInvest = tempoInvest;
    }

    public double calcularInvestimento(double valor, double aporte, double juros, int meses){
        double valorTotal = valor;

        for(int i = 0; i < meses; i++){
            valorTotal += aporte;
            valorTotal *= 1 + (juros / 100);
        }

        return valorTotal;
    }

    @Override
    public String toString() {
        return "Simulador [id=" + id + ", valor=" + valor + ", aporte=" + aporte + ", tipoInvest=" + tipoInvest
                + ", juros=" + juros + ", tempoInvest=" + tempoInvest + "]";
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

  
}
