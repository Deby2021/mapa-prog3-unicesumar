package mapa.controls;

import java.util.List;
import mapa.dialogs.Mensagem;
import mapa.entities.Suite;
import mapa.exceptions.ValorIncorretoException;

public class SuiteImpl implements SuiteDAO {

    @Override
    public void verificarNumero(int numero) {
        if (numero <= 0) {
            throw new ValorIncorretoException(Mensagem.erroNumerico());
        }
    }

    @Override
    public void verificarCapacidade(int capacidade) {
        if (capacidade <= 0) {
            throw new ValorIncorretoException(Mensagem.erroNumerico());
        }
    }

    @Override
    public void verificarValor(double valor) {
        if (valor <= 0.0) {
            throw new ValorIncorretoException(Mensagem.erroNumerico());
        }
    }

    @Override
    public void listarTodasAsSuites(List<Suite> listaDeTodasAsSuites) {
         for (Suite s : listaDeTodasAsSuites ) {
            System.out.println(s.toString());   
         }
    }
    
    @Override
    public void listarSuitesDisponiveis(List<Suite> lista){
        for(Suite s: lista){
            System.out.println(s.getNumero() + s.getTipo());
        }
    }

    
}

