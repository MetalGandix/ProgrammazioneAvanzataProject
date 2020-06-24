package it.unicam.cs.pa.jbudget097670.model;

import java.io.Serializable;
import java.util.Date;

import it.unicam.cs.pa.jbudget097670.controller.DateController;

/**
 * @author Leonardo Mogianesi
 * Questa classe crea un nuovo Piano che avrà come parametri: l'importo del piano, la durata del piano in mesi,
 * il tassoARegime del piano, il tipo di piano, un id, una data iniziale del piano e una data finale
 */
public class Piano implements OperazioniPianoInterface, Serializable{
	private static final long serialVersionUID = -4496010640226398163L;
	private double importoTotale;
	private int durataMesi;
	private double tassoARegime;
	private Type tipo;
	private int id;
	private Date dataFinale;
	private Date dataIniziale;
	DateController data = new DateController();
	
	/**
	 * @param tipo
	 * @param importoTotale
	 * @param tassoARegime
	 * @param durataMesi
	 * @param dataIniziale
	 * @param dataFinale
	 * @param id
	 * Creo il costruttore del Piano
	 */
	public Piano(Type tipo,double importoTotale,double tassoARegime,int durataMesi,Date dataIniziale, Date dataFinale, int id) {
		this.tipo = tipo;
		this.tassoARegime = tassoARegime;
		this.durataMesi = durataMesi;
		this.id = id;
		this.importoTotale = importoTotale;
		this.dataIniziale = dataIniziale;
		this.dataFinale = dataFinale;
	}

	/**
	 *@return ritorna l'importo mensile del piano, quanti soldi l'utente dovrà spendere o guadagnare mensilmente.
	 */
	@Override
	public double importoMensile() {
		double tassodaAggiungdere = (importoTotale*tassoARegime)/100;
		return (importoTotale+tassodaAggiungdere)/durataMesi;
	}

	/**
	 *@return ritorna l'id del piano
	 */
	@Override
	public int getId() {
		return id;
	} 

	/**
	 *@return ritorna il tipo del Piano
	 */
	@Override
	public Type getTipo() {
		return tipo;
	}
	
	/**
	 * Richiamando l'oggetto Movimento richiamo in automatico il toString()
	 * Non utilizzabile visto l'uso dei Json
	 */
	@Override //
	public String toString() {
		String piano = "";
		piano += "Piano di tipo: " + this.getTipo() + "\n";
		piano += "Importo: " + this.importoMensile() + "\n";
		piano += "Data iniziale di apertura del piano: " + this.getDataIniziale() + "\n";
		piano += "Data in cui il piano finirà: " + this.getDataFinale();
		piano += "ID: " + this.getId() + "\n";
		return piano;
	}
	
	/**
	 *@return ritorna la data iniziale del Piano
	 */
	@Override
	public Date getDataIniziale() {
		return data.getDate();
	}

	/**
	 *@return ritorna la data finale del Piano
	 */
	@Override
	public Date getDataFinale() {
		
		return data.getFinalDate(durataMesi);
	}

}

































