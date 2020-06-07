package it.unicam.cs.pa.jbudget097670;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.view.GestioneInput;

/**
 * @author Leonardo Mogianesi
 * 
 * Questa classe manda gli oggetti Movimento e Piano al server
 * Il serializableUID serve per dire al programma per capire quali classi invieranno e riceveranno oggetti
 * Dichiaro un nuovo socket e la porta dove scambieranno dati client e server
 * @output ObjectOutPutStream è il tipo che manda al server un dato
 */
public class Client implements Serializable {
	
	private static final long serialVersionUID = -1167949529498883236L;
	Socket socket = null;
	int porta = 4999;
	
	public ObjectOutputStream output;
	public ObjectInputStream input;
	
	/**
	 * @return ritorna legge l'asset dal Server
	 */
	public Asset leggiOggetto() {
			Asset asset = null;
			try {
				input = new ObjectInputStream(socket.getInputStream());
				asset = (Asset) input.readObject();
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("Errore");
			}
			return asset;
	}
	
	/**
	 * In questo metodo viene connesso il client al server sulla porta 4999
	 */
	public Socket connetti() {
		try {
			socket = new Socket(InetAddress.getLocalHost(), porta);
			System.out.println("CLIENT: Connesso alla porta: " + porta + "\n");
			output = new ObjectOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			System.err.println("Impossibile identificare l'host");
		} catch (IOException e) {
			System.err.println("Impossibile stabilire una connessione");
		}
		return socket;
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
		GestioneInput in = new GestioneInput();
		System.out.println("-- Benvenuto su GestioneBadgetPA --\n");
		in.start(
				"- HOME -"
				+ "\nPremi: " 
				+ "\n 1)Se vuoi creare un conto e fare dei movimenti."
				+ "\n 2)Creare un nuovo piano."
				+ "\n 3)Fermare l'applicazione."
				+ "\n 4)Per mandare i cambiamenti al server."
				+ "\n 5)Seleziona un Movimento o un Piano tra quelli creati. "
				+ "\n");
	} 
}
