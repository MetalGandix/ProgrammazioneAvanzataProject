package it.unicam.cs.pa.jbudget097670;

import java.util.ArrayList;
import java.util.Date;

import it.unicam.cs.pa.jbudget097670.OperazioniPiano.Type;

public class Piano implements OperazioniPiano{
	
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
		piano += "Movimento di tipo: " + this.getTipo() + "\n";
		piano += "Importo: " + this.importoMensile() + "\n";
		piano += "Data iniziale di apertura del piano: " + this.getDataIniziale() + "\n";
		piano += "Data in cui il piano finir�: " + this.getDataFinale();
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

































