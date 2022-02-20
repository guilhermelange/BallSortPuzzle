package br.com.plugins;

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
    private static HashSet<Estado> logs = new HashSet();
    private static long count = 0;
    /** busca sem mostrar status */
    public BuscaLargura() {
    }
    
    /** busca mostrando status */
    public BuscaLargura(MostraStatusConsole ms) {
        super(ms);
    }

    public Nodo busca(E inicial) {
        status.inicia();
        initFechados();
        Queue<Nodo> abertos = new PriorityQueue<Nodo>();
        Nodo nodoInicial = new Nodo(inicial, null);
        abertos.add(nodoInicial);
        
        while (!parar && abertos.size() > 0) {
            Nodo n = abertos.remove();
            Estado estado = n.estado;
            
            if (logs.contains(estado)) {
                continue;
            }
            
            System.out.println("Possibilidades: " + (++count));
            status.explorando(n, abertos.size());

            if (n.estado.ehMeta()) {
                status.termina(true);
                return n;
            }

            logs.add(estado);
            abertos.addAll(sucessores(n));   
        }
        status.termina(false);
        return null;
    }
    
    public String toString() {
    	return "BL - Busca em Largura";
    }
}
