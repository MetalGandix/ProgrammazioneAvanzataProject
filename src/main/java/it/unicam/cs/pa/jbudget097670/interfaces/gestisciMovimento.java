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
	 * @return ritornerà la categoria del Movimento
	 */
	public Categoria getCategoria();
	
	/**
	 * @return ritornerà la categoria del Movimento
	 */
	public String getTipoCategoria();
	
	/**
	 * @return ritornerà l'importo del Movimento
	 */
	public double getImporto();
	
	/**
	 * @return ritornerà il tipo del Movimento
	 */
	public Type getTipo();
	
	/**
	 * @return ritornerà la data del Movimento
	 */
	public Date getData();
	
	/**
	 * @return ritornerà l'ID del Movimento
	 */
	public int getId();
	
}
