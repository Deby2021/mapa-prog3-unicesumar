package mapa.controls;

import java.util.List;
import mapa.entities.Suite;
import mapa.exceptions.ElementoNaoEncontradoException;
import mapa.exceptions.ValorIncorretoException;

public interface SuiteDAO {

    void verificarNumero(int numero) throws ValorIncorretoException;

    void verificarCapacidade(int capacidade) throws ValorIncorretoException;

    void verificarValor(double valor) throws ValorIncorretoException;
    
    void listarTodasAsSuites(List<Suite> listaDeTodasAsSuites);
    
    void listarSuitesDisponiveis(List<Suite> lista);

}
