package mapa.controls;

import java.util.List;
import mapa.dialogs.Mensagem;
import mapa.entities.Hospede;
import mapa.exceptions.ValorIncorretoException;

public class HospedeImpl implements HospedeDAO {
    
     //private static Set<Hospede> hospedes = new HashSet<>();

    @Override
    public void verificarCodigo(int codigo) {
        if (codigo <= 0) {
            throw new ValorIncorretoException(msgErro());
        }
    }

    @Override
    public void verificarNome(String nome) {
        if (nome.isBlank()) {
            throw new ValorIncorretoException(Mensagem.erroValorVazio());
        }
    }

    @Override
    public void verificarIdade(int idade) {
        if (idade <= 0) {
            throw new ValorIncorretoException(msgErro());
        }
    }

    @Override
    public void mostrarHospedes(List<Hospede> lista) {
        System.out.println("\n Lista de hóspedes: ");
        System.out.println("\n ------------------ ");
        for (Hospede h : lista) {
            System.out.println(h.toString());
        }
    }

    public String msgErro() {
        return "\n** Erro! Valor inválido. Tente novamente ** \n";
    }

    
}
