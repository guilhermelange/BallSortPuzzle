package br.com.plugins;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


/**
 *   Algoritmos de Busca (geral, qquer problema)
 *   Busca a solu��o por busca em profundidade.
 *
 *   @author Jomi Fred H�bner
 *  
 *  Tratamento com Tipos genericos adicionados por Adilson Vahldick.
 *  
 */
public class BuscaProfundidade<E extends Estado> extends Busca<E> {
    private static HashSet<Estado> logs = new HashSet();
    private static long count = 0;
    protected int profMax = 40;

    /** busca sem mostrar status */
    public BuscaProfundidade() {
    }
    public BuscaProfundidade(int m) {
        profMax = m;
    }
    
    /** busca mostrando status */
    public BuscaProfundidade(MostraStatusConsole ms) {
        super(ms);
    }
    public BuscaProfundidade(int m, MostraStatusConsole ms) {
        super(ms);
        profMax = m;
    }
    
	
	public void setProfMax(int m) {
		profMax = m;
	}
	
    public Nodo busca(Estado inicial) {
        status.inicia();
        initFechados();
        
        List<Nodo> abertos = new LinkedList<Nodo>();
        
        abertos.add(new Nodo(inicial, null));
        
        while (!parar && abertos.size() > 0) {
            
            Nodo n = abertos.remove(0);
            Estado estado = n.estado;
            
            if (logs.contains(estado)) {
                continue;
            }
            
            System.out.println("Possibilidades: " + (++count));
            status.explorando(n,abertos.size());
            if (n.estado.ehMeta()) {
                status.termina(true);
                return n;
            }
        
            if (n.getProfundidade() < profMax) {
                logs.add(estado);
                abertos.addAll( 0, sucessores(n) );
            }            
        }
        status.termina(false);
        return null;
    }        
    
    public String toString() {
    	return "BP - Busca em Profundidade";
    }
}
