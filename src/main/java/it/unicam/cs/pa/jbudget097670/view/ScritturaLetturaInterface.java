package it.unicam.cs.pa.jbudget097670.view;

import it.unicam.cs.pa.jbudget097670.model.Asset;

public interface ScritturaLetturaInterface {

	/**
	 * Scrive l'oggetto con al suo interno i movimenti nel file Json
	 */
	public void scritturaFileMovimenti();
	
	/**
	 * @return ritorna l'oggetto contenuto nel json cartaDiCredito
	 */
	public Asset letturaFileCassa();
	
	/**
	 * @return ritorna l'oggetto contenuto nel json contoCorrente
	 */
	public Asset letturaFileContoCorrente();
	
}
