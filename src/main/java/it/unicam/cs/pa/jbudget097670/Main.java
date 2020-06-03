package it.unicam.cs.pa.jbudget097670;

import java.io.IOException;
import java.text.ParseException;
import it.unicam.cs.pa.jbudget097670.controller.InputController;

/**
 * @author Leonardo Mogianesi
 * 
 * Il main esegue il metodo start dell'inputController, che da all'utente la possibilità di scegliere come procedere
 *
 */
public class Main {
	public static void main(String[] args) throws IOException, ParseException {
		System.out.println("\nBenvenuto su GestioneBadgetPA");
		InputController.start(
				"Premi: \n 1)Se vuoi creare un piano e fare dei movimenti.\n 2)Creare un nuovo piano.\n 3)Fermare l'applicazione.\n 4)Guardare i tuoi asset.\n");
	}
}