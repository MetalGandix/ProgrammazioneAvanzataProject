package it.unicam.cs.pa.jbudget097670;
import java.io.Serializable;
import java.util.Date;

public class Movimento implements gestisciMovimento,Serializable {
	
	private static final long serialVersionUID = 8186361857135749011L;
	private Categoria categoria;
	private double importo;
	private Type tipo;
	private Date data;
	private int id;

	public Movimento(Categoria c, double importo,Date data, int id) {
		this.categoria = c;		
		this.importo = importo;
		if(importo>0)
			this.tipo=Type.RICAVO;
		else 
			this.tipo=Type.SPESA;
		this.data=data;
		this.id = id;
	}
	
	public Movimento() {
		this.tipo = null;
		this.categoria = null;		
		this.importo = 0;
		this.data= null;
		this.id = id;
	}
	
	@Override //Richiamando l'oggetto Movimento richiamo in automatico il toString()
	public String toString() {
		String mov = "";
		mov += "Movimento di tipo: " + this.getTipo() + "\n";
		mov += "Categoria: " + this.getTipoCategoria() + "\n";
		mov += "Importo: " + this.getImporto() + "\n";
		mov += "Data: " + this.getData() + "\n";
		mov += "ID: " + this.getId() + "\n";
		return mov;
	}
	
	@Override
	public Categoria getCategoria() {
		return categoria;
	}
	
	public String getTipoCategoria() {
		return categoria.getTipo();
	}
	
	@Override
	public double getImporto() {
		return importo;
	}
	
	@Override
	public Type getTipo() {
		return tipo;
	}
	
	public Date getData() {
		return data;
	}

	@Override
	public int getId() {
		return id;
	}
}
