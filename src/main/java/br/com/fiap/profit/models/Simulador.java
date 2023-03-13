package br.com.fiap.profit.models;

public class Simulador {

    private double valor;
    private double aporte;
    private String tipoInvest;
    private double juros;
    private int tempoInvest;

    public Simulador(double valor, double aporte, String tipoInvest, double juros, int tempoInvest) {
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
        return "Simulador [valor=" + valor + ", aporte=" + aporte + ", tipoInvest=" + tipoInvest + ", juros=" + juros
                + ", tempoInvest=" + tempoInvest + "]";
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
