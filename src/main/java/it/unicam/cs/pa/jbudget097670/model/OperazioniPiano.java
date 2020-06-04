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
	 * servir� per ottenere il tipo di piano (Ammortameno o Investimento)
	 */
	public Type getTipo();
	
	/**
	 * servir� per ottenere l'importo mensile del piano
	 */
	public double importoMensile();
	
	/**
	 * servir� per ottenere l'ID
	 */
	public int getId();
	
	/**
	 * servir� per ottenere la data iniziale del piano
	 */
	public Date getDataIniziale();
	
	/**
	 * servir� per ottenere la data finale del piano
	 */
	public Date getDataFinale();
}