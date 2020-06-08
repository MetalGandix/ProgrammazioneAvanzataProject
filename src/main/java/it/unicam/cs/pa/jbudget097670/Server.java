package it.unicam.cs.pa.jbudget097670;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.TipoConto;
import it.unicam.cs.pa.jbudget097670.view.GestioneFile;

/**
 * @author Leonardo Mogianesi
 * 
 * Questa classe riceve un oggetto in input dal Client
 * Creo un nuovo serverSocket
 * Scelgo la porta dove far comunicare il Client con il Server 
 * @int porta = 4999 
 */
public class Server implements Serializable {
	private static final long serialVersionUID = -1167949529498883236L;
	
	ServerSocket server = null; 
	Socket socket = null;
	int porta = 4999;
	ObjectInputStream input;
	ObjectOutputStream output;

	/**
	 * @return ritorna il server con l'oggetto ricevuto dal Client
	 * @throws ClassNotFoundException
	 * Inizializzo il servizio del server sulla porta specificata
	 * @server.accept Ascolta le richieste nella porta
	 * @input riceve in input l'oggetto dal Client
	 * asset con il metodo .readObject() diventa l'oggetto che è stato letto dal server
	 */
	public Socket comunicazione() throws ClassNotFoundException {
		try {
			System.out.println("SERVER: \nInizializzo il server");
			server = new ServerSocket(porta); 
			System.out.println("Ascolto sulla porta " + porta);
			Asset asset = null;
			while (true) {
				socket = server.accept();
				System.out.println("Connessione client-server stabilita");
				outPutServer(asset);
				input = new ObjectInputStream(socket.getInputStream());
				asset = (Asset) input.readObject();
				if(asset.getTipoConto() == TipoConto.CARTA_DI_CREDITO) {
				System.out.println("Inserisco nel file txt Movimento.txt le informazioni dei miei conti con i loro Movimenti.\n");
				GestioneFile.scritturaFileMovimenti(asset);	
				GestioneFile.letturaFileCassa();
				}else {
					System.out.println("Inserisco nel file txt Movimento.txt le informazioni dei miei conti con i loro Movimenti.\n");
					GestioneFile.scritturaFileMovimenti(asset);	
					GestioneFile.letturaFileCassa();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return socket;
	}
	
	/**
	 * @param asset
	 * @throws IOException
	 * @output manda al Client l'oggetto che legge nel file Json
	 */
	public void outPutServer(Asset asset) throws IOException {
		if(asset == null) {
			asset = GestioneFile.letturaFileCassa();
			if(asset.getTipoConto() == TipoConto.CARTA_DI_CREDITO) {
			output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject(asset);
			}
			output = new ObjectOutputStream(socket.getOutputStream());
			asset = GestioneFile.letturaFileContoCorrente();
			output.writeObject(asset);
		}
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
