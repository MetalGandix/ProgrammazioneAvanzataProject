package it.unicam.cs.pa.jbudget097670.controller;

import java.util.Collection;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Movimento;

public interface GetOggetti {
	
	/**
	 * @param asset
	 * @return ritorna un Movimento con quel determinato ID
	 */
	public Collection<Movimento> getMovimentoPerId(Asset asset);
	
	public Asset getPianoPerId(Asset a);
	
	public Asset getMovimentoPerGiorno(Asset a);
	
	/**
	 * @param c
	 * @return una lista di Movimenti con la stessa categoria
	 */
	public Collection<Movimento> getMovimentiperCategoria(Asset asset);

	

	
}