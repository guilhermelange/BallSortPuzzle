package br.com.plugins;

/**
 * Interface para estados que tem a fun��o antecessores.
 *
 * @author  jomi
 * 
 */
public interface Antecessor {
    public <E extends Estado> java.util.List<E> antecessores();
}
