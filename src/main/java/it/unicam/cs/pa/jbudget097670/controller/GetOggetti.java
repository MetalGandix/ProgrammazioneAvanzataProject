package it.unicam.cs.pa.jbudget097670.controller;

import java.util.Collection;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Movimento;
import it.unicam.cs.pa.jbudget097670.model.Piano;

public interface GetOggetti {
	/**
	 * @param asset
	 * @return ritornerà un Movimento con quel determinato ID
	 * @throws Exception 
	 */
	public Collection<Movimento> getMovimentoPerId(Asset asset) throws Exception;
	
	/**
	 * @param a
	 * @return ritornerà un piano con l'id scelto dall'utente
	 * @throws Exception 
	 */
	public Collection<Piano> getPianoPerId(Asset asset) throws Exception;
	
	/**
	 * @param c
	 * @return ritornerà una lista di Movimenti con la stessa categoria
	 */
	public Collection<Movimento> getMovimentiperCategoria(Asset asset);
	
	/**
	 * @param asset
	 * @return ritornerà una lista di Piani dello stesso tipo (Ammortamento o Investimento)
	 */
	public Collection<Piano> getPianiPerTipo(Asset asset);	
	
	/**
	 * @param asset
	 * @return ritornerà la lista dei Movimenti dove è stato rimosso il Movimento con l'ID selezionato
	 */
	public Collection<Movimento> deleteMovimentoPerId(Asset asset);
	
	/**
	 * @param asset
	 * @return ritornerà la lista dei Piani dove è stato rimosso il Piano con l'ID selezionato
	 */
	public Collection<Piano> deletePianoPerId(Asset asset);
	}