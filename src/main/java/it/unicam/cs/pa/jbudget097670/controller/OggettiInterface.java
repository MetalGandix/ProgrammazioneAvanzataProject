package it.unicam.cs.pa.jbudget097670.controller;

import java.util.Collection;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Movimento;
import it.unicam.cs.pa.jbudget097670.model.OperazioniPiano.Type;
import it.unicam.cs.pa.jbudget097670.model.Piano;

public interface OggettiInterface {
	/**
	 * @param asset
	 * @param x 
	 * @return ritorner� un Movimento con quel determinato ID
	 * @throws Exception 
	 */
	public Collection<Movimento> getMovimentoPerId(Asset asset, int x) throws Exception;
	
	/**
	 * @param a
	 * @return ritorner� un piano con l'id scelto dall'utente
	 * @throws Exception 
	 */
	public Collection<Piano> getPianoPerId(Asset asset, int x) throws Exception;
	
	/**
	 * @param c
	 * @return ritorner� una lista di Movimenti con la stessa categoria
	 * @throws Exception 
	 */
	public Collection<Movimento> getMovimentiperCategoria(Asset asset, String x) throws Exception;
	
	/**
	 * @param asset
	 * @param x 
	 * @return ritorner� una lista di Piani dello stesso tipo (Ammortamento o Investimento)
	 * @throws Exception 
	 */
	public Collection<Piano> getPianiPerTipo(Asset asset, Type x) throws Exception;	
	
	/**
	 * @param asset
	 * @return ritorner� la lista dei Movimenti dove � stato rimosso il Movimento con l'ID selezionato
	 * @throws Exception 
	 */
	public void deleteMovimentoPerId(Asset asset, int x) throws Exception;
	
	/**
	 * @param asset
	 * @param x 
	 * @return ritorner� la lista dei Piani dove � stato rimosso il Piano con l'ID selezionato
	 * @throws Exception 
	 */
	public void deletePianoPerId(Asset asset, int x) throws Exception;
	
	}