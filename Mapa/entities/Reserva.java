package mapa.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reserva {

    private List<Hospede> hospedes = new ArrayList<>();
    private Suite suite;
    private Integer qtdDePessoas;
    private Integer qtdDias;

    public Reserva() {
    }

    public Reserva(Suite suite, Integer qtdDePessoas, Integer qtdDias, List<Hospede> hospedes) {
        this.suite = suite;
        this.qtdDePessoas = qtdDePessoas;
        this.qtdDias = qtdDias;
        this.hospedes = hospedes;
    }

    public Suite getSuite() {
        return suite;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }

    public Integer getQtdDePessoas() {
        return qtdDePessoas;
    }

    public void setQtdDePessoas(Integer qtdDePessoas) {
        this.qtdDePessoas = qtdDePessoas;
    }

    public Integer getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Reserva other = (Reserva) obj;
        return Objects.equals(this.suite, other.suite);
    }

    @Override
    public String toString() {
        return "Hóspedes: " + hospedes
                + "\nSuite=" + suite
                + "\n Número de hóspedes: " + qtdDePessoas
                + "\nDiárias: " + qtdDias
                + '\n';
    }
}
