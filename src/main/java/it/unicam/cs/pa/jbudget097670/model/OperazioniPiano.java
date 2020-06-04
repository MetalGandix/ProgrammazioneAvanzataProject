package it.unicam.cs.pa.jbudget097670.model;

import java.util.Date;

/**
 * @author Leonardo Mogianesi
 *
 */
public interface OperazioniPiano {
	/**
	 * I tipi di piano
	 */
	public enum Type { Ammortamento,Investimento }
	
	/**
	 * servirà per ottenere il tipo di piano (Ammortameno o Investimento)
	 */
	public Type getTipo();
	
	/**
	 * servirà per ottenere l'importo mensile del piano
	 */
	public double importoMensile();
	
	/**
	 * servirà per ottenere l'ID
	 */
	public int getId();
	
	/**
	 * servirà per ottenere la data iniziale del piano
	 */
	public Date getDataIniziale();
	
	/**
	 * servirà per ottenere la data finale del piano
	 */
	public Date getDataFinale();
}