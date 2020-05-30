package it.unicam.cs.pa.jbudget097670;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import it.unicam.cs.pa.jbudget097670.OperazioniPiano.Type;

public class InputController {
	public static Scanner i = null;

	// Creo un nuovo scanner
	public static void getInstance() {
		if (i == null)
			i = new Scanner(System.in);
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
	public static tipoConto inputConto(String messaggio) {
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
			return tipoConto.CONTO_CORRENTE;
		case 2:
			System.out.println("Hai scelto la cassa.\n");
			return tipoConto.CASSA;
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
			Piano piano = new Piano(tipo, importoPiano,importo, durataPiano, DateController.getFinalDate(), c.getPiano().size());
			System.out.println("Piano di tipo: " + tipo + "\nL'importo mensile del piano è: " + piano.importoMensile());
		return c;
	}
}
