package it.unicam.cs.pa.jbudget097670.interfaces;

import java.util.Collection;

import it.unicam.cs.pa.jbudget097670.model.Categoria;
import it.unicam.cs.pa.jbudget097670.model.Movimento;

public interface Budget {
	
	public Collection<Movimento> getMovimentiperCategoria(Categoria c);

	Collection<Movimento> getMovimentiPerData(java.util.Date dataInizio, java.util.Date dataFine);

}
