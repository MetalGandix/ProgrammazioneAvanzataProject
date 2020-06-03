package it.unicam.cs.pa.jbudget097670.interfaces;

import java.util.ArrayList;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Movimento;
import it.unicam.cs.pa.jbudget097670.model.Piano;

public interface OperazioniAsset {
	
	
	/**
	 * @return ritornerà la lista dei conti
	 */
	public ArrayList<Asset> getConti();

	/**
	 * Questo metodo permetterà di depositare denaro all'interno dei conti
	 */
	public void deposita(double importo);
	
	/**
	 * @double importo
	 * Questo metodo permetterà di prelevare denaro all'interno dei conti
	 */
	public void preleva(double importo);
	
	/**
	 * @param m
	 * Questo metodo aggiungerà il movimento appena creato alla lista dei Movimenti
	 */
	public void aggiungiMovimento(Movimento m);
	
	/**
	 * @return ritornerà il saldo del conto
	 */
	public double getSaldo();
	
	/**
	 * @return ritornerà una lista di Movimenti
	 */
	public ArrayList<Movimento> getMovimenti();
	
	/**
	 * @return ritornerà l'ID del movimento
	 */
	public int getId();
	
	/**
	 * @param p
	 * con questo metodo aggiungerò il piano alla lista di Piani
	 */
	public void aggiungiPiano(Piano p);
	
	/**
	 * @return ritornerò la lista di Piani
	 */
	public ArrayList<Piano> getPiani();
}
