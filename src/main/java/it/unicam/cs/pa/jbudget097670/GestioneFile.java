package it.unicam.cs.pa.jbudget097670;

import java.io.FileWriter;
import java.io.IOException;
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
			writer = new FileWriter(GestioneFile.pathAssoluta());
			String riga = asset.getTipoConto() + "\nSaldo: " + asset.getSaldoDisponibile() + "\nValuta: " + asset.getValuta() + "\n";
			writer.write(riga + "\nLista Movimenti:\n\n" + asset.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
