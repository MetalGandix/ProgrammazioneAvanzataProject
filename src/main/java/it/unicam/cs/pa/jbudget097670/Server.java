package it.unicam.cs.pa.jbudget097670;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Piano;
import it.unicam.cs.pa.jbudget097670.view.GestioneFile;

/**
 * @author Leonardo Mogianesi
 * 
 * Questa classe riceve un oggetto in input dal Client
 *
 */
public class Server implements Serializable {
	private static final long serialVersionUID = -1167949529498883236L;
	
	/**
	 * Creo un nuovo serverSocket
	 */
	ServerSocket server = null;
	Socket socket = null;

	/**
	 * Scelgo la porta dove far comunicare il Client con il Server
	 * @int porta = 4999 
	 */
	int porta = 4999;

	ObjectInputStream input;

	/**
	 * @return ritorna il server con l'oggetto ricevuto dal Client
	 * @throws ClassNotFoundException
	 */
	public Socket comunicazione() throws ClassNotFoundException {
		try {

			System.out.println("SERVER:");
			System.out.println("Inizializzo il server");
			
			/**
			 * Inizializzo il servizio del server sulla porta specificata
			 */
			server = new ServerSocket(porta);
			System.out.println("Ascolto sulla porta " + porta);
			
			while (true) {
				
				/**
				 * @server.accept Ascolta le richieste nella porta
				 */
				socket = server.accept();
				System.out.println("Connessione client-server stabilita");
			
				/**
				 * @input riceve in input l'oggetto dal Client
				 */
				input = new ObjectInputStream(socket.getInputStream());
			
				
				/**
				 * asset con il metodo .readObject() diventa l'oggetto che è stato letto dal server
				 */
				Asset asset = (Asset) input.readObject();
				System.out.println("Inserisco nel file txt Movimento.txt le informazioni dei miei conti con i loro Movimenti.\n");
				GestioneFile.scritturaFileMovimenti(asset);	
				GestioneFile.letturaFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return socket;
	}
	
	
	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * Qua faccio partire il Server
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		Server server = new Server();
		server.comunicazione();
	}
}
