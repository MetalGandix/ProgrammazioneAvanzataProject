package it.unicam.cs.pa.jbudget097670;

import java.util.Collection;

public interface Budget {
	
	public Collection<Movimento> getMovimentiperCategoria(Categoria c);

	Collection<Movimento> getMovimentiPerData(java.util.Date dataInizio, java.util.Date dataFine);

}
