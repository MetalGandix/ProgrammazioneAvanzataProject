package it.unicam.cs.pa.jbudget097670.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import it.unicam.cs.pa.jbudget097670.controller.OggettiController;
import it.unicam.cs.pa.jbudget097670.model.gestisciMovimento.Type;
import it.unicam.cs.pa.jbudget097670.view.GestioneInput;

public class Asset implements OperazioniAsset,Serializable{
	
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
	 * @param id
	 * Creo il costruttore di Asset
	 */
	public Asset(TipoConto tipoConto,double saldoDisponibile,char valuta, int id) {
		piani = new ArrayList<Piano>();
		movimenti = new ArrayList<Movimento>();
		this.saldoDisponibile = saldoDisponibile;
		this.valuta = valuta;
		this.tipoConto = tipoConto;
	}


	/**
	 * @param importo
	 * Qua c'è il saldo disponibile all'interno di un asset
	 */
	public void setSaldoDisponibile(double importo) {
		this.saldoDisponibile=importo;
	}

	/**
	 * @return ritorna il saldo dell'asset
	 */
	public double getSaldoDisponibile() {
		return saldoDisponibile;
	}
	
	/**
	 * Questo metodo serve ad aumentare il saldo dell'asset di una cifra stabilita dall'utente
	 */
	@Override
	public void deposita(double importo) {
		this.setSaldoDisponibile(this.getSaldoDisponibile()+importo);
	}

	/**
	 * Questo metodo serve a diminuire il saldo dell'asset di una cifra stabilita dall'utente
	 * se il saldo disponibile è minore o uguale all'importo, il metodo stampa un messaggio e non rimuove denaro dalla cassa
	 */
	@Override
	public void preleva(double importo) {
		if(this.saldoDisponibile <= importo) {
			System.out.println("Nel saldo non ci sono abbastanza soldi, preleva una cifra possibile.\nSaldo disponibile: " + this.saldoDisponibile + "\n");
			importo = 0;
			this.deposita(GestioneInput.inputInt("Scrivi un importo da aggiungere: "));
		}else {
			this.setSaldoDisponibile(this.getSaldoDisponibile()-importo); 
		}	
	}



	/**
	 *Questo metodo aggiunge un movimento alla lista, se il tipo del movimento è una spesa, viene richiamato il metodo preleva
	 *se è un importo, viene richiamato il metodo deposita
	 */
	@Override
	public void aggiungiMovimento(Movimento m) {
		if(m.getTipo()==Type.SPESA) {
			this.preleva(m.getImporto());
		}else {
			this.deposita(m.getImporto());
		}
		this.movimenti.add(m);
	}

	
	/**
	 * Mostra la lista di tutti i movimenti
	 */
	public ArrayList<Movimento> getMovimenti(){
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
	
	
	/**
	 * Questo toString() servirebbe se volessi stampare su file gli oggetti piano e movimento, però usando i json non è necessario
	 */
	/* 
	@Override
	public String toString() {
		ArrayList<Movimento> listaMov = this.getMovimenti();
		ArrayList<Piano> listaPiani = this.getPiani();
		String risultato = "";
		//Foreach
		for(Movimento mov: listaMov) {
			risultato += mov + "\n";
		}
		for(Piano p: listaPiani) {
			risultato += p +"\n";
		}
		return risultato;
	}*/

}


