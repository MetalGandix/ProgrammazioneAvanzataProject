package it.unicam.cs.pa.jbudget097670.interfaces;

import java.util.Collection;

import it.unicam.cs.pa.jbudget097670.model.Categoria;
import it.unicam.cs.pa.jbudget097670.model.Movimento;

/**
 * @author Leonardo Mogianesi
 * In questa interfaccia dichiaro dei metodi che serviranno per raggruppare i movimenti
 *
 */
public interface Budget {
	
	/**
	 * @param c
	 * @return una lista di Movimenti con la stessa categoria
	 */
	public Collection<Movimento> getMovimentiperCategoria(Categoria c);

	/**
	 * @param dataInizio
	 * @param dataFine
	 * @return una lista di Movimenti con le stesse date
	 */
	Collection<Movimento> getMovimentiPerData(java.util.Date dataInizio, java.util.Date dataFine);

}
