package it.unicam.cs.pa.jbudget097670;

import java.io.FileWriter;
import java.io.IOException;

import it.unicam.cs.pa.jbudget097670.OperazioniPiano.Type;

import java.io.File;

public class GestioneFile {
	public static String fileName = "\\src\\main\\java\\it\\unicam\\cs\\pa\\jbudget097670\\Oggetto.txt";
	
	public static String pathAssoluta() { 
		File file = new File("");
		System.out.println(file.getAbsolutePath()+fileName);
		return file.getAbsolutePath()+fileName;
	}
	
	public static void scritturaFile(Asset asset) {
		FileWriter writer;
		try {
			writer = new FileWriter(GestioneFile.pathAssoluta(), true);
			String rigaMovimento = asset.getTipoConto() + "\n\nSaldo: " + asset.getSaldoDisponibile() + "\nValuta: " + asset.getValuta() + "\n";
			writer.write(rigaMovimento + "\n" + asset.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
