package it.unicam.cs.pa.jbudget097670.controller;

import java.util.ArrayList;
import java.util.Collection;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Categoria;
import it.unicam.cs.pa.jbudget097670.model.Movimento;
import it.unicam.cs.pa.jbudget097670.model.Piano;
import it.unicam.cs.pa.jbudget097670.model.OperazioniPianoInterface.Type;

/**
 * @author Leonardo Mogianesi Questa classe serve per controllare tutti gli
 *         input che l'utente inserisce all'interno del programma
 */
public class OggettiController implements OggettiControllerInterface {
	/**
	 * @param destinazione
	 * @param sorgente
	 * @param importo
	 * @param cat
	 * @return ritorna il movimento che ho aggiunto alla lista In questa classe
	 *         aggiorno la carta di credito creando un nuovo Movimento in caso di spesa
	 */
	@Override
	public Asset aggiornaCartaDiCreditoSpesa(Asset destinazione, Asset sorgente, double importo, Categoria cat) {  
		Movimento mov = null;
			if (importo < 0) {
				mov = destinazione.preleva(-importo, cat);
			}  
			return destinazione;
	}
	
	/**
	 * @param destinazione
	 * @param sorgente
	 * @param importo
	 * @param cat
	 * @return ritorna il movimento che ho aggiunto alla lista In questa classe
	 *         aggiorno la carta di credito creando un nuovo Movimento in caso di ricavo
	 */
	@Override
	public Asset aggiornaCartaDiCreditoRicavo(Asset destinazione, Asset sorgente, double importo, Categoria cat) {
		Movimento mov = null;
		mov = sorgente.preleva(importo, cat);
		if (mov != null) {
			destinazione.deposita(importo, cat);
		}  		
		return destinazione;
	}
	
	/**
	 * @param destinazione
	 * @param sorgente
	 * @param importo
	 * @param cat
	 * @return ritorna il conto scelto come destinazione 
	 * (In questo caso il conto corrente)
	 */
	@Override
	public Asset aggiornaContoCorrente(Asset destinazione, Asset sorgente, double importo, Categoria cat) {
		Movimento mov = null;
		mov = destinazione.deposita(importo, cat);
		return destinazione;
	}

	/**
	 * @param asset
	 * @param tipo
	 * @return ritorno il piano aggiunto dall'utente In questa classe l'utente
	 *         creerà un nuovo piano inserendo l'importo, il tasso e le date del
	 *         piano.
	 */
	@Override
	public Asset aggiornaPiano(Asset asset, Type tipo, double importoPiano, double importo,int durataPiano) {
		DateController data = new DateController();
		Piano piano = new Piano(tipo, importoPiano, importo, durataPiano, data.getDate(),
				data.getFinalDate(durataPiano), asset.getPiani().size());
		asset.aggiungiPiano(piano);
		return asset;
	}

	/**
	 * Questo metodo ritorna il movimento con l'id selezionato dall'utente Se
	 * l'utente inserisce un id non associato ad un movimento, parte un messaggio
	 * d'errore
	 */
	@Override
	public Collection<Movimento> getMovimentoPerId(Asset asset, int x) throws Exception {
		ArrayList<Movimento> movWithId = new ArrayList<Movimento>();
		asset.getMovimenti().forEach(t -> {
			if (x == t.getId()) {
				movWithId.add(t);
			}
		});
		return movWithId;
	}

	/**
	 * In questo metodo ritorna il piano con Id selezionato dall'utente. L'utente
	 * inserisce un ID associato al piano che vuole vedere stampato.
	 */
	@Override
	public Collection<Piano> getPianoPerId(Asset asset, int x) throws Exception {
		ArrayList<Piano> pianoId = new ArrayList<Piano>();
		asset.getPiani().forEach(p -> {
			if (x == p.getId()) {
				pianoId.add(p);
			}
		});
		return pianoId;
	}

	/**
	 * Questo metodo mostra una lista di Movimenti effettuati che hanno tutti la
	 * stessa Categoria inserita dall'utente
	 */
	@Override
	public Collection<Movimento> getMovimentiperCategoria(Asset asset, String x) throws Exception {
		ArrayList<Movimento> movWithCat = new ArrayList<Movimento>();
		asset.getMovimenti().forEach(c -> {
			if (x.equals(c.getTipoCategoria())) {
				movWithCat.add(c);
			}
		});
		return movWithCat;
	}

	/**
	 * Questo metodo ritorna nella console la lista dei Piani con lo stesso tipo inserito
	 * dall'utente
	 */
	@Override
	public Collection<Piano> getPianiPerTipo(Asset asset, Type x) throws Exception {
		ArrayList<Piano> pianoType = new ArrayList<Piano>();
		asset.getPiani().forEach(p -> {
			if (x == p.getTipo()) {
				pianoType.add(p);
			}
		});
		return pianoType;
	}

	/**
	 * Questo metodo serve ad eliminare un movimento dalla lista
	 */
	@Override
	public Movimento deleteMovimentoPerId(Asset asset, int x) throws Exception {
		int movIndexToRemove = -1;
		for (int i = 0; i < asset.getMovimenti().size(); i++) {
			Movimento t = asset.getMovimenti().get(i);
			if (t.getId() == x) {
				movIndexToRemove = i;
				asset.getMovimenti().remove(movIndexToRemove);
				return t;
			}
		}
		return null;
	}

	/**
	 * Questo metodo serve ad eliminare un piano dalla lista
	 */
	@Override
	public Piano deletePianoPerId(Asset asset, int x) throws Exception {
		int pIndexToRemove = -1;
		for (int i = 0; i < asset.getPiani().size(); i++) {
			Piano p = asset.getPiani().get(i);
			if (p.getId() == x) {
				pIndexToRemove = i;
				asset.getPiani().remove(pIndexToRemove);
				return p;
			}
		}
		return null;
	}
}
