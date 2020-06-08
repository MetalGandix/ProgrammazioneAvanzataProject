package it.unicam.cs.pa.jbudget097670.model;

import java.io.Serializable;
import java.util.ArrayList;
import it.unicam.cs.pa.jbudget097670.controller.DateController;

public class Asset implements OperazioniAsset, Serializable {

	private static final long serialVersionUID = 8186361857135749011L;
	private TipoConto tipoConto;
	private double saldoDisponibile = 0.0;
	private char valuta;
	private ArrayList<Movimento> movimenti;
	private ArrayList<Piano> piani;
 
	/** 
	 * @param tipoConto
	 * @param saldoDisponibile
	 * @param saldoContabile
	 * @param valuta  
	 * @param id               Creo il costruttore di Asset
	 */
	public Asset(TipoConto tipoConto, double saldoDisponibile, char valuta) {
		piani = new ArrayList<Piano>();
		movimenti = new ArrayList<Movimento>();
		this.saldoDisponibile = saldoDisponibile;
		this.valuta = valuta;
		this.tipoConto = tipoConto;
	}

	/**
	 * @param importo Qua c'è il saldo disponibile all'interno di un asset
	 */
	public void setSaldoDisponibile(double importo) {
		this.saldoDisponibile = importo;
	}

	/**
	 * @return ritorna il saldo dell'asset
	 */
	public double getSaldoDisponibile() {
		return saldoDisponibile;
	}

	/**
	 * Questo metodo serve ad aumentare il saldo dell'asset di una cifra stabilita
	 * dall'utente
	 */
	@Override
	public Movimento deposita(double importo, Categoria cat) {
		DateController odierna = new DateController();
		Movimento mov = new Movimento(cat, importo, odierna.getDate(), this.getMovimenti().size());
		this.aggiungiMovimento(mov);
		this.setSaldoDisponibile(this.getSaldoDisponibile() + importo);
		return mov;
	}
 
	/**
	 * Questo metodo serve a diminuire il saldo dell'asset di una cifra stabilita
	 * dall'utente se il saldo disponibile è minore o uguale all'importo, il metodo
	 * stampa un messaggio e non rimuove denaro dalla cassa
	 */
	@Override
	public Movimento preleva(double importo, Categoria cat) {
		if (this.saldoDisponibile <= importo) {
			return null;
		} else {
			DateController odierna = new DateController();
			Movimento mov = new Movimento(cat, -importo, odierna.getDate(), this.getMovimenti().size());
			this.aggiungiMovimento(mov);
			this.setSaldoDisponibile(this.getSaldoDisponibile() - importo);
			return mov;
		}
	}

	/**
	 * Questo metodo aggiunge un movimento alla lista, se il tipo del movimento è
	 * una spesa, viene richiamato il metodo preleva se è un importo, viene
	 * richiamato il metodo deposita
	 */
	@Override
	public void aggiungiMovimento(Movimento m) {
		this.movimenti.add(m);
	}

	/**
	 * Mostra la lista di tutti i movimenti
	 */
	public ArrayList<Movimento> getMovimenti() {
		return movimenti;
	}

	/**
	 * @return ritorna il tipo di valuta, di tipo char
	 */
	public char getValuta() {
		return valuta;
	}

	/**
	 * @return ritorna il tipo del conto (Conto corrente o Cassa)
	 */
	public TipoConto getTipoConto() {
		return tipoConto;
	}

	/**
	 * Aggiunge il piano alla lista
	 */
	@Override
	public void aggiungiPiano(Piano p) {
		piani.add(p);
	}

	/**
	 * Mostra i piani creati
	 */
	@Override
	public ArrayList<Piano> getPiani() {
		return piani;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(saldoDisponibile);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipoConto == null) ? 0 : tipoConto.hashCode());
		result = prime * result + valuta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asset other = (Asset) obj;
		if (Double.doubleToLongBits(saldoDisponibile) != Double.doubleToLongBits(other.saldoDisponibile))
			return false;
		if (tipoConto != other.tipoConto)
			return false;
		if (valuta != other.valuta)
			return false;
		return true;
	}
	
	
}
