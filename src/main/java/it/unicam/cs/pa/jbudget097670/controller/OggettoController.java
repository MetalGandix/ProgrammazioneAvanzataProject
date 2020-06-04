package it.unicam.cs.pa.jbudget097670.controller;

import java.util.Collection;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Categoria;
import it.unicam.cs.pa.jbudget097670.model.Movimento;

public interface OggettoController {
	
	Collection<Movimento> getMovimentoPerId(Asset a, Movimento m);
	
	Asset getPianoPerId(Asset a);
	
	Asset getMovimentoPerGiorno(Asset a);
	
	/**
	 * @param c
	 * @return una lista di Movimenti con la stessa categoria
	 */
	Collection<Movimento> getMovimentiperCategoria(Categoria c);
}