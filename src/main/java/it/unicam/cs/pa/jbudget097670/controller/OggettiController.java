package it.unicam.cs.pa.jbudget097670.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Categoria;
import it.unicam.cs.pa.jbudget097670.model.Movimento;
import it.unicam.cs.pa.jbudget097670.model.Piano;
import it.unicam.cs.pa.jbudget097670.view.GestioneInput;
import it.unicam.cs.pa.jbudget097670.model.OperazioniPiano.Type;

/**
 * @author Leonardo Mogianesi Questa classe serve per controllare tutti gli
 *         input che l'utente inserisce all'interno del programma
 */
public class OggettiController implements OggettoController{
	

	/**
	 * @param asset
	 * @return ritorna il movimento che ho aggiunto alla lista In questa classe
	 *         aggiorno iil conto creando un nuovo Movimento
	 */
	public static Asset aggiornaConto(Asset asset) {
		double importo = GestioneInput.inputInt("Scrivi l'importo da transitare: ");
		Categoria cat = new Categoria(GestioneInput.inputString("Scrivi categoria: "));
		DateController odierna = new DateController();
		Movimento mov = new Movimento(cat, importo, odierna.getDate(), asset.getMovimenti().size());
		asset.aggiungiMovimento(mov);
		System.out.println("Importo transitato nel Movimento: " + mov.getImporto());
		System.out.println("Movimento con categoria: " + mov.getTipoCategoria());
		System.out.println("Movimento effettuato in data: " + mov.getData());
		System.out.println("Movimento con ID: " + mov.getId());
		return asset;
	}

	/**
	 * @param asset
	 * @param tipo
	 * @return ritorno il piano aggiunto dall'utente In questa classe l'utente
	 *         creerà un nuovo piano inserendo l'importo, il tasso e le date del
	 *         piano.
	 */
	public static Asset aggiornaPiano(Asset asset, Type tipo) {
		DateController data = new DateController();
		double importoPiano = GestioneInput.inputInt("Scrivi l'importo da aggiungere al piano: ");
		double importo = GestioneInput.inputInt("Scrivi il tasso a regime: ");
		int durataPiano = (int) GestioneInput.inputInt("Scrivi quanti mesi durerà il piano: ");
		Piano piano = new Piano(tipo, importoPiano, importo, durataPiano, data.getDate(),
				data.getFinalDate(durataPiano), asset.getPiani().size());
		asset.aggiungiPiano(piano);
		System.out.println("Piano di tipo: " + tipo + "\nL'importo mensile del piano è: " + piano.importoMensile());
		return asset;
	}

	@Override
	public Asset getMovimentoPerId() {
		return null;
	}

	@Override
	public Asset getPianoPerId() {
		return null;
	}

	@Override
	public Asset getMovimentoPerGiorno() {
		return null;
	}
	
	/**
	 * Mostra i movimenti raggruppati per Categoria
	 */
	@Override
	public Collection<Movimento> getMovimentiperCategoria(Categoria c) {
		return null;
	}
	
}
