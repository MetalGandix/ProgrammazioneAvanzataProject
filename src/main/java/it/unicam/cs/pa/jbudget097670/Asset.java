package it.unicam.cs.pa.jbudget097670;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import it.unicam.cs.pa.jbudget097670.gestisciMovimento.Type;

public class Asset implements OperazioniAsset,Budget,Serializable{
	
	private static final long serialVersionUID = 8186361857135749011L;
	private int id = 0;
	private ArrayList<Asset> nomeConto;
	private tipoConto tipoConto;
	private double saldoDisponibile = 0.0; 
	private double saldoContabile;   
	private char valuta;
	private ArrayList<Movimento> movimenti;
	private ArrayList<Piano> piani;

	public Asset(ArrayList<Asset> nomeConto, tipoConto tipoConto,double saldoDisponibile,double saldoContabile,char valuta, int id) {
		this.nomeConto = new ArrayList<Asset>();
		this.id = id;
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
	
	//Deposita un importo
	@Override
	public void deposita(double importo) {
		this.setSaldoDisponibile(this.getSaldoDisponibile()+importo);
	}

	//Preleva un importo dal saldo disponibile
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
	public ArrayList<Asset> getConti() {
		return this.nomeConto;
	}
	
	@Override
	public void aggiungiConto(Asset a) {
		this.nomeConto.add(a);
	}

	@Override
	public double getSaldo() {
		return saldoContabile;
	}

	@Override
	public int getId() {
		return id;
	}

	public char getValuta() {
		return valuta;
	}

	public tipoConto getTipoConto() {
		return tipoConto;
	}
	
	public ArrayList<Piano> getPiano(){
		return piani;
	}
	
	public OperazioniPiano.Type getTipoPiano() {
		Piano p = new Piano(null, saldoContabile, saldoContabile, id, null, id);
		return p.getTipo();
	}
	
	@Override
	public String toString() {
		ArrayList<Movimento> lista = this.getMovimenti();
		ArrayList<Piano> listaPiani = this.getPiano();
		String risultato = "";
		//Foreach
		for(Movimento mov: lista) {
			risultato += mov + "\n";
		}
		/*for(Piano p: listaPiani) {
			risultato += p +"\n";
		}*/
		return risultato;
	}

}


