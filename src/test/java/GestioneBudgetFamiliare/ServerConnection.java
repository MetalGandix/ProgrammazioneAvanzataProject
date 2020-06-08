package GestioneBudgetFamiliare;

import static org.junit.Assert.assertEquals;

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

public class ServerConnection implements Serializable{
	
	private static final long serialVersionUID = 7025389444165314616L;
	static Socket socket = null;
	static int porta = 4999;
	public static ObjectOutputStream output;
	public static ObjectInputStream input;
	static ServerSocket server = null; 
	
	@Test
	public void testConnectionServer() throws IOException, ClassNotFoundException {
			int a = 0;
			Asset asset2 = new Asset(TipoConto.CARTA_DI_CREDITO, a, '€');
			Asset asset1 = null;
			server = new ServerSocket(porta);
			socket = server.accept();
			input = new ObjectInputStream(socket.getInputStream());
			asset1 = (Asset) input.readObject();
			assertEquals(asset1, asset2);			
	}
	
}
