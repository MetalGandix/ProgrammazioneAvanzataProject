package it.unicam.cs.pa.jbudget097670.interfaces;

import java.util.ArrayList;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Movimento;
import it.unicam.cs.pa.jbudget097670.model.Piano;

public interface OperazioniAsset {
	
	
	/**
	 * @return ritorner� la lista dei conti
	 */
	public ArrayList<Asset> getConti();

	/**
	 * Questo metodo permetter� di depositare denaro all'interno dei conti
	 */
	public void deposita(double importo);
	
	/**
	 * @double importo
	 * Questo metodo permetter� di prelevare denaro all'interno dei conti
	 */
	public void preleva(double importo);
	
	/**
	 * @param m
	 * Questo metodo aggiunger� il movimento appena creato alla lista dei Movimenti
	 */
	public void aggiungiMovimento(Movimento m);
	
	/**
	 * @return ritorner� il saldo del conto
	 */
	public double getSaldo();
	
	/**
	 * @return ritorner� una lista di Movimenti
	 */
	public ArrayList<Movimento> getMovimenti();
	
	/**
	 * @return ritorner� l'ID del movimento
	 */
	public int getId();
	
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
