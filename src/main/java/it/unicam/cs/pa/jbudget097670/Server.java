package it.unicam.cs.pa.jbudget097670;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Serializable {
	private static final long serialVersionUID = -1167949529498883236L;
	ServerSocket server = null;
	Socket socket = null;
	
	private static String fileName = "C:\\Users\\User\\Desktop\\Repository GitHub\\JavaSocketPa\\ProgrammazioneAvanzataProject\\src\\main\\java\\it\\unicam\\cs\\pa\\jbudget097670\\Oggetto.txt";

	// Porta del Server
	int porta = 4999;

	// Permette di fare delle richieste
	ObjectInputStream input;

	// Permette di avere delle risposte
	ObjectOutputStream output;

	public Socket comunicazione() throws ClassNotFoundException {
		try {

			System.out.println("SERVER:");
			System.out.println("Inizializzo il server");
			
			// Inizializzo il servizio del Server
			server = new ServerSocket(porta);
			System.out.println("Ascolto sulla porta " + porta);
			
			while (true) {
				// Ascolta le richieste nella porta 4999
				socket = server.accept();
				System.out.println("Connessione client-server stabilita");
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());
				
				Asset asset = (Asset) input.readObject();
				System.out.println("Inserisco nel file txt Oggetto.txt le informazioni dei miei conti con i loro Movimenti.\n");
				
				File f = new File(fileName);
				FileWriter writer = new FileWriter(f);
				
				String riga = asset.getTipoConto() + "\nSaldo: " + asset.getSaldoDisponibile() + "\nValuta: " + asset.getValuta() + "\n";
				writer.write(riga + "\nLista Movimenti:\n\n" + asset.toString());
				writer.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return socket;
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		Server server = new Server();
		server.comunicazione();
	}
}
