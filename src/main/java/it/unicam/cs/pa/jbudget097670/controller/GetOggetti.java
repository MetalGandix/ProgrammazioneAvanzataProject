package it.unicam.cs.pa.jbudget097670.controller;

import java.util.Collection;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Movimento;
import it.unicam.cs.pa.jbudget097670.model.Piano;

public interface GetOggetti {
	
	/**
	 * @param asset
	 * @return ritornerÓ un Movimento con quel determinato ID
	 */
	public Collection<Movimento> getMovimentoPerId(Asset asset);
	
	/**
	 * @param a
	 * @return ritornerÓ un piano con l'id scelto dall'utente
	 */
	public Collection<Piano> getPianoPerId(Asset asset);
	
	/**
	 * @param c
	 * @return ritornerÓ una lista di Movimenti con la stessa categoria
	 */
	public Collection<Movimento> getMovimentiperCategoria(Asset asset);
	
	/**
	 * @param asset
	 * @return ritornerÓ una lista di Piani dello stesso tipo (Ammortamento o Investimento)
	 */
	public Collection<Piano> getPianiPerTipo(Asset asset);

	

	
}