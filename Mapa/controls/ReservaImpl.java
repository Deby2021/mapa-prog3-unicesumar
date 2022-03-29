package mapa.controls;

import java.util.List;
import mapa.dialogs.Mensagem;
import mapa.entities.Hospede;
import mapa.entities.Reserva;
import mapa.entities.Suite;
import mapa.exceptions.ElementoNaoEncontradoException;
import mapa.exceptions.ReservaInvalidaException;

public class ReservaImpl implements ReservaDAO {

    @Override
    public Suite procurarSuite(List<Reserva> lista, int codigo) {
        Suite s = null;
        for (Reserva r : lista) {
            if (r.getSuite().getNumero() == codigo) {
                s = r.getSuite();
            }
        }
        if (s != null) {
            return s;
        } else {
            throw new ElementoNaoEncontradoException(Mensagem.erroNaoEncontrada());
        }
    }

   
     
    
    @Override
    public boolean verificarCapacidade(List<Hospede> lista, int capacidade) {
        if (hospedesAcimaDoisAnos(lista) <= capacidade) {
            return true;
        } else {
            throw new ReservaInvalidaException(Mensagem.erroCapacidade());
        }
    }
    
     public int hospedesAcimaDoisAnos(List<Hospede> lista) {
        int total = 0;
        for (Hospede h : lista) {
            if (h.getIdade() > 2) {
                total++;
            }
        }
        return total;
    }
    
     @Override
     public void qtH2Anos(List<Hospede> lista){
         int total = 0;
         for (Hospede h : lista) {
            if (h.getIdade() > 2) {
               total++;
            }
           
        }         
        System.out.println("Total: " + total);
    }
    /*@Override
    public boolean verificarCapacidade(List<Hospede> hospedes, int capacidade){
        Reserva r = null;
        hospedes.stream().filter(h -> h.getIdade() > 2);
        capacidade = hospedes.size();
        return (r.getQtdDePessoas()  > capacidade) ? true : false;
    } */

    @Override
    public void adicionarReserva(List<Reserva> lista, Reserva reserva) {
        lista.add(reserva);
    }

    @Override
    public boolean procurarReserva(List<Reserva> lista, int numSuite) {
        boolean achou = false;
        for (Reserva r : lista) {
            if (r.getSuite().getNumero() == numSuite) {
                achou = true;
            }
        }
        return achou;
    }

    @Override
    public boolean procurarHospede(List<Reserva> lista, String nome) {
        boolean achou = false;
        for (Reserva r : lista) {
            for (Hospede h : r.getHospedes()) {
                if (h.getNome().equalsIgnoreCase(nome)) {
                    System.out.print("\n Nome do hóspede: " + h.getNome());
                    System.out.println("\n Hospedado na suíte #" + r.getSuite().getNumero() + "\n");
                    achou = true;
                }
            }
        }
        return achou;
    }

    @Override
    public void mostrarHospedeSuite(List<Reserva> lista, int suite) {
        List<Hospede> hospedes = null;
        if (procurarReserva(lista, suite)) {
            System.out.println();
            for (Reserva r : lista) {
                if (r.getSuite().getNumero().equals(suite)) {
                    for (Hospede h : r.getHospedes()) {
                        System.out.println(h.toString());
                    }
                }
            }
        } else {
            throw new ElementoNaoEncontradoException(Mensagem.erroNaoEncontrada());
        }
    }

    @Override
    public void mostrarReserva(Reserva r) {
        List<Hospede> hospedes = r.getHospedes();

        System.out.println("\nDADOS DA RESERVA:");
        System.out.println("----------------");
        System.out.println(r.getSuite().toString());

        for (Hospede h : hospedes) {
            System.out.println(h.toString());
        }

        double valorDiaria = r.getSuite().getValorDiaria();
        int diarias = r.getQtdDias();
        System.out.println("\nCÁLCULO DAS DIÁRIAS:");
        System.out.println("-------------------");
        System.out.println("  VALOR DA DIÁRIA  : R$ " + valorDiaria);
        System.out.println("  TOTAL DE DIÁRIAS : " + diarias);
        System.out.println("  TOTAL            : R$ " + calcularDiaria(r.getSuite(), diarias));
        System.out.println("  TOTAL DE DESCONTO: R$ " + 
                ((r.getSuite().getValorDiaria()* diarias) - calcularDiaria(r.getSuite(), diarias)));
        System.out.println("\n");

    }

    @Override
    public void listarTodasAsReservas(List<Reserva> listaDeTodasAsReservas) {
        int cont = 0;
        for (Reserva r : listaDeTodasAsReservas) {
            System.out.println("\nINFORMAÇÕES DA RESERVA #" + (cont + 1) + ":");
            mostrarReserva(r);
            cont++;
        }
        System.out.println("---------------------------");
        System.out.println(" VALOR DE TODAS AS DIÁRIAS: R$ " + calcularTotalDeDiarias(listaDeTodasAsReservas) + "\n\n");
    }

    @Override
    public boolean verificarReservasVazias(List<Reserva> reservas) {
        return (reservas.isEmpty());
    }

    @Override
    public double calcularDiaria(Suite s, int diarias) {
        double desconto = 1;
        double taxa = 0.1;
        if (diarias > 7) {
            desconto -= taxa;
        }
        return (desconto * (s.getValorDiaria() * diarias));
    }

    @Override
    public double calcularTotalDeDiarias(List<Reserva> lista) {
        double total = 0.0;

        for (Reserva r : lista) {
            total += (calcularDiaria(r.getSuite(), r.getQtdDias()));
        }
        return total;
    }

    @Override
    public Reserva getReserva(List<Reserva> lista, int suite) {
        Reserva r = null;
        for (Reserva reserva : lista) {
            if (reserva.getSuite().getNumero().equals(suite)) {
                r = reserva;
            }
        }
        return r;
    }
    
   


    @Override
    public void liberarSuite(List<Reserva> lista, int inteiro) {
        Reserva r = getReserva(lista, inteiro);
        if (r != null) {
            lista.remove(r);
        }
    }

   

    @Override
    public boolean verificarCapacidadeIdade(List<Hospede> hospedes, Suite s){
        boolean cap;
        int total = 0;
        for (Hospede h : hospedes) {
            if (h.getIdade() > 2) {
                total++;
            }
        }
        if (total <= s.getCapacidade()){
              s.setCapacidade(total);
              cap = true;
        }else{
            cap = false;
            throw new ReservaInvalidaException(Mensagem.erroCapacidade());
            }
        return cap;
    }
}
