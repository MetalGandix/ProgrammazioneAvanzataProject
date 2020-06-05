package it.unicam.cs.pa.jbudget097670.view;

import java.io.IOException;

import it.unicam.cs.pa.jbudget097670.Client;
import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.OperazioniPiano;
import it.unicam.cs.pa.jbudget097670.model.TipoConto;

/**
 * @author Leonardo Mogianesi
 * Questa interfaccia contiene i metodi che verranno implementati
 * dalla view
 *
 */
public interface UserInput {

	/**
	 * @param messaggio
	 * @throws IOException
	 * Questo medoto sar� la prima vista che apparir� all'utente
	 */
	public void start(String messaggio) throws IOException; 
	
	/**
	 * @param asset
	 * @throws IOException
	 * Questo metodo dar� la possibilit� all'utente di 
	 * usare i metodi di get/delete all'interno del controller
	 */
	public void selezionaOggetto(Asset asset) throws IOException;
	
	/**
	 * @param messaggio
	 * @return ritorna un double
	 */
	public double inputInt(String messaggio);
	
	/**
	 * @param asset1
	 * @param asset2
	 * @return ritorna il tipo di un Movimento (ContoCorrente,Cassa)
	 */
	public TipoConto creaMovimento(Asset asset1, Asset asset2);
	
	/**
	 * @param asset1
	 * @param asset2
	 * Questo metodo permetter� di aggiungere un nuovo Piano
	 * ad una lista su uno specifico Asset
	 */
	public void aggiungiPiani(Asset asset1, Asset asset2);
	
	/**
	 * @param tipo
	 * @param c
	 * @param asset1
	 * @param asset2
	 * @throws IOException
	 * Questo metodo stamper� i risultati sulla console, inoltre mander� l'oggetto al server che lo stamper� sul file.json
	 */
	public void stampaRisultati(TipoConto tipo, Client c, Asset asset1, Asset asset2) throws IOException;
	
	/**
	 * @param messaggio
	 * @return vero se l'utente vuole fare un nuovo ciclo di un metodo, ritorna falso se ne vuole uscire
	 */
	public boolean sceltaNuovoCiclo(String messaggio);
	
	/**
	 * @param messaggio
	 * @return ritorna il tipo di conto che ha scelto l'utente
	 */
	public TipoConto inputConto(String messaggio);
;}
