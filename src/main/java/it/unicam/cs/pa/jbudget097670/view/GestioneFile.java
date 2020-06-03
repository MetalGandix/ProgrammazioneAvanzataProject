package it.unicam.cs.pa.jbudget097670.view;

import java.io.FileWriter;
import java.io.IOException;

import it.unicam.cs.pa.jbudget097670.interfaces.OperazioniPiano.Type;
import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Piano;

import java.io.File;

public class GestioneFile {
	public static String fileNameMov = "\\src\\main\\java\\it\\unicam\\cs\\pa\\jbudget097670\\view\\Movimenti.txt";
	public static String fileNameConti = "\\src\\main\\java\\it\\unicam\\cs\\pa\\jbudget097670\\view\\Conti.txt";
	public static String fileNamePiani = "\\src\\main\\java\\it\\unicam\\cs\\pa\\jbudget097670\\view\\Piani.txt";
	
	public static void scritturaFileMovimenti(Asset asset) {
		File file = new File("");
		System.out.println(file.getAbsolutePath()+fileNameMov);
		FileWriter writer;
		try {
			writer = new FileWriter(file.getAbsolutePath()+fileNameMov);
			String rigaMovimento = asset.getTipoConto() + "\n\nSaldo: " + asset.getSaldoDisponibile() + "\nValuta: " + asset.getValuta() + "\n";
			writer.write(rigaMovimento + "\n" + asset.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public static void scritturaFilePiani(Piano p) {
		File file = new File("");
		System.out.println(file.getAbsolutePath()+fileNamePiani);
		FileWriter writer;
		try {
			writer = new FileWriter(file.getAbsolutePath()+fileNameMov);
			writer.write("\n" + p.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
		
	
	
	
}
