package it.unicam.cs.pa.jbudget097670.controller;

import java.util.Collection;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Categoria;
import it.unicam.cs.pa.jbudget097670.model.Movimento;

public interface OggettoController {
	
	Asset getMovimentoPerId();
	
	Asset getPianoPerId();
	
	Asset getMovimentoPerGiorno();
	
	/**
	 * @param c
	 * @return una lista di Movimenti con la stessa categoria
	 */
	Collection<Movimento> getMovimentiperCategoria(Categoria c);
}