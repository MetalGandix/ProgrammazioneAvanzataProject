package it.unicam.cs.pa.jbudget097670.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import it.unicam.cs.pa.jbudget097670.controller.DateController;
import it.unicam.cs.pa.jbudget097670.interfaces.OperazioniPiano;
import it.unicam.cs.pa.jbudget097670.interfaces.OperazioniPiano.Type;

public class Piano implements OperazioniPiano, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4496010640226398163L;
	private double importoTotale;
	private int durataMesi;
	private double tassoARegime;
	private Type tipo;
	private int id;
	private Date dataFinale;
	
	
	
	public Piano(Type tipo,double importoTotale,double tassoARegime,int durataMesi,Date dataFinale, int id) {
		this.tipo = tipo;
		this.tassoARegime = tassoARegime;
		this.durataMesi = durataMesi;
		this.id = id;
		this.importoTotale = importoTotale;
		this.dataFinale = dataFinale;
	}

	@Override
	public double importoMensile() {
		return (importoTotale*tassoARegime)/durataMesi;
	}
	


	@Override
	public int getId() {
		return id;
	}

	@Override
	public Type getTipo() {
		return tipo;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override //Richiamando l'oggetto Movimento richiamo in automatico il toString()
	public String toString() {
		String piano = "";
		piano += "Piano di tipo: " + this.getTipo() + "\n";
		piano += "Importo: " + this.importoMensile() + "\n";
		piano += "Data iniziale di apertura del piano: " + this.getDataIniziale() + "\n";
		piano += "Data in cui il piano finirà: " + this.getDataFinale();
		piano += "ID: " + this.getId() + "\n";
		return piano;
	}
	
	@Override
	public Date getDataIniziale() {
		return DateController.getDate();
	}

	@Override
	public Date getDataFinale() {
		return DateController.getFinalDate(durataMesi);
	}

}

































