package mapa.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Suite {

    private List<Suite> suites = new ArrayList<>();
    private Integer numero;
    private String tipo;
    private Integer capacidade;
    private Double valorDiaria;

    public Suite() {
    }

    public Suite(Integer numero, String tipo, Integer capacidade, Double valorDiaria) {
        this.numero = numero;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.valorDiaria = valorDiaria;
    }

    
    public List<Suite> getSuites() {
        return suites;
    }
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Suite other = (Suite) obj;
        return Objects.equals(this.numero, other.numero);
    }

    @Override
    public String toString() {
        return "  SUITE #         : " + numero
                + "\n  TIPO            : " + tipo
                + "\n  CAPACIDADE      : " + capacidade
                + "\n  VALOR DA DIÁRIA : " + valorDiaria
                + '\n';
    }
}