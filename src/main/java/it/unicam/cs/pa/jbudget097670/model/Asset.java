package it.unicam.cs.pa.jbudget097670.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import it.unicam.cs.pa.jbudget097670.model.gestisciMovimento.Type;

public class Asset implements OperazioniAsset,Budget,Serializable{
	
	private static final long serialVersionUID = 8186361857135749011L;
	private TipoConto tipoConto;
	private double saldoDisponibile = 0.0; 
	private double saldoContabile;   
	private char valuta;
	private ArrayList<Movimento> movimenti;
	private ArrayList<Piano> piani;

	public Asset(TipoConto tipoConto,double saldoDisponibile,double saldoContabile,char valuta, int id) {
		piani = new ArrayList<Piano>();
		movimenti = new ArrayList<Movimento>();
		this.saldoDisponibile = saldoDisponibile;
		this.saldoContabile = saldoContabile;
		this.valuta = valuta;
		this.tipoConto = tipoConto;
	}

	public Asset() {
	}

	//Get e set di saldoDisponibile()
	public void setSaldoDisponibile(double importo) {
		this.saldoDisponibile=importo;
	}

	public double getSaldoDisponibile() {
		return saldoDisponibile;
	}
	
	@Override
	public void deposita(double importo) {
		this.setSaldoDisponibile(this.getSaldoDisponibile()+importo);
	}

	@Override
	public void preleva(double importo) {
		this.setSaldoDisponibile(this.getSaldoDisponibile()-importo); 
	}

	//Aggiunge un movimento alla lista movimenti
	@Override
	public void aggiungiMovimento(Movimento m) {
		if(m.getTipo()==Type.SPESA) {
			this.preleva(m.getImporto());
		}else {
			this.deposita(m.getImporto());
		}
		this.movimenti.add(m);
	}

	
	//Mostra tutti i movimenti
	public ArrayList<Movimento> getMovimenti(){
		return movimenti;
	}
	

	//Mostra i movimenti per il tipo di categoria
	@Override
	public Collection<Movimento> getMovimentiperCategoria(Categoria c) {
		ArrayList<Movimento> risultato = new ArrayList<Movimento>();
		getMovimenti().forEach(mov ->{
			if(mov.getCategoria() == c){
				risultato.add(mov);
			}
		});
		return risultato;
	}

	//Mostra i movimenti in un determinato periodo di tempo     datainizio -------- movimento ----------- datafine
	@Override
	public Collection<Movimento> getMovimentiPerData(Date dataInizio, Date dataFine) {
		ArrayList<Movimento> risultato = new ArrayList<Movimento>();
		getMovimenti().forEach(mov->{
			if(mov.getData().after(dataInizio) && mov.getData().before(dataFine) ) {
				risultato.add(mov);
			}
		});
		return risultato;
	}

	@Override
	public double getSaldo() {
		return saldoContabile;
	}

	public char getValuta() {
		return valuta;
	}

	public TipoConto getTipoConto() {
		return tipoConto;
	}

	@Override
	public void aggiungiPiano(Piano p) {
		piani.add(p);
	}

	@Override
	public ArrayList<Piano> getPiani() {
		return piani;
	}

}


