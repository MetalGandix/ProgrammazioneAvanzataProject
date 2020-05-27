package it.unicam.cs.pa.jbudget097670;

import java.util.ArrayList;

import it.unicam.cs.pa.jbudget097670.OperazioniPiano.Type;

public class Piano implements OperazioniPiano{
	
	private double importoTotale;
	private int durataMesi;
	private double tassoARegime;
	private Type tipo;
	private int id;
	private ArrayList<Piano> piani;
	
	
	
	public Piano(double importoTotale,int durataMesi,double tassoARegime,Type tipo, int id) {
		this.tipo = tipo;
	}

	@Override
	public double importoMensile() {
		System.out.println("Questo è l'importo da pagare mensilmente: ");
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

	public ArrayList<Piano> getPiani() {
		return piani;
	}
}

































