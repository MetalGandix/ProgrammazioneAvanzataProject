package it.unicam.cs.pa.jbudget097670.controller;

import java.util.Collection;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Categoria;
import it.unicam.cs.pa.jbudget097670.model.Movimento;
import it.unicam.cs.pa.jbudget097670.model.OperazioniPianoInterface.Type;
import it.unicam.cs.pa.jbudget097670.model.Piano;

/**
 * @author User
 *
 */
public interface OggettiControllerInterface {

	public Collection<Movimento> getAllMovement(Asset asset) throws Exception;

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
	public Movimento deleteMovimentoPerId(Asset asset, int x) throws Exception;
	
	/**
	 * @param asset
	 * @param x 
	 * @return ritorner� la lista dei Piani dove � stato rimosso il Piano con l'ID selezionato
	 * @throws Exception 
	 */
	public Piano deletePianoPerId(Asset asset, int x) throws Exception;
	
	/**
	 * @param asset1
	 * @param asset2
	 * @param importo
	 * @param cat
	 * @return ritorner� l'asset della carta di credito aggiornato con la spesa
	 */
	public Asset aggiornaCartaDiCreditoSpesa(Asset asset1, Asset asset2, double importo, Categoria cat); 
	
	/**
	 * @param asset1
	 * @param asset2
	 * @param importo
	 * @param cat
	 * @return ritorner� l'asset della carta di credito aggiornato col ricavo
	 */
	public Asset aggiornaCartaDiCreditoRicavo(Asset asset1, Asset asset2, double importo, Categoria cat);
	
	/**
	 * @param asset1
	 * @param asset2
	 * @param importo
	 * @param cat
	 * @return ritorner� l'asset del conto corrente aggiornato
	 */
	public Asset aggiornaContoCorrente(Asset asset1, Asset asset2, double importo, Categoria cat);

	/**
	 * @param asset
	 * @param tipo
	 * @param importoPiano
	 * @param importo
	 * @param durataPiano
	 * @return ritorner� l'asset del conto corrente con il piano aggiornato
	 */
	public Asset aggiornaPiano(Asset asset, Type tipo, double importoPiano, double importo, int durataPiano);
	
	
	
	}