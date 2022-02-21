package br.com.plugins;

import java.util.Date;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * Busca a solu��o por busca em largura.
 *
 *  @author Jomi Fred H�bner
 *  
 *  Tratamento com Tipos genericos adicionados por Adilson Vahldick.
 *  
 */
public class BuscaLargura<E extends Estado> extends Busca<E> {
    private long segundosExecucao;
    /** busca sem mostrar status */
    public BuscaLargura() {
    }
    
    /** busca mostrando status */
    public BuscaLargura(MostraStatusConsole ms) {
        super(ms);
        MostraStatusConsole mostraStatus = ms;
    }

    public Nodo busca(E inicial) {
        status.inicia();
        initFechados();
        Queue<Nodo> abertos = new PriorityQueue<Nodo>();
        Nodo nodoInicial = new Nodo(inicial, null);
        abertos.add(nodoInicial);
        Date dataInicial = new Date();
        
        while (!parar && abertos.size() > 0) {
            Nodo n = abertos.remove();
            
            long seconds = Math.abs((new Date()).getTime() - dataInicial.getTime())/1000;
            if (seconds > segundosExecucao) {
                segundosExecucao = seconds;
                gravarLog("Em andamento");
            }
            
            status.explorando(n, abertos.size());

            if (n.estado.ehMeta()) {
                status.termina(true);
                return n;
            }

            abertos.addAll(sucessores(n));
        }
        status.termina(false);
        
        return null;
    }

    private void gravarLog(String statusGeral) {
        long tempoDecorrido = status.getTempoDecorrido()/1000;
        System.out.println(String.format("Status: (%s)\n  Tempo(segundos): %d\n  Profundidade: %d\n  Qtd. Visitados: %d\n", statusGeral, tempoDecorrido, status.getProfundidade(), status.getVisitados()));
    }
    
    public String toString() {
    	return "BL - Busca em Largura";
    }
}
