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
	 * @return ritornerÓ la categoria del Movimento
	 */
	public String getTipoCategoria();
	
	/**
	 * @return ritornerÓ l'importo del Movimento
	 */
	public double getImporto();
	
	/**
	 * @return ritornerÓ il tipo del Movimento
	 */
	public Type getTipo();
	
	/**
	 * @return ritornerÓ la data del Movimento
	 */
	public Date getData();
	
	/**
	 * @return ritornerÓ l'ID del Movimento
	 */
	public int getId();	
}
