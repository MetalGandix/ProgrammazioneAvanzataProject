package it.unicam.cs.pa.jbudget097670;

public interface OperazioniPiano {
	public enum Type { Ammortamento,Investimento }
	public Type getTipo();
	public double importoMensile();
	public int getId();
}