package GestioneBudgetFamiliare;

import static org.junit.Assert.*;
import java.util.Iterator;
import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Movimento;
import it.unicam.cs.pa.jbudget097670.model.TipoConto;

import org.junit.Test;

public class AggiungiMovimento {

	@Test
	public void testConti() {
		Asset contoCorrente = new Asset(TipoConto.CONTO_CORRENTE, 0, '€');
		Asset cassa = new Asset(TipoConto.CARTA_DI_CREDITO, 0, '€');
		Movimento mov = new Movimento(null, 0, null, 0); 
		contoCorrente.aggiungiMovimento(mov);
		Iterator<Movimento> iter1 = contoCorrente.getMovimenti().iterator();
		Iterator<Movimento> iter2 = cassa.getMovimenti().iterator();
		while(iter1.hasNext()) {
			assertEquals(iter1.next(), mov);
		}
		while(iter2.hasNext()) {
			assertEquals(iter2.next(), mov); 
		}
	} 	
	
}