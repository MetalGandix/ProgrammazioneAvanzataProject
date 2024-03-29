package it.unicam.cs.pa.jbudget097670.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Leonardo Mogianesi
 * Questa classe restituisce la data attuale e una data futura decisa dall'utente
 */
public class DateController implements GestioneDateInterface, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3545288670915460768L;

	/**
	 * @return ritorna la data odierna, questo mi � utile per mostrare all'utente la data di un Movimento o di inizio di un Piano
	 */
	@Override
	public Date getDate() {
		Date today = Calendar.getInstance().getTime();
		return today; 
	}

	/**
	 * @param mesi, indica quanti mesi bisogna aggiungere alla data odierna per arrivare alla data finale decisa dall'utente
	 * @return ritorna la data finale, aggiungendo il numero di mesi deciso dall'utente alla data attuale
	 */
	@Override
	public Date getFinalDate(int mesi) {
	Calendar cal = Calendar.getInstance(); 
	cal.add(Calendar.MONTH, mesi);
	Date finalDate = cal.getTime();
	return finalDate;
	}
}
