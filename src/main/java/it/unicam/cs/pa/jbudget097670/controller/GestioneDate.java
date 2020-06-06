package it.unicam.cs.pa.jbudget097670.controller;

import java.util.Date;

/**
 * @author Leonardo Mogianesi
 *
 */
public interface GestioneDate {
	/**
	 * @return ritorer� la data odierna
	 */
	Date getDate();
	
	/**
	 * @param mesi
	 * @return ritorner� la data futura in base al numero di mesi aggiunti dall'utente
	 */
	Date getFinalDate(int mesi);
}
