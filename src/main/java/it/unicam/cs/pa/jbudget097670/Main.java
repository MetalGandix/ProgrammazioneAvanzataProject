package it.unicam.cs.pa.jbudget097670;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.Date;

import it.unicam.cs.pa.jbudget097670.controller.InputController;
import it.unicam.cs.pa.jbudget097670.interfaces.gestisciMovimento.Type;

public class Main {
	public static void main(String[] args) throws IOException, ParseException {

		System.out.println("\nBenvenuto su GestioneBadgetPA");
		InputController.start(
				"Premi: \n 1)Se vuoi creare un piano e fare dei movimenti.\n 2)Creare un nuovo piano.\n 3)Fermare l'applicazione.\n 4)Guardare i tuoi asset.\n");
	}
}
