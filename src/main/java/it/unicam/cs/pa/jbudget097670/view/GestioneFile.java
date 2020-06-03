package it.unicam.cs.pa.jbudget097670.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Piano;
import it.unicam.cs.pa.jbudget097670.model.OperazioniPiano.Type;

import java.io.File;
import java.io.FileReader;

/**
 * @author Leonardo Mogianesi
 * In questa classe trasformo l'oggetto che il client manda al server in un JSON
 * Nel file Conti.txt ci stampo solamente il saldo che è disponibile nei conti
 */
public class GestioneFile {
	public static String fileNameMov = "\\src\\main\\java\\it\\unicam\\cs\\pa\\jbudget097670\\view\\Movimenti.json";
	public static String fileNameConti = "\\src\\main\\java\\it\\unicam\\cs\\pa\\jbudget097670\\view\\Conti.txt";
	
	/**
	 * @param asset
	 * @throws JsonIOException
	 * Questo metodo serve a trasformare l'oggetto asset in un JSON, usando la classe Gson
	 * GsonBuilder().setPrettyPrinting().create() permette di avere il JSON più leggibile
	 * il gson.toJson trasforma un oggetto in un JSON
	 */
	public static void scritturaFileMovimenti(Asset asset) throws JsonIOException{
		File file = new File("");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter writer = null;
		try {
			writer = new FileWriter(file.getAbsolutePath()+fileNameMov);
			String g = gson.toJson(asset);
			System.out.println(g);
			gson.toJson(asset, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * In questo metodo stampo il contenuto del JSON (convertito in una stringa) all'interno di un file txt
	 */
	public static void letturaFile() {
	    Gson gson = new Gson();
	    File file = new File("");
	    FileWriter writer;
        try (Reader reader = new FileReader(file.getAbsolutePath()+fileNameMov)){
            Asset asset = gson.fromJson(reader, Asset.class);
            System.out.println(asset.getTipoConto());
			writer = new FileWriter(file.getAbsolutePath()+fileNameConti);
			writer.write(asset.getTipoConto() + "\n\nSaldo: " + asset.getSaldoDisponibile() + "\nValuta: " + asset.getValuta() + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
