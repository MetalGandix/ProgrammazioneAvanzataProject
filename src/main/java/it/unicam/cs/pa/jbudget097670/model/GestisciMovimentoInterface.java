package it.unicam.cs.pa.jbudget097670.model;

import java.util.Date;

/**
 * @author Leonardo Mogianesi
 *
 */

public interface GestisciMovimentoInterface {

	/**
	 * I possibili tipi di Movimento
	 */
	public enum Type{ SPESA , RICAVO };
	
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
