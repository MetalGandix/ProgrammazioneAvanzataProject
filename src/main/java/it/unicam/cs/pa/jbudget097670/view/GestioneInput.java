package it.unicam.cs.pa.jbudget097670.view;

import java.io.IOException;
import java.util.Scanner;

import it.unicam.cs.pa.jbudget097670.Client;
import it.unicam.cs.pa.jbudget097670.controller.OggettiController;
import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Categoria;
import it.unicam.cs.pa.jbudget097670.model.OperazioniPiano;
import it.unicam.cs.pa.jbudget097670.model.TipoConto;
import it.unicam.cs.pa.jbudget097670.model.OperazioniPiano.Type;

/**
 * @author Leonardo Mogianesi In questa classe ci sono tutti i metodi che
 *         gestiscono la view
 *
 */
public class GestioneInput implements GestioneInputInterface {
	public Scanner i = null;

	/**
	 * Creo un nuovo scanner
	 */
	public void getInstance() {
		if (i == null)
			i = new Scanner(System.in);
	}

	/**
	 * @param messaggio, che scriverò poi nel main, che spiega all'utente cosa fare
	 *                   per muoversi nel programma
	 * @throws IOException
	 * 
	 *                     Connetto il Client con il server Inizializzo a 0 i 2
	 *                     conti
	 * 
	 */
	@Override 
	public void start(String messaggio) throws IOException, NumberFormatException {
		getInstance();
		Client c = new Client();
		c.connetti(); 
		Asset cassa = c.leggiOggetto();
		if(cassa == null) {
			cassa = new Asset(TipoConto.CARTA_DI_CREDITO, 0, '€');
		}
		Asset contoCorrente = c.leggiOggetto();
		if(contoCorrente == null) {
			contoCorrente = new Asset(TipoConto.CONTO_CORRENTE, 0, '€');
		}
		TipoConto tipo = null;
		OggettiController o = new OggettiController();
		int risultato = 0;
		while (true) {
			while (true) {
				try {
					System.out.print(messaggio); 
					risultato = Integer.parseInt(i.next());
					break;
				} catch (Exception e) {
					System.out.println("Devi scegliere un numero tra 1, 2, 3, 4 o 5");
				}
			}
			switch (risultato) {
			default:
				System.out.println("Devi scegliere un numero tra 1, 2, 3, 4 o 5");
				continue;
			case 1:
				tipo = creaMovimento(contoCorrente, cassa);
				continue;
			case 2:
				aggiungiPiani(contoCorrente);
				continue;
			case 3:
				System.out.println("Uscita dall'app in corso...");
				System.exit(1);
			case 4:
				stampaRisultati(tipo, c, contoCorrente, cassa);
				c.connetti();
				continue;
			case 5:
				selezionaOggetto(cassa, contoCorrente);
				continue;
			}
		}
	}

	/**
	 * @param asset
	 * @throws IOException Questo metodo chiama i metodi all'interno del Controller
	 */
	@Override
	public void selezionaOggetto(Asset cassa, Asset contoCorrente) throws IOException {
		int risultato = 0;
		String messaggio = "Seleziona cosa cercare nella carta di credito: " + "\n 0)Per tornare alla home."
				+ "\n 1)Seleziona un Movimento con un ID specifico. "
				+ "\n 2)Seleziona uno o più Movimenti con una categoria specifica. "
				+ "\n 3)Seleziona un Piano con un id specifico. " + "\n 4)Seleziona uno o più Piani dello stesso tipo. "
				+ "\n 5)Cancella un Movimento inserendo il suo ID. " + ""
				+ "\n 6)Cancella un Piano inserendo il suo ID. \n";
		;
		getInstance();
		switchMethod(risultato, messaggio, cassa, contoCorrente);

	}

	/**
	 * @param risultato
	 * @param messaggio
	 * @param cassa
	 * @param contoCorrente Questo metodo serve per accorciare selezionaOggetto(),
	 *                      perchè altrimenti sarebbe venuto un metodo troppo lungo
	 *                      Chiama i metodi del controller con lo scanner di
	 *                      selezionaOggetto()
	 */
	private void switchMethod(int risultato, String messaggio, Asset cassa, Asset contoCorrente) {
		OggettiController o = new OggettiController();
		while (true) {
			while (true) {
				try {
					System.out.println(messaggio);
					risultato = Integer.parseInt(i.next());
					break;
				} catch (Exception e) {
					System.out.println("Devi scegliere un numero tra 1, 2, 3, 4, 5 o 6");
				}
			}
			switch (risultato) {
			default:
				System.out.println("Devi inserire un numero che sia 1, 2, 3, 4, 5 o 6");
				continue;
			case 0:
				return;
			case 1:
				try {
					int x = cercaId("Inserisci l'id del Movimento che vuoi visualizzare: \n");
					o.getMovimentoPerId(cassa, x);
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
				continue;
			case 2:
				try {
					String x = inputString("Inserisci la categoria del Movimento che vuoi vedere: \n");
					o.getMovimentiperCategoria(cassa,x);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
				continue;
			case 3:
				try {
					int x = cercaId("Inserisci l'id del Piano che vuoi visualizzare: \n");
					o.getPianoPerId(contoCorrente, x);
				} catch (Exception e3) {
					System.out.println(e3.getMessage());
				}
				continue;
			case 4:
				try {
					Type x = apriPiano("Per visualizzare la lista dei piani inserisci: "
							+ "\n 1)Piani di tipo Ammortamento " + "\n 2)Piani di tipo Investimento");
					o.getPianiPerTipo(contoCorrente,x);
				} catch (Exception e4) {
					System.out.println(e4.getMessage());
				}
				continue;
			case 5:
				try {
					int x = cercaId("Inserisci l'id del Movimento che vuoi eliminare: \n");
					o.deleteMovimentoPerId(cassa, x);
				} catch (Exception e5) {
					System.out.println(e5.getMessage());
				}
				continue;
			case 6:
				try {
					int x = cercaId("Inserisci l'id del Piano che vuoi eliminare: \n");
					o.deletePianoPerId(contoCorrente, x);
				} catch (Exception e6) {
					System.out.println(e6.getMessage());
				}
			}
		}
	}

	/**
	 * @param messaggio, che dirà all'utente di inserire un double
	 * @return
	 */
	@Override
	public double inputInt(String messaggio) {
		double risultato = 0;
		getInstance();
		System.out.print(messaggio);
		while (true) {
			try {
				risultato = Double.parseDouble(i.next());
				i.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Devi inserire un importo numerico, reinserisci un importo:");
			}
		}
		return risultato;
	}

	/**
	 * @param contoCorrente
	 * @param cassa
	 * @return ritorna il tipo di conto che l'utente ha selezionato, 1
	 *         contoCorrente, 2 cassa
	 */
	@Override
	public TipoConto creaMovimento(Asset contoCorrente, Asset cassa) {
		OggettiController controller = new OggettiController();
		TipoConto tipo = null;
		while (true) {
			tipo = inputConto("Digita il conto che vuoi utilizzare: 1) Conto Corrente, 2) Carta di credito\n ");
			double importo = inputInt("Scrivi l'importo da transitare: ");
			Categoria cat = new Categoria(inputString("Scrivi categoria: ")); 
			if (tipo == TipoConto.CONTO_CORRENTE) {
				if(importo < 0) {
					System.out.println("Non puoi effettuare spese nel conto corrente.");
				}else {
				contoCorrente = controller.aggiornaContoCorrente(contoCorrente, cassa, importo, cat);
				System.out.println("Lista dei movimenti del Conto Corrente: \n" + contoCorrente.getMovimenti().toString());
				}
			}else {
				if(importo < 0) {
					System.out.println("Hai effettuato una spesa di : " + -importo);
					cassa = controller.aggiornaCartaDiCreditoSpesa(cassa, contoCorrente, importo, cat);
				}else {
					if(contoCorrente.getSaldoDisponibile() < importo) {
						System.out.println("Impossibile effettuare un importo dal conto corrente perchè non ci sono abbastanza soldi.");
					}
					else {
					System.out.println("Hai effettuato un ricavo di : " + importo);
					cassa = controller.aggiornaCartaDiCreditoRicavo(cassa, contoCorrente, importo, cat);
					}
				}
				System.out.println("Saldo conto corrente: " + contoCorrente.getSaldoDisponibile() + "\nSaldo carta di credito: " + cassa.getSaldoDisponibile());
			}
			boolean continuaMovimento = sceltaNuovoCiclo(
					"Digita 1 per creare un altro movimento o 2 per fermarti qua. \n");
			if (!continuaMovimento) {
				break;
			}
		}
		return tipo;
	}

	/**
	 * @param contoCorrente
	 * @param cassa         A seconda del conto scelto dall'utente, aggiorna il
	 *                      piano
	 */
	@Override
	public void aggiungiPiani(Asset contoCorrente) {
		OggettiController controller = new OggettiController();
		while (true) {
			OperazioniPiano.Type tipoPiano = apriPiano(
					"Premi 1 se vuoi creare un piano d'ammortamento o premi 2 se vuoi creare un piano d'investimento: ");
			double importoPiano = inputInt("Scrivi l'importo da aggiungere al piano: ");
			double importo = inputInt("Scrivi il tasso a regime: ");
			int durataPiano = (int) inputInt("Scrivi quanti mesi durerà il piano: ");
			controller.aggiornaPiano(contoCorrente, tipoPiano, importoPiano, importo, durataPiano);
			System.out.println("La lista dei piani è: \n" + contoCorrente.getPiani().toString());
			boolean continuaPiano = sceltaNuovoCiclo("Digita 1 per creare un altro piano o 2 per fermarti qua. \n");
			if (!continuaPiano) {
				break;
			}
		}
	}

	/**
	 * @param tipo,          il tipo di conto
	 * @param c,             l'oggetto del client
	 * @param contoCorrente, oggetto contoCorrente
	 * @param cassa,         oggetto cassa
	 * @throws IOException In questo metodo mando l'oggetto contoCorrente o cassa al
	 *                     server, con writeObject()
	 */
	@Override
	public void stampaRisultati(TipoConto tipo, Client c, Asset contoCorrente, Asset cassa) throws IOException {
		System.out.println("Controlla il file per vedere i Movimenti e i Piani. \n");
		c.output.writeObject(contoCorrente);
		c.output.flush();
		c.output.close();
		c.connetti();
		c.output.writeObject(cassa);
		c.output.flush();
		c.output.close();
	}

	/**
	 * @param messaggio
	 * @return ritorna true se l'utente vuole creare un altro movimento, altrimenti
	 *         ritorna false
	 */
	@Override
	public boolean sceltaNuovoCiclo(String messaggio) {
		getInstance();
		int risultato = 0;
		while (true) {
			try {
				System.out.println(messaggio);
				risultato = Integer.parseInt(i.next());
				break;
			} catch (Exception e) {
				System.out.println("Devi inserire un numero che sia 1 o 2.\n");
			}
		}
		switch (risultato) {
		default:
			System.out.println("Devi inserire un numero che sia 1 o 2.\n");
			return sceltaNuovoCiclo(messaggio);
		case 1:
			return true;
		case 2:
			return false;
		}
	}

	/**
	 * @param messaggio
	 * @return ritorna il piano di tipo ammortamento, se l'utente seleziona 1,
	 *         altrimenti se seleziona 2 ritorna un piano di tipo investimento In
	 *         questo metodo scelgo quale tipo di piano aprire
	 */
	public OperazioniPiano.Type apriPiano(String messaggio) {
		getInstance();
		int risultato = 0;
		while (true) {
			try {
				System.out.println(messaggio);
				risultato = Integer.parseInt(i.next());
				break;
			} catch (Exception e) {
				System.out.println("Devi selezionare 1 per il piano d'ammortamento o 2 per il piano d'investimento.\n");
			}
		}
		switch (risultato) {
		default:
			System.out.println("Devi selezionare 1 per il piano d'ammortamento o 2 per il piano d'investimento.\n");
			return apriPiano(messaggio);
		case 1:
			System.out.println("Il Piano scelto è di tipo Ammortamento.\n");
			return Type.Ammortamento;
		case 2:
			System.out.println("Il Piano scelto è di tipo Investimento.\n");
			return Type.Investimento;
		}
	}

	/**
	 * @param messaggio
	 * @return ritorna il conto corrente se risultato è = 1, altrimenti ritorna la
	 *         carta di credito. In questo metodo scelgo dove fare Movimenti
	 */
	@Override
	public TipoConto inputConto(String messaggio) {
		getInstance();
		int risultato = 0;
		while (true) {
			try {
				System.out.println(messaggio);
				risultato = Integer.parseInt(i.next());
				break;
			} catch (Exception e) {
				System.out.println("Devi selezionare 1 o 2.\n");
			}
		}
		switch (risultato) {
		default:
			System.out.println("Devi selezionare 1 o 2.\n");
			return inputConto(messaggio);
		case 1:
			System.out.println("Hai scelto il conto corrente!\n ");
			return TipoConto.CONTO_CORRENTE;
		case 2:
			System.out.println("Hai scelto la carta di credito!\n");
			return TipoConto.CARTA_DI_CREDITO;
		}
	}

	/**
	 * @param messaggio
	 * Doppia nextLine per evitare un bug
	 * @return ritorna una stringa dell'utente che sarà il nome della categoria del
	 *         Movimento
	 */
	@Override
	public String inputString(String messaggio) {
		getInstance();
		System.out.print(messaggio);
		String descrizione = null;
		descrizione = i.nextLine();
		return descrizione;
	}

	/**
	 * @param messaggio
	 * @return ritorna un numero inserito dall'utente, che sarà poi usato per
	 *         trovare un ID in una lista di movimenti
	 */
	@Override
	public int cercaId(String messaggio) {
		getInstance();
		System.out.print(messaggio);
		int descrizione = 0;
		descrizione = i.nextInt();
		return descrizione;
	}
}