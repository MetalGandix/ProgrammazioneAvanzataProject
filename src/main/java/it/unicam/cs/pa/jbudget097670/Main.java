package it.unicam.cs.pa.jbudget097670;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.Date;
import it.unicam.cs.pa.jbudget097670.gestisciMovimento.Type;

public class Main {
	public static void main(String[] args) throws IOException, ParseException {

		// Connessione client-server
		Client c = new Client();
		c.connetti();

		// Conti disponibili
		Asset contoCorrente = new Asset(null, tipoConto.CONTO_CORRENTE, 0, 0, '€', 0);
		Asset cassa = new Asset(null, tipoConto.CASSA, 0, 0, '€', 0);

		/*System.out.println("\nBenvenuto su GestioneBadgetPA\n");
		tipoConto tipo = InputController.start(
				"Premi: \n 1)Se vuoi fare fare un movimento su un asset.\n 2)Creare un nuovo piano.\n 3)Fermare l'applicazione.\n 4)Guardare i tuoi asset.\n", contoCorrente, cassa);*/
		
		tipoConto tipo = InputController
				.inputConto("Digita il conto che vuoi utilizzare: 1) ContoCorrente, 2) Cassa\n ");
		while (true) {
			if (tipo == tipoConto.CONTO_CORRENTE)
				contoCorrente = InputController.aggiornaConto(contoCorrente);
			else
				cassa = InputController.aggiornaConto(cassa);
			boolean continuaMovimento = InputController
					.sceltaNuovoM("Digita 1 per creare un altro movimento o 2 per fermarti qua. \n");
			if (!continuaMovimento) {
				break;
			}
		}

		while (true) {
			boolean iniziaPiano = InputController
					.sceltaNuovoP("Digita 1 se vuoi aprire un piano, altrimenti digita 2.");
			if (!iniziaPiano) {
				break;
			}
			OperazioniPiano.Type tipoPiano = InputController.apriPiano(
					"Premi 1 se vuoi creare un piano d'ammortamento o premi 2 se vuoi creare un piano d'investimento: ");
			if (tipoPiano == OperazioniPiano.Type.Ammortamento)
				contoCorrente = InputController.aggiornaPiano(contoCorrente, tipoPiano.Ammortamento);
			else
				cassa = InputController.aggiornaPiano(cassa, tipoPiano.Investimento);
			boolean continuaPiano = InputController.sceltaNuovoM("Digita 1 per crearlo o 2 per fermarti qua. \n");
			if (!continuaPiano) {
				break;
			}
		}

		// Quando stampo l'oggetto, stampo in realtà il toString che ho creato nella
		if (tipo == tipoConto.CONTO_CORRENTE) {
			/*
			 * contoCorrente.getMovimentiperCategoria(new
			 * Categoria(InputController.inputString("Scegli quale categoria raggruppare."))
			 * ).forEach(movimento->{ System.out.println(movimento); });
			 */
			System.out.println("I movimenti del conto corrente sono: ");
			System.out.println(contoCorrente);
			// Mando dal Client al Server l'oggetto
			c.output.writeObject(contoCorrente);
		} else {
			/*
			 * cassa.getMovimentiperCategoria(new
			 * Categoria(InputController.inputString("Scegli quale categoria raggruppare."))
			 * ).forEach(movimento->{ System.out.println(movimento); });
			 */
			System.out.println("I movimenti della cassa sono: ");
			System.out.println(cassa);
			c.output.writeObject(cassa);
		}

		c.output.flush();
		c.output.close();
	}
}
