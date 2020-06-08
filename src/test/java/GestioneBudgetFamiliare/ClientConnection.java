package GestioneBudgetFamiliare;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.TipoConto;

public class ClientConnection implements Serializable{

	private static final long serialVersionUID = -8964305154783898900L;
	static Socket socket = null;
	static int porta = 4999;
	public static ObjectOutputStream output;
	public static ObjectInputStream input;	
	static ServerSocket server = null; 

	@Test
	public void testConnectionClient() throws UnknownHostException, IOException, ClassNotFoundException {
		int a = 0;
		Asset asset = new Asset(TipoConto.CARTA_DI_CREDITO, a, '€');
		socket = new Socket(InetAddress.getLocalHost(), porta);
		output = new ObjectOutputStream(socket.getOutputStream());
		output.writeObject(asset);
		output.flush();
		output.close();
	}
}
