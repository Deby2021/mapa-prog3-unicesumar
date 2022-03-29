package mapa.application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import mapa.controls.HospedeDAO;
import mapa.controls.HospedeImpl;
import mapa.controls.ReservaDAO;
import mapa.controls.ReservaImpl;
import mapa.controls.SuiteDAO;
import mapa.controls.SuiteImpl;
import mapa.dialogs.Mensagem;
import mapa.entities.Hospede;
import mapa.entities.Reserva;
import mapa.entities.Suite;
import mapa.exceptions.ElementoNaoEncontradoException;
import mapa.exceptions.ReservaInvalidaException;
import mapa.exceptions.ValorIncorretoException;

public class MAPA3_Hotel {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        
        ReservaDAO reservaDAO = new ReservaImpl();
        HospedeDAO hospedeDAO = new HospedeImpl();
        SuiteDAO suiteDAO = new SuiteImpl();
        
        List<Suite> listaSuite = new ArrayList<>();
        //s = new Suite(numeroDaSuite, tipo, capacidade, valorDaDiaria);
        Suite sMaster= new Suite(1, "Master", 10, 180.00);
        Suite sEstrela =  new Suite(2, "Estrela", 15, 280.00);
        Suite sSimples =  new Suite(3, "Simples", 1, 130.00);
        Suite sLuxo =  new Suite(4, "Luxo", 20, 400.00);
        listaSuite.add(sMaster);
        listaSuite.add(sEstrela);
        listaSuite.add(sSimples);
        listaSuite.add(sLuxo);
        

        List<Reserva> listaDeReservas = new ArrayList<>();
        int opcao;

        do {
            // Receber opção do menu
            do {
                try {
                    Mensagem.mostrarMenu();
                    opcao = Mensagem.inserirInteiro("");
                    if (opcao < 0 || opcao > 6) {
                        throw new ValorIncorretoException(Mensagem.erroNumerico());
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(Mensagem.erroNumerico());
                } catch (ValorIncorretoException e) {
                    System.out.println(e.getMessage());
                }
            } while (true);
            // ---------------------

            switch (opcao) {
                // 1 - RESERVAR SUITE
                case 1 -> {
                    List<Hospede> listaDeHospedes = new ArrayList<>();
                    Reserva r;
                    Suite s = null;
                    int capacidade = 0;
                    int qtdDeDiarias = 0;
                    int numeroDeHospedes = 0;                      
                    do{
                        try {
                            numeroDeHospedes = Mensagem.inserirInteiro("\nQuantidade de hóspedes: ");
                            if (numeroDeHospedes <= 0) {
                                throw new ValorIncorretoException(Mensagem.erroNumerico());
                            }
                            break;
                        } catch (ValorIncorretoException | InputMismatchException e) {
                            System.out.println(Mensagem.erroNumerico());
                        }
                    } while (true);

                    Mensagem.cadastrarHospedes();
                    for (int i = 0; i < numeroDeHospedes; i++) {
                
                        Hospede h = new Hospede();
                        System.out.printf("Código: " + i);
                        h.setCodigo(Mensagem.lerInteiro(i));
                        h.setNome(Mensagem.inserirTexto("\nNome do Hóspede: "));
                        h.setEndereco(Mensagem.inserirTexto("Endereço: "));
                        h.setIdade(Mensagem.inserirInteiro("Idade: "));
                        listaDeHospedes.add(h);
                }   
                    
                    do {
                            try {
                                
                                System.out.println("\n");
                                suiteDAO.listarSuitesDisponiveis(listaSuite);
                                 
                                do{
                                    try{
                                     opcao = Mensagem.inserirInteiro("Que suite deseja ? \n> ");
                                    switch (opcao){
                                case 1 -> s = sMaster;
                                case 2 -> s = sEstrela;
                                case 3 -> s = sSimples;
                                case 4 -> s = sLuxo;
                                //default -> System.out.println(Mensagem.suiteNaoCadastrada());
                                
                                }
                                      
                                      if (opcao > listaSuite.size()) {
                                         throw new ReservaInvalidaException(Mensagem.suiteNaoCadastrada());
                                        }break;
                                         } catch (InputMismatchException e) {
                                                 System.out.println(Mensagem.suiteNaoCadastrada());
                                        }   
                                }while(true);                                    
                                
                                reservaDAO.qtH2Anos(listaDeHospedes);
                                capacidade = s.getCapacidade();
                                System.out.println("A capacidade é: " + capacidade);
                                    if (!reservaDAO.verificarCapacidade(listaDeHospedes, capacidade)) {
                                        throw new ReservaInvalidaException(Mensagem.erroCapacidade());
                                    }
                                break;
                            } catch (ValorIncorretoException | InputMismatchException e) {
                                System.out.println(Mensagem.erroNumerico());
                            }catch(ReservaInvalidaException e){
                                System.out.println(e.getMessage());
                            }
                        } while (true);
                                                         
                    

                    // Quantidade de diárias
                    do {
                        try {
                            qtdDeDiarias = Mensagem.inserirInteiro("Quantidade de diárias: ");
                            suiteDAO.verificarNumero(qtdDeDiarias);
                            break;
                        } catch (ValorIncorretoException | InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    } while (true);

                    // Instanciando uma reserva
                    r = new Reserva( s, numeroDeHospedes, qtdDeDiarias, listaDeHospedes);

                    // Adicionar a reserva à lista
                    reservaDAO.adicionarReserva(listaDeReservas, r);

                    // Mostrar reserva                    
                    Mensagem.sucessoReserva();
                    reservaDAO.mostrarReserva(r);
                    
                }
                      

                // 2 - Listar reservas
                case 2 -> {
                    try {
                        Mensagem.mostrarReservas();
                        if (reservaDAO.verificarReservasVazias(listaDeReservas)) {
                            throw new ElementoNaoEncontradoException(Mensagem.erroNaoEncontrada());
                        } else {
                            reservaDAO.listarTodasAsReservas(listaDeReservas);
                        }
                    } catch (ElementoNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                }

                // 3 - Localizar suite
                case 3 -> {
                    Mensagem.mostrarSuite();
                    int numeroSuite;
                    do {
                        try {
                            numeroSuite = Mensagem.inserirInteiro("Que suite deseja procurar ? \n> ");
                            suiteDAO.verificarNumero(numeroSuite);
                            break;
                        } catch (ValorIncorretoException | InputMismatchException e) {
                            System.out.println(Mensagem.erroNumerico());
                        }
                    } while (true);
                    try {
                        // Se não houver reservas cadastradas
                        if (reservaDAO.verificarReservasVazias(listaDeReservas)) {
                            throw new ElementoNaoEncontradoException(Mensagem.erroNaoEncontrada());
                        } else {
                            try {
                                // Se houver a suite, exibe, senão, lança erro
                                Suite s = reservaDAO.procurarSuite(listaDeReservas, numeroSuite);
                                reservaDAO.mostrarHospedeSuite(listaDeReservas, s.getNumero());
                                break;
                            } catch (ElementoNaoEncontradoException e) {
                                System.out.println(Mensagem.erroNaoEncontrada());
                            }
                        }
                    } catch (ElementoNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }

                }
                case 4 -> {
                    Mensagem.mostrarHospede();
                    String nomeDoHospede;
                    do {
                        try {
                            nomeDoHospede = Mensagem.inserirTexto("Nome do hóspede que deseja procurar: ");
                            hospedeDAO.verificarNome(nomeDoHospede);
                            break;
                        } catch (ValorIncorretoException e) {
                            System.out.println(Mensagem.erroValorVazio());
                        }
                    } while (true);
                    try {
                        // Se não houver reservas cadastradas
                        if (reservaDAO.verificarReservasVazias(listaDeReservas)) {
                            throw new ElementoNaoEncontradoException(Mensagem.erroNaoEncontrada());
                        } else {
                            try {
                                if (reservaDAO.procurarHospede(listaDeReservas, nomeDoHospede)) {
                                    break;
                                } else {
                                    throw new ElementoNaoEncontradoException(Mensagem.erroNaoEncontrada());
                                }
                            } catch (ElementoNaoEncontradoException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    } catch (ElementoNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                }

                // Liberar suite
                case 5 -> {
                    Mensagem.liberarSuite();
                    int numeroDaSuite;
                    do {
                        try {
                            numeroDaSuite = Mensagem.inserirInteiro("Número da suite a ser liberada: ");
                            suiteDAO.verificarNumero(numeroDaSuite);
                            break;
                        } catch (ValorIncorretoException | InputMismatchException e) {
                            System.out.println(Mensagem.erroNumerico());
                        }
                    } while (true);
                    try {
                        if (reservaDAO.verificarReservasVazias(listaDeReservas)) {
                            throw new ReservaInvalidaException(Mensagem.erroNaoEncontrada());
                        } else {
                            if (reservaDAO.getReserva(listaDeReservas, numeroDaSuite) != null) {
                                reservaDAO.liberarSuite(listaDeReservas, numeroDaSuite);
                                Mensagem.sucessoLiberarReserva();
                            } else {
                                throw new ElementoNaoEncontradoException(Mensagem.erroNaoEncontrada());
                            }
                        }
                    } catch (ReservaInvalidaException | ElementoNaoEncontradoException | IncompatibleClassChangeError e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } while (opcao != 0);
    }
    
}