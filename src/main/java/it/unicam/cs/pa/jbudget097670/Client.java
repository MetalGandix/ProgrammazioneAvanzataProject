package it.unicam.cs.pa.jbudget097670;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;

import it.unicam.cs.pa.jbudget097670.controller.OggettiController;
import it.unicam.cs.pa.jbudget097670.view.GestioneInput;

/**
 * @author Leonardo Mogianesi
 * 
 * Questa classe manda gli oggetti Movimento e Piano al server
 *
 */
public class Client implements Serializable {
	/**
	 * Il serializableUID serve per dire al programma per capire quali classi invieranno e riceveranno oggetti
	 */
	private static final long serialVersionUID = -1167949529498883236L;
	
	/**
	 * Dichiaro un nuovo socket e la porta dove scambieranno dati client e server
	 */
	Socket socket = null;
	int porta = 4999;
	
	/**
	 * ObjectOutPutStream è quello che manda al server un dato
	 */
	public ObjectOutputStream output;

	/**
	 * In questo metodo viene connesso il client al server sulla porta 4999
	 */
	public void connetti() {
		try {
			System.out.println("CLIENT:");
			System.out.println("Connessione al server con porta: " + porta);
			// Inizializzo il servizio del Server
			socket = new Socket(InetAddress.getLocalHost(), porta);
			System.out.println("Connesso");
			// Connesso
			output = new ObjectOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			System.err.println("Impossibile identificare l'host");
		} catch (IOException e) {
			System.err.println("Impossibile stabilire una connessione");
		}
	}
	
	/**
	 * @return ritorna il dato che sarà l'output da mandare al server
	 */
	public ObjectOutputStream output() {
		return output;
	}
	
	/**
	 * @author Leonardo Mogianesi
	 * Il main esegue il metodo start dell'inputController, che da all'utente la possibilità di scegliere come procedere
	 */
	public static void main(String[] args) throws IOException, ParseException {
		System.out.println("-- Benvenuto su GestioneBadgetPA --\n");
		GestioneInput.start(
				"Premi: \n 1)Se vuoi creare un conto e fare dei movimenti.\n 2)Creare un nuovo piano.\n 3)Fermare l'applicazione.\n 4)Guardare i tuoi asset.\n");
	}
}
