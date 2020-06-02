package it.unicam.cs.pa.jbudget097670;

import java.util.ArrayList;

public interface OperazioniAsset {
	
	public void aggiungiConto(Asset a);
	
	public ArrayList<Asset> getConti();

	public void deposita(double importo);
	
	public void preleva(double importo);
	
	public void aggiungiMovimento(Movimento m);
	
	public double getSaldo();
	
	public ArrayList<Movimento> getMovimenti();
	
	public int getId();
	
	public void aggiungiPiano(Piano p);
	
	public ArrayList<Piano> getPiani();
}
