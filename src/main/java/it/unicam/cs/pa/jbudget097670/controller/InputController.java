package it.unicam.cs.pa.jbudget097670.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import it.unicam.cs.pa.jbudget097670.Client;
import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Categoria;
import it.unicam.cs.pa.jbudget097670.model.Movimento;
import it.unicam.cs.pa.jbudget097670.model.OperazioniPiano;
import it.unicam.cs.pa.jbudget097670.model.Piano;
import it.unicam.cs.pa.jbudget097670.model.TipoConto;
import it.unicam.cs.pa.jbudget097670.model.OperazioniPiano.Type;

/**
 * @author Leonardo Mogianesi Questa classe serve per controllare tutti gli
 *         input che l'utente inserisce all'interno del programma
 */
public class InputController {
	public static Scanner i = null;

	/**
	 * Creo un nuovo scanner
	 */
	public static void getInstance() {
		if (i == null)
			i = new Scanner(System.in);
	}

	/**
	 * @param messaggio, che scriverò poi nel main, che spiega all'utente cosa fare
	 *                   per muoversi nel programma
	 * @throws IOException
	 */
	public static void start(String messaggio) throws IOException {

		// Chiamo lo scanner
		InputController.getInstance();

		// Connetto il Client con il server
		Client c = new Client();
		c.connetti();

		// Inizializzo a 0 i 2 conti
		Asset contoCorrente = new Asset(TipoConto.CONTO_CORRENTE, 0, 0, '€', 0);
		Asset cassa = new Asset(TipoConto.CASSA, 0, 0, '€', 0);

		// TipoConto inizialmente è null, successivamente l'utente deciderà su quale
		// tipo di conto fare il movimento o il piano
		TipoConto tipo = null;

		// Finchè è true, continua a chiedere all'utente cosa vuole fare
		while (true) {
			System.out.print(messaggio);
			int risultato = 0;
			risultato = i.nextInt();
			i.nextLine();
			if (risultato != 1 && tipo == null) {
				System.out.println("Devi prima creare il conto.");
				continue;
			}
			/**
			 * Se il @risultato dello switch è un numero diverso da 1,2,3 o 4, stampa il
			 * messaggio di default Se @risultato è = 1, l'utente potrà creare un Movimento
			 * Se @risultato è = 2, l'utente potrà creare un Piano Se @risultato è = 3,
			 * l'utente potrà uscire dall'applicacazione Se @risultato è = 4, l'utente potrà
			 * stampare i conti e i pani
			 */
			switch (risultato) {
			default:
				System.out.println("Devi scegliere un numero tra 1, 2, 3 o 4");
				continue;
			case 1:
				tipo = InputController.creaMovimento(contoCorrente, cassa);
				continue;
			case 2:
				InputController.aggiungiPiani(contoCorrente, cassa);
				continue;
			case 3:
				System.out.println("Uscita dall'app in corso...");
				System.exit(1);
			case 4:
				InputController.stampaRisultati(tipo, c, contoCorrente, cassa);
				continue;
			}
		}
	}

	/**
	 * @param messaggio, che dirà all'utente di inserire un double
	 * @return
	 */
	public static double inputInt(String messaggio) {
		double risultato = 0;
		InputController.getInstance();
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
		tipo = InputController.inputConto("Digita il conto che vuoi utilizzare: 1) Conto Corrente, 2) Cassa\n ");
		while (true) {
			if (tipo == TipoConto.CONTO_CORRENTE)
				contoCorrente = InputController.aggiornaConto(contoCorrente);
			else
				cassa = InputController.aggiornaConto(cassa);
			boolean continuaMovimento = InputController
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
		TipoConto tipo = InputController
				.inputConto("Digita il conto che vuoi utilizzare: 1) ContoCorrente, 2) Cassa\n ");
		while (true) {
			OperazioniPiano.Type tipoPiano = InputController.apriPiano(
					"Premi 1 se vuoi creare un piano d'ammortamento o premi 2 se vuoi creare un piano d'investimento: ");
			switch (tipo) {
			case CASSA:
				InputController.aggiornaPiano(cassa, tipoPiano);
				break;
			default:
			case CONTO_CORRENTE:
				InputController.aggiornaPiano(contoCorrente, tipoPiano);
				break;
			}
			boolean continuaPiano = InputController
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
		if (tipo == TipoConto.CONTO_CORRENTE) {
			System.out.println("I movimenti del conto corrente sono: ");
			System.out.println(contoCorrente);
			c.output.writeObject(contoCorrente);
		} else {
			System.out.println("I movimenti della cassa sono: ");
			// Quando stampo l'oggetto, stampo in realtà il toString che ho creato nella
			// classe Asset
			System.out.println(cassa);
			c.output.writeObject(cassa);
		}
		c.output.flush();
		c.output.close();
	}

	/**
	 * @param messaggio
	 * @return ritorna true se l'utente vuole creare un altro movimento, altrimenti
	 *         ritorna false
	 */
	public static boolean sceltaNuovoM(String messaggio) {
		InputController.getInstance();
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
		InputController.getInstance();
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
		InputController.getInstance();
		System.out.print(messaggio);
		int risultato = 0;
		risultato = i.nextInt();
		i.nextLine();
		switch (risultato) {
		default:
			System.out.println("Devi selezionare 1 per il piano d'ammortamento o 2 per il piano d'investimento");
			return apriPiano(messaggio);
		case 1:
			System.out.println("Hai aperto un piano d'ammortamento.");
			return Type.Ammortamento;
		case 2:
			System.out.println("Hai aperto un piano d'investimento.");
			return Type.Investimento;
		}
	}

	/**
	 * @param messaggio
	 * @return ritorna il conto corrente se risultato è = 1, altrimenti ritorna la
	 *         cassa In questo metodo scelgo dove fare Movimenti
	 */
	public static TipoConto inputConto(String messaggio) {
		InputController.getInstance();
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
	 * @return ritorna una stringa dell'utente che sarà il nome della categoria del
	 *         Movimento
	 */
	public static String inputString(String messaggio) {
		InputController.getInstance();
		System.out.print(messaggio);
		String descrizione = null;
		descrizione = i.nextLine();
		return descrizione;
	}

	/**
	 * @param asset
	 * @return ritorna il movimento che ho aggiunto alla lista In questa classe
	 *         aggiorno iil conto creando un nuovo Movimento
	 */
	public static Asset aggiornaConto(Asset asset) {
		double importo = InputController.inputInt("Scrivi l'importo da transitare: ");
		Categoria cat = new Categoria(InputController.inputString("Scrivi categoria: "));
		Date odierna = DateController.getDate();
		Movimento mov = new Movimento(cat, importo, odierna, asset.getMovimenti().size());
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
		double importoPiano = InputController.inputInt("Scrivi l'importo da aggiungere al piano: ");
		double importo = InputController.inputInt("Scrivi il tasso a regime: ");
		int durataPiano = (int) InputController.inputInt("Scrivi quanti mesi durerà il piano: ");
		Piano piano = new Piano(tipo, importoPiano, importo, durataPiano, DateController.getFinalDate(durataPiano),
				asset.getPiani().size());
		asset.aggiungiPiano(piano);
		System.out.println("Piano di tipo: " + tipo + "\nL'importo mensile del piano è: " + piano.importoMensile());
		return asset;
	}
}
