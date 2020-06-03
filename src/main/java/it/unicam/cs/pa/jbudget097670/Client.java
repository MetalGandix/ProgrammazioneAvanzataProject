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

public class Client implements Serializable {
	private static final long serialVersionUID = -1167949529498883236L;
	Socket socket = null;
	int porta = 4999;

	// Permette di fare delle richieste
	ObjectInputStream input;

	// Permette di avere delle risposte
	public ObjectOutputStream output;

	public void connetti() {
		
		try {
			System.out.println("CLIENT:");
			System.out.println("Connessione al server con porta: " + porta);
			// Inizializzo il servizio del Server
			socket = new Socket(InetAddress.getLocalHost(), porta);
			System.out.println("Connesso");
			// Connesso
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println("Impossibile identificare l'host");
		} catch (IOException e) {
			System.err.println("Impossibile stabilire una connessione");
		}
	}

	public ObjectInputStream input() {
		return input;
	}
	public ObjectOutputStream output() {
		return output;
	}
}
