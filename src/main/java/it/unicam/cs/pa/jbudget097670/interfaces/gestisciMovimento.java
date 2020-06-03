package it.unicam.cs.pa.jbudget097670.interfaces;

import java.util.Date;

import it.unicam.cs.pa.jbudget097670.model.Categoria;

/**
 * @author Leonardo Mogianesi
 *
 */

public interface gestisciMovimento {

	/**
	 * I possibili tipi di Movimento
	 */
	public enum Type{ SPESA , RICAVO };
	
	
	/**
	 * @return ritorner� la categoria del Movimento
	 */
	public Categoria getCategoria();
	
	/**
	 * @return ritorner� la categoria del Movimento
	 */
	public String getTipoCategoria();
	
	/**
	 * @return ritorner� l'importo del Movimento
	 */
	public double getImporto();
	
	/**
	 * @return ritorner� il tipo del Movimento
	 */
	public Type getTipo();
	
	/**
	 * @return ritorner� la data del Movimento
	 */
	public Date getData();
	
	/**
	 * @return ritorner� l'ID del Movimento
	 */
	public int getId();
	
}
