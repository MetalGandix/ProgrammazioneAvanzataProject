package it.unicam.cs.pa.jbudget097670.view;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import it.unicam.cs.pa.jbudget097670.Client;
import it.unicam.cs.pa.jbudget097670.controller.OggettiController;
import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Categoria;
import it.unicam.cs.pa.jbudget097670.model.Movimento;
import it.unicam.cs.pa.jbudget097670.model.OperazioniPiano;
import it.unicam.cs.pa.jbudget097670.model.TipoConto;
import it.unicam.cs.pa.jbudget097670.model.OperazioniPiano.Type;

public class GestioneInput {
	public static Scanner i = null;

	/**
	 * Creo un nuovo scanner
	 */
	public static void getInstance() {
		if (i == null)
			i = new Scanner(System.in);
	}

	/**
	 * @param messaggio, che scriver� poi nel main, che spiega all'utente cosa fare
	 *                   per muoversi nel programma
	 * @throws IOException
	 * 
	 *                     Connetto il Client con il server Inizializzo a 0 i 2
	 *                     conti
	 * 
	 */
	public static void start(String messaggio) throws IOException {

		GestioneInput.getInstance();
		Client c = new Client();
		c.connetti();

		Asset cassa = c.leggiOggetto();
		Asset contoCorrente = c.leggiOggetto();
		

		TipoConto tipo = null;
		OggettiController o = new OggettiController();
		while (true) {
			System.out.print(messaggio);
			int risultato = 0;
			risultato = i.nextInt();
			i.nextLine();
			switch (risultato) {
			default:
				System.out.println("Devi scegliere un numero tra 1, 2, 3, 4 o 5");
				continue;
			case 1:
				tipo = GestioneInput.creaMovimento(contoCorrente, cassa);
				continue;
			case 2:
				GestioneInput.aggiungiPiani(contoCorrente, cassa);
				continue;
			case 3:
				System.out.println("Uscita dall'app in corso...");
				System.exit(1);
			case 4:
				GestioneInput.stampaRisultati(tipo, c, contoCorrente, cassa);
				c.connetti();
				continue;
			case 5:
				selezionaOggetto(cassa);
				continue;
			}
		}
	}

	/**
	 * @param asset
	 * @throws IOException Questo metodo chiama i metodi all'interno del Controller
	 */
	public static void selezionaOggetto(Asset asset) throws IOException {
		OggettiController o = new OggettiController();
		GestioneInput.getInstance();
		while (true) {
			System.out.println(
					"Seleziona: " + "\n 0)Per tornare alla home." + "\n 1)Seleziona un Movimento con un ID specifico. "
							+ "\n 2)Seleziona uno o pi� Movimenti con una categoria specifica. "
							+ "\n 3)Seleziona un Piano con un id specifico. "
							+ "\n 4)Seleziona uno o pi� Piani dello stesso tipo. "
							+ "\n 5)Cancella un Movimento inserendo il suo ID. " + "\n");
			int risultato = 0;
			risultato = i.nextInt();
			i.nextLine();
			switch (risultato) {
			default:
				System.out.println("Devi inserire un numero che sia 1, 2, 3, 4 o 5");
				continue;
			case 0:
				return;
			case 1:
				try {
					o.getMovimentoPerId(asset);
				} catch (Exception e) {
					e.printStackTrace();
				}
				continue;
			case 2:
				o.getMovimentiperCategoria(asset);
				continue;
			case 3:
				try {
					o.getPianoPerId(asset);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				continue;
			case 4:
				o.getPianiPerTipo(asset);
				continue;
			case 5:
				o.deleteMovimentoPerId(asset);
				continue;
			}
		}
	}

	/**
	 * @param messaggio, che dir� all'utente di inserire un double
	 * @return
	 */
	public static double inputInt(String messaggio) {
		double risultato = 0;
		GestioneInput.getInstance();
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
	public static TipoConto creaMovimento(Asset contoCorrente, Asset cassa) {
		TipoConto tipo = null;
		tipo = GestioneInput.inputConto("Digita il conto che vuoi utilizzare: 1) Conto Corrente, 2) Cassa\n ");
		while (true) {
			if (tipo == TipoConto.CONTO_CORRENTE)
				contoCorrente = OggettiController.aggiornaConto(contoCorrente, cassa);
			else
				cassa = OggettiController.aggiornaConto(cassa, contoCorrente);
			boolean continuaMovimento = GestioneInput
					.sceltaNuovoM("Digita 1 per creare un altro movimento o 2 per fermarti qua. \n");
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
	public static void aggiungiPiani(Asset contoCorrente, Asset cassa) {
		TipoConto tipo = GestioneInput.inputConto("Digita il conto che vuoi utilizzare: 1) ContoCorrente, 2) Cassa\n ");
		while (true) {
			OperazioniPiano.Type tipoPiano = GestioneInput.apriPiano(
					"Premi 1 se vuoi creare un piano d'ammortamento o premi 2 se vuoi creare un piano d'investimento: ");
			switch (tipo) {
			default:
			case CASSA:
				OggettiController.aggiornaPiano(cassa, tipoPiano);
				break;
			case CONTO_CORRENTE:
				OggettiController.aggiornaPiano(contoCorrente, tipoPiano);
				break;
			}
			boolean continuaPiano = GestioneInput
					.sceltaNuovoM("Digita 1 per creare un altro piano o 2 per fermarti qua. \n");
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
	public static void stampaRisultati(TipoConto tipo, Client c, Asset contoCorrente, Asset cassa) throws IOException {
		// Mando dal Client al Server l'oggetto
		
		System.out.println("Controlla il file per vedere i Movimenti e i Piani. \n");
		c.output.writeObject(contoCorrente);
		c.output.flush();
		c.output.close();
		System.out.println("Controlla il file per vedere i Movimenti e i Piani. \n");
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
	public static boolean sceltaNuovoM(String messaggio) {
		GestioneInput.getInstance();
		System.out.print(messaggio);
		int risultato = 0;
		risultato = i.nextInt();
		// Il lextLine() dopo il nextInt() serve per risolvere un bug che mi dava, se lo
		// togliessi mi salterebbe una riga
		i.nextLine();
		switch (risultato) {
		default:
			System.out.println("Devi inserire un numero che sia 1 o 2");
			return sceltaNuovoM(messaggio);
		case 1:
			return true;
		case 2:
			return false;
		}
	}

	/**
	 * @param messaggio
	 * @return ritorna true se l'utente vuole creare un altro piano, altrimenti
	 *         ritorna false
	 */
	public static boolean sceltaNuovoP(String messaggio) {
		GestioneInput.getInstance();
		System.out.print(messaggio);
		int sceltaNuovoPiano = 0;
		sceltaNuovoPiano = i.nextInt();
		i.nextLine();
		switch (sceltaNuovoPiano) {
		default:
			System.out.println("Devi inserire un numero che sia 1 o 2");
			return sceltaNuovoP(messaggio);
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
	public static OperazioniPiano.Type apriPiano(String messaggio) {
		GestioneInput.getInstance();
		System.out.print(messaggio);
		int risultato = 0;
		risultato = i.nextInt();
		i.nextLine();
		switch (risultato) {
		default:
			System.out.println("Devi selezionare 1 per il piano d'ammortamento o 2 per il piano d'investimento.");
			return apriPiano(messaggio);
		case 1:
			System.out.println("Il Piano scelto � di tipo Ammortamento.\n");
			return Type.Ammortamento;
		case 2:
			System.out.println("Il Piano scelto � di tipo Investimento.\n");
			return Type.Investimento;
		}
	}

	/**
	 * @param messaggio
	 * @return ritorna il conto corrente se risultato � = 1, altrimenti ritorna la
	 *         cassa In questo metodo scelgo dove fare Movimenti
	 */
	public static TipoConto inputConto(String messaggio) {
		GestioneInput.getInstance();
		System.out.print(messaggio);
		int risultato = 0;
		risultato = i.nextInt();
		switch (risultato) {
		default:
			System.out.println("Devi selezionare 1 o 2");
			return inputConto(messaggio);
		case 1:
			System.out.println("Hai scelto il conto corrente.\n ");
			return TipoConto.CONTO_CORRENTE;
		case 2:
			System.out.println("Hai scelto la cassa.\n");
			return TipoConto.CASSA;
		}
	}

	/**
	 * @param messaggio
	 * @return ritorna una stringa dell'utente che sar� il nome della categoria del
	 *         Movimento
	 */
	public static String inputString(String messaggio) {
		GestioneInput.getInstance();
		System.out.print(messaggio);
		String descrizione = null;
		descrizione = i.nextLine();
		return descrizione;
	}

	/**
	 * @param messaggio
	 * @return ritorna un numero inserito dall'utente, che sar� poi usato per
	 *         trovare un ID in una lista di movimenti
	 */
	public static int cercaId(String messaggio) {
		GestioneInput.getInstance();
		System.out.print(messaggio);
		int descrizione = 0;
		descrizione = i.nextInt();
		return descrizione;
	}
}