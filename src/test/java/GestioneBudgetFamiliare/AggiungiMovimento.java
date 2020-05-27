package GestioneBudgetFamiliare;

import static org.junit.Assert.*;
import java.util.Iterator;
import it.unicam.cs.pa.jbudget097670.*;
import org.junit.Test;

public class AggiungiMovimento {

	@Test
	public void test() {
		Asset contoCorrente = new Asset(null, tipoConto.CONTO_CORRENTE, 0, 0, '€', 0);
		Movimento mov = new Movimento();
		contoCorrente.aggiungiMovimento(mov);
		Iterator<Movimento> iter = contoCorrente.getMovimenti().iterator();
		while(iter.hasNext()) {
			assertEquals(iter.next(), mov);
		}
	}
}