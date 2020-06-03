package it.unicam.cs.pa.jbudget097670.model;

import java.io.Serializable;

public class Categoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8186361857135749011L;
	private String tipo;
	
	public Categoria(String categoria) {
		this.tipo = categoria;
	}

	//Get di tipo
	public String getTipo() {
		return tipo;
	}

}
