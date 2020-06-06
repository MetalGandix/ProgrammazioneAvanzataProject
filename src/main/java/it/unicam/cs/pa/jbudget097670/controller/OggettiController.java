package it.unicam.cs.pa.jbudget097670.controller;

import java.util.ArrayList;
import java.util.Collection;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Categoria;
import it.unicam.cs.pa.jbudget097670.model.Movimento;
import it.unicam.cs.pa.jbudget097670.model.Piano;
import it.unicam.cs.pa.jbudget097670.model.TipoConto;
import it.unicam.cs.pa.jbudget097670.model.gestisciMovimento;
import it.unicam.cs.pa.jbudget097670.view.GestioneInput;
import it.unicam.cs.pa.jbudget097670.model.OperazioniPiano.Type;

/**
 * @author Leonardo Mogianesi Questa classe serve per controllare tutti gli
 *         input che l'utente inserisce all'interno del programma
 */
public class OggettiController implements GetOggetti {
	
	/**
	 * @param asset
	 * @return ritorna il movimento che ho aggiunto alla lista In questa classe
	 *         aggiorno iil conto creando un nuovo Movimento
	 */
	public static Asset aggiornaConto(Asset destinazione, Asset sorgente) {
		GestioneInput g = new GestioneInput();
		Movimento mov = null;
		double importo = g.inputInt("Scrivi l'importo da transitare: ");
		Categoria cat = new Categoria(g.inputString("Scrivi categoria: "));
		if (destinazione.getTipoConto() == TipoConto.CASSA) {
			if (importo < 0) {
				mov = destinazione.preleva(-importo, cat);
			} else {
				System.out.println("Scegli un importo da prelevare dal ContoCorrente: \n");
				mov = sorgente.preleva(importo, cat);
				if (mov != null) {
					System.out.println("Prelevato: " + importo);
					destinazione.deposita(importo, cat);
					System.out.println("Saldo conto corrente: " + sorgente.getSaldoDisponibile() + "\nSaldo carta di credito: " + destinazione.getSaldoDisponibile());
				} 
			}
		} else {
			if(importo < 0){
				mov = null;
				System.out.println("Non � possibile fare spese con il conto corrente.");
			}else {
			mov = destinazione.deposita(importo, cat);
			}
		}
		if (mov != null) {
			System.out.println("Importo transitato nel Movimento: " + mov.getImporto() + "\nMovimento con categoria: "
					+ mov.getTipoCategoria() + "\nMovimento effettuato in data: " + mov.getData()
					+ "\nMovimento con ID: " + mov.getId());
		}else {
			System.out.println("Non � stato possibile prelevare dal conto.");
		}
		return destinazione;
	}

	/**
	 * @param asset
	 * @param tipo
	 * @return ritorno il piano aggiunto dall'utente In questa classe l'utente
	 *         creer� un nuovo piano inserendo l'importo, il tasso e le date del
	 *         piano.
	 */
	public static Asset aggiornaPiano(Asset asset, Type tipo) {
		GestioneInput g = new GestioneInput();
		DateController data = new DateController();
		double importoPiano = g.inputInt("Scrivi l'importo da aggiungere al piano: ");
		double importo = g.inputInt("Scrivi il tasso a regime: ");
		int durataPiano = (int) g.inputInt("Scrivi quanti mesi durer� il piano: ");
		Piano piano = new Piano(tipo, importoPiano, importo, durataPiano, data.getDate(),
				data.getFinalDate(durataPiano), asset.getPiani().size());
		asset.aggiungiPiano(piano);
		System.out.println("Piano di tipo: " + tipo + "\nL'importo mensile del piano �: " + piano.importoMensile());
		return asset;
	}

	/**
	 * Questo metodo ritorna il movimento con l'id selezionato dall'utente Se
	 * l'utente inserisce un id non associato ad un movimento, parte un messaggio
	 * d'errore
	 * 
	 * @throws Exception
	 */
	@Override
	public Collection<Movimento> getMovimentoPerId(Asset asset) throws Exception {
		ArrayList<Movimento> movWithId = new ArrayList<Movimento>();
		int x = GestioneInput.cercaId("Inserisci l'id del Movimento che vuoi visualizzare: \n");
		asset.getMovimenti().forEach(t -> {
			if (x == t.getId()) {
				movWithId.add(t);
			}
		});
		if (movWithId.size() == 0) {
			throw new Exception("Non hai ancora creato nessun Movimento");
		} else {
			movWithId.forEach(t -> {
				if (movWithId != null) {
					System.out.println("ID: " + t.getId() + "\nTipo del Movimento: " + t.getTipo()
							+ "\nCategoria del Movimento: " + t.getTipoCategoria() + "\nImporto del Movimento: "
							+ t.getImporto() + "\nData del Movimento: " + t.getData() + "\n");
				}
			});
		}
		return movWithId;
	}

	/**
	 * In questo metodo ritorna il piano con Id selezionato dall'utente. L'utente
	 * inserisce un ID associato al piano che vuole vedere stampato.
	 */
	@Override
	public Collection<Piano> getPianoPerId(Asset asset) throws Exception {
		ArrayList<Piano> pianoId = new ArrayList<Piano>();
		int x = GestioneInput.cercaId("Inserisci l'id del Piano che vuoi visualizzare: \n");
		asset.getPiani().forEach(p -> {
			if (x == p.getId()) {
				pianoId.add(p);
			}
		});
		if (pianoId.size() == 0) {
			throw new Exception("Non hai ancora creato nessun Piano");
		} else {
			pianoId.forEach(p -> {
				if (pianoId != null) {
					System.out.println("ID del piano: " + p.getId() + "\nTipo del piano: " + p.getTipo()
							+ "\nImporto del piano: " + p.importoMensile() + "\nData iniziale: " + p.getDataIniziale()
							+ "\nData finale: " + p.getDataFinale() + "\n");
				}
			});
		}
		return pianoId;
	}

	/**
	 * Questo metodo mostra una lista di Movimenti effettuati che hanno tutti la
	 * stessa Categoria inserita dall'utente
	 */
	@Override
	public Collection<Movimento> getMovimentiperCategoria(Asset asset) {
		ArrayList<Movimento> movWithCat = new ArrayList<Movimento>();
		String x = GestioneInput.inputString("Inserisci la categoria del Movimento che vuoi vedere: \n");
		asset.getMovimenti().forEach(c -> {
			if (x.equals(c.getTipoCategoria())) {
				movWithCat.add(c);
			}
		});
		movWithCat.forEach(t -> {
			if (movWithCat != null) {
				System.out.println("ID: " + t.getId() + "\nTipo del Movimento: " + t.getTipo()
						+ "\nCategoria del Movimento: " + t.getTipoCategoria() + "\nImporto del Movimento: "
						+ t.getImporto() + "\nData del Movimento: " + t.getData() + "\n");
			}
		});
		return movWithCat;
	}

	/**
	 * Questo metodo Ritorna la lista dei Piani con lo stesso tipo inserito
	 * dall'utente
	 */
	@Override
	public Collection<Piano> getPianiPerTipo(Asset asset) {
		ArrayList<Piano> pianoType = new ArrayList<Piano>();
		Type x = GestioneInput.apriPiano("Per visualizzare la lista dei piani inserisci: "
				+ "\n 1)Piani di tipo Ammortamento " + "\n 2)Piani di tipo Investimento");
		asset.getPiani().forEach(p -> {
			if (x == p.getTipo()) {
				pianoType.add(p);
			}
		});
		pianoType.forEach(p -> {
			if (pianoType != null) {
				System.out.println("ID del piano: " + p.getId() + "\nTipo del piano: " + p.getTipo()
						+ "\nImporto del piano: " + p.importoMensile() + "\nData iniziale: " + p.getDataIniziale()
						+ "\nData finale: " + p.getDataFinale() + "\n");
			}
		});
		return pianoType;
	}

	@Override
	public void deleteMovimentoPerId(Asset asset) throws Exception {
		int movIndexToRemove = -1;
		int x = GestioneInput.cercaId("Inserisci l'id del Movimento che vuoi visualizzare: \n");
		for (int i = 0; i < asset.getMovimenti().size(); i++) {
			Movimento t = asset.getMovimenti().get(i);
			if (t.getId() == x) {
				movIndexToRemove = i;
			}
		}
		if (movIndexToRemove < 0) {
			throw new Exception("Non hai un movimento con questo id.");
		} else {
			asset.getMovimenti().remove(movIndexToRemove);
			System.out.println("L'id rimosso �: " + x);
		}
	}

	@Override
	public Collection<Piano> deletePianoPerId(Asset asset) {
		return null;
	}

}
