package it.unicam.cs.pa.jbudget097670.interfaces;

import java.util.Date;

import it.unicam.cs.pa.jbudget097670.model.Categoria;

public interface gestisciMovimento {

	public enum Type{ SPESA , RICAVO };
	
	public Categoria getCategoria();
	
	public String getTipoCategoria();
	
	public double getImporto();
	
	public Type getTipo();
	
	public Date getData();
	
	public int getId();
	
}
