package it.unicam.cs.pa.jbudget097670;

import java.util.Date;

public interface OperazioniPiano {
	public enum Type { Ammortamento,Investimento }
	public Type getTipo();
	public double importoMensile();
	public int getId();
	public Date getDataIniziale();
	public Date getDataFinale();
}