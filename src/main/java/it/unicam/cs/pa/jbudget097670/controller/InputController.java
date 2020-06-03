package it.unicam.cs.pa.jbudget097670.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import it.unicam.cs.pa.jbudget097670.Client;
import it.unicam.cs.pa.jbudget097670.interfaces.OperazioniPiano;
import it.unicam.cs.pa.jbudget097670.interfaces.TipoConto;
import it.unicam.cs.pa.jbudget097670.interfaces.OperazioniPiano.Type;
import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Categoria;
import it.unicam.cs.pa.jbudget097670.model.Movimento;
import it.unicam.cs.pa.jbudget097670.model.Piano;

public class InputController {
	public static Scanner i = null;

	// Creo un nuovo scanner
	public static void getInstance() {
		if (i == null)
			i = new Scanner(System.in);
	}
	
	public static void start(String messaggio) throws IOException {
		InputController.getInstance();
		Client c = new Client();
		c.connetti();
		Asset contoCorrente = new Asset(null, TipoConto.CONTO_CORRENTE, 0, 0, '€', 0);
		Asset cassa = new Asset(null, TipoConto.CASSA, 0, 0, '€', 0);
		TipoConto tipo = null;
		while(true) {
			System.out.print(messaggio);
			int risultato = 0;
			risultato = i.nextInt();
			i.nextLine();
			if(risultato != 1 && tipo == null) {
				System.out.println("Devi prima creare il conto.");
				continue;
			}
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
	

	// Inserisco il double per l'importo da aggiungere o rimuovere nel saldo
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

	
	public static TipoConto creaMovimento(Asset contoCorrente, Asset cassa) {
		TipoConto tipo = null;
		tipo = InputController
				.inputConto("Digita il conto che vuoi utilizzare: 1) ContoCorrente, 2) Cassa\n ");
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
	
	public static void aggiungiPiani(Asset contoCorrente, Asset cassa) {
		TipoConto tipo = InputController.inputConto("Digita il conto che vuoi utilizzare: 1) ContoCorrente, 2) Cassa\n ");
		while (true) {
			OperazioniPiano.Type tipoPiano = InputController.apriPiano(
					"Premi 1 se vuoi creare un piano d'ammortamento o premi 2 se vuoi creare un piano d'investimento: ");
			switch(tipo) {
			case CASSA:
				InputController.aggiornaPiano(cassa, tipoPiano);
				break;
			default:
			case CONTO_CORRENTE:
				InputController.aggiornaPiano(contoCorrente, tipoPiano);
				break;
			}
			boolean continuaPiano = InputController.sceltaNuovoM("Digita 1 per creare un altro piano o 2 per fermarti qua. \n");
			if (!continuaPiano) {
				break;
			}
		}
	}
	
	public static void stampaRisultati(TipoConto tipo, Client c, Asset contoCorrente, Asset cassa) throws IOException {
			/*
			 * contoCorrente.getMovimentiperCategoria(new
			 * Categoria(InputController.inputString("Scegli quale categoria raggruppare."))
			 * ).forEach(movimento->{ System.out.println(movimento); });
			 */
			// Mando dal Client al Server l'oggetto
			if(tipo == TipoConto.CONTO_CORRENTE) {
			System.out.println("I movimenti del conto corrente sono: ");
			System.out.println(contoCorrente);
			
			}else {
			System.out.println("I movimenti della cassa sono: ");
			// Quando stampo l'oggetto, stampo in realtà il toString che ho creato nella classe Asset
			System.out.println(cassa);
			c.output.writeObject(cassa);
			}
			c.output.flush();
			c.output.close();
	}

	// Scelgo se rifare o no un movimento/piano
	public static boolean sceltaNuovoM(String messaggio) {
		InputController.getInstance();
		System.out.print(messaggio);
		int risultato = 0;
		risultato = i.nextInt();
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

	// Scelgo se aggiungere un nuovo piano al mio conto
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

	// Scelgo il tipo di piano
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

	// Qua scelgo quale conto utilizzare tra C.C. e la cassa
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

	// Questo metodo serve per scegliere la categoria
	public static String inputString(String messaggio) {
		InputController.getInstance();
		System.out.print(messaggio);
		String descrizione = null;
		descrizione = i.nextLine();
		return descrizione;
	}

	// Questo metodo serve per creare un nuovo movimento e di conseguenza aggiornare
	// il conto
	public static Asset aggiornaConto(Asset c) {
		double importo = InputController.inputInt("Scrivi l'importo da transitare: ");
		Categoria cat = new Categoria(InputController.inputString("Scrivi categoria: "));
		Date odierna = DateController.getDate();
		Movimento mov = new Movimento(cat, importo, odierna, c.getMovimenti().size());
		c.aggiungiMovimento(mov);
		System.out.println("Importo transitato nel Movimento: " + mov.getImporto());
		System.out.println("Movimento con categoria: " + mov.getTipoCategoria());
		System.out.println("Movimento effettuato in data: " + mov.getData());
		System.out.println("Movimento con ID: " + mov.getId());
		return c;
	}

	public static Asset aggiornaPiano(Asset c, Type tipo) {
		double importoPiano = InputController.inputInt("Scrivi l'importo da aggiungere al piano: ");
		double importo = InputController.inputInt("Scrivi il tasso a regime: ");
		int durataPiano = (int) InputController.inputInt("Scrivi quanti mesi durerà il piano: ");
		Piano piano = new Piano(tipo, importoPiano, importo, durataPiano, DateController.getFinalDate(durataPiano),
				c.getPiani().size());
		c.aggiungiPiano(piano);
		System.out.println("Piano di tipo: " + tipo + "\nL'importo mensile del piano è: " + piano.importoMensile());
		return c;
	}
}
