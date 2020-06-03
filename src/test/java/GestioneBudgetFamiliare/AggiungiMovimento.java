package GestioneBudgetFamiliare;

import static org.junit.Assert.*;
import java.util.Iterator;
import it.unicam.cs.pa.jbudget097670.*;
import it.unicam.cs.pa.jbudget097670.interfaces.TipoConto;
import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Movimento;

import org.junit.Test;

public class AggiungiMovimento {

	@Test
	public void test() {
		Asset contoCorrente = new Asset(TipoConto.CONTO_CORRENTE, 0, 0, '�', 0);
		Movimento mov = new Movimento();
		contoCorrente.aggiungiMovimento(mov);
		Iterator<Movimento> iter = contoCorrente.getMovimenti().iterator();
		while(iter.hasNext()) {
			assertEquals(iter.next(), mov);
		}
	}
}