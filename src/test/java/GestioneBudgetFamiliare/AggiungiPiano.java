package GestioneBudgetFamiliare;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import it.unicam.cs.pa.jbudget097670.controller.DateController;
import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.OperazioniPianoInterface.Type;
import it.unicam.cs.pa.jbudget097670.model.Piano;
import it.unicam.cs.pa.jbudget097670.model.TipoConto;

public class AggiungiPiano {
	
	@Test
	public void testAggiuntaPiano() {
		DateController d = new DateController();
		int mesi = 0;
		Asset contoCorrente = new Asset(TipoConto.CONTO_CORRENTE, 0, '€');
		Piano p1 = new Piano(Type.Ammortamento, 0, 0, 0, d.getDate(), d.getFinalDate(mesi), 0); 
		Piano p2 = new Piano(Type.Investimento, 0, 0, 0, d.getDate(), d.getFinalDate(mesi), 0); 
		contoCorrente.aggiungiPiano(p1);
		contoCorrente.aggiungiPiano(p2);
		Iterator<Piano> iter = contoCorrente.getPiani().iterator();
		while(iter.hasNext()) {
			assertEquals(iter.next(), p1);
			assertEquals(iter.next(), p2);
		}
	}

}
