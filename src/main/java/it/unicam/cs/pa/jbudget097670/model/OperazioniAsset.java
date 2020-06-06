package it.unicam.cs.pa.jbudget097670.model;

import java.util.ArrayList;

/**
 * @author Leonardo Mogianesi
 *
 */
public interface OperazioniAsset {

	/**
	 * Questo metodo permetter� di depositare denaro all'interno dei conti
	 * @return 
	 */
	public Movimento deposita(double importo, Categoria cat);
	
	/**
	 * @return 
	 * @double importo
	 * Questo metodo permetter� di prelevare denaro all'interno dei conti
	 */
	public Movimento preleva(double importo, Categoria cat);
	
	/**
	 * @param m
	 * Questo metodo aggiunger� il movimento appena creato alla lista dei Movimenti
	 */
	public void aggiungiMovimento(Movimento m);
	
	/**
	 * @return ritorner� il saldo del conto
	 */
	public double getSaldoDisponibile();
	
	/**
	 * @return ritorner� una lista di Movimenti
	 */
	public ArrayList<Movimento> getMovimenti();
	
	/**
	 * @param p
	 * con questo metodo aggiunger� il piano alla lista di Piani
	 */
	public void aggiungiPiano(Piano p);
	
	/**
	 * @return ritorner� la lista di Piani
	 */
	public ArrayList<Piano> getPiani();

	

	
	
}
