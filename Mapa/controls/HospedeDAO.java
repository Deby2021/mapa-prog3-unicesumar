package mapa.controls;

import java.util.List;
import mapa.entities.Hospede;
import mapa.exceptions.ElementoNaoEncontradoException;
import mapa.exceptions.ValorIncorretoException;

public interface HospedeDAO {

    void verificarCodigo(int codigo) throws ValorIncorretoException;

    void verificarNome(String nome) throws ValorIncorretoException;

    void verificarIdade(int idade) throws ValorIncorretoException;

    void mostrarHospedes(List<Hospede> lista) throws ElementoNaoEncontradoException;
    
   
}
