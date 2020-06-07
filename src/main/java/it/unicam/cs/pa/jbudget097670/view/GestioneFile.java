package it.unicam.cs.pa.jbudget097670.view;

import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.TipoConto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author Leonardo Mogianesi In questa classe trasformo l'oggetto che il client
 *         manda al server in un JSON Nel file Conti.txt ci stampo solamente il
 *         saldo che è disponibile nei conti
 */
public class GestioneFile {
	public static String fileNameMov = "\\src\\main\\java\\it\\unicam\\cs\\pa\\jbudget097670\\view\\CartaDiCredito.json";
	public static String fileNameConti = "\\src\\main\\java\\it\\unicam\\cs\\pa\\jbudget097670\\view\\ContoCorrente.json";
 
	/**
	 * @param asset
	 * @throws JsonIOException Questo metodo serve a trasformare l'oggetto asset in
	 *                         un JSON, usando la classe Gson
	 *                         GsonBuilder().setPrettyPrinting().create() permette
	 *                         di avere il JSON più leggibile il gson.toJson
	 *                         trasforma un oggetto in un JSON
	 */
	public static void scritturaFileMovimenti(Asset asset) throws JsonIOException { 
		File file = new File("");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter writer = null;
		try { 
			if(asset.getTipoConto() == TipoConto.CARTA_DI_CREDITO) {
			writer = new FileWriter(file.getAbsolutePath() + fileNameMov);
			String g = gson.toJson(asset);
			System.out.println(g);
			gson.toJson(asset, writer);
			}else {
				writer = new FileWriter(file.getAbsolutePath() + fileNameConti);
				String g = gson.toJson(asset);
				System.out.println(g);
				gson.toJson(asset, writer);
			}
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
	 * @return ritorno il file.json 
	 */
	public static Asset letturaFileCassa() {
		File file = new File("");
		String path;
			path = file.getAbsolutePath() + fileNameMov;
        BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			System.out.println("Il file non esiste");
			e.printStackTrace();
		}
        Gson gson = new Gson();
        Asset asset = gson.fromJson(bufferedReader, Asset.class);
		return asset;
	}
	
	/**
	 * @return stampo il file 
	 */
	public static Asset letturaFileContoCorrente() {
		File file = new File("");
		String path;
			path = file.getAbsolutePath() + fileNameConti;
        BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			System.out.println("Il file non esiste");
			e.printStackTrace();
		}
        Gson gson = new Gson();
        Asset asset = gson.fromJson(bufferedReader, Asset.class);
	
		return asset;
	}
}
