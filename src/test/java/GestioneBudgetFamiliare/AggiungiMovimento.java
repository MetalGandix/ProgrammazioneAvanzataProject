package GestioneBudgetFamiliare;

import static org.junit.Assert.*;
import java.util.Iterator;
import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Movimento;
import it.unicam.cs.pa.jbudget097670.model.TipoConto;

import org.junit.Test;

public class AggiungiMovimento {

	@Test
	public void testContoCorrente() {
		Asset contoCorrente = new Asset(TipoConto.CONTO_CORRENTE, 0, '€');
		Movimento mov = new Movimento(null, 0, null, 0);
		contoCorrente.aggiungiMovimento(mov);
		Iterator<Movimento> iter = contoCorrente.getMovimenti().iterator();
		while(iter.hasNext()) {
			assertEquals(iter.next(), mov);
		}
	} 
	
	@Test
	public void testCassa() {
		Asset contoCorrente = new Asset(TipoConto.CARTA_DI_CREDITO, 0, '€');
		Movimento mov = new Movimento(null, 0, null, 0);
		contoCorrente.aggiungiMovimento(mov);
		Iterator<Movimento> iter = contoCorrente.getMovimenti().iterator();
		while(iter.hasNext()) {
			assertEquals(iter.next(), mov);
		}
	} 
	
	
}