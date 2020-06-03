package it.unicam.cs.pa.jbudget097670.model;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Leonardo Mogianesi
 * La classe Movimento serve a creare una transazione che andrà a variare il saldo di un asset
 * Riconosciuto da una categoria, un importo, una data, un tipo e un id
 */
public class Movimento implements gestisciMovimento,Serializable {
	
	private static final long serialVersionUID = 8186361857135749011L;
	private Categoria categoria;
	private double importo;
	private Type tipo;
	private Date data;
	private int id;

	/**
	 * @param c		     la categoria del Movimento che verrà inserita dall'utente
	 * @param importo    Se l'importo inserito dall'utente ha un - davanti, il tipo del conto diventa SPESA
	 * @param data       La data odierna
	 * @param id         L'id aumenta di 1 per ogni Movimento
	 */
	public Movimento(Categoria c, double importo,Date data, int id) {
		this.categoria = c;		
		if (importo < 0)
			this.importo = importo * -1;
		else
			this.importo = importo;
		if(importo>0)
			this.tipo=Type.RICAVO;
		else 
			this.tipo=Type.SPESA;
		this.data=data;
		this.id = id;
	} 
	
	/**
	 *@return ritorna la categoria del Movimento
	 */
	@Override
	public Categoria getCategoria() {
		return categoria;
	}
	
	/**
	 *@return ritorna la categoria del Movimento
	 */
	public String getTipoCategoria() {
		return categoria.getTipo();
	}
	
	/**
	 *@return ritorna l'importo del Movimento
	 */
	@Override
	public double getImporto() {
		return importo;
	}
	
	/**
	 *@return ritorna il tipo del Movimento
	 */
	@Override
	public Type getTipo() {
		return tipo;
	}
	
	/**
	 *@return ritorna la data del Movimento
	 */
	public Date getData() {
		return data;
	}

	/**
	 *@return ritorna l'ID del Movimento
	 */
	@Override
	public int getId() {
		return id;
	}
	
	/**
	 * Richiamando un Movimento, richiamo in automatico il toString(), per la stampa su file
	 * Non è necessario per la stampa visto che i Json stampano direttamente l'oggetto senza bisogno di printare il contenuto
	 */
	@Override
	public String toString() {
		String mov = "";
		mov += "Movimento di tipo: " + this.getTipo() + "\n";
		mov += "Categoria: " + this.getTipoCategoria() + "\n";
		mov += "Importo: " + this.getImporto() + "\n";
		mov += "Data: " + this.getData() + "\n";
		mov += "ID: " + this.getId() + "\n";
		return mov;
	}
}
