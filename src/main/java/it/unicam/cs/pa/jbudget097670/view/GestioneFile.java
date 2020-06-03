package it.unicam.cs.pa.jbudget097670.view;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import it.unicam.cs.pa.jbudget097670.interfaces.OperazioniPiano.Type;
import it.unicam.cs.pa.jbudget097670.model.Asset;
import it.unicam.cs.pa.jbudget097670.model.Piano;


import java.io.File;

public class GestioneFile {
	public static String fileNameMov = "\\src\\main\\java\\it\\unicam\\cs\\pa\\jbudget097670\\view\\Movimenti.json";
	public static String fileNameConti = "\\src\\main\\java\\it\\unicam\\cs\\pa\\jbudget097670\\view\\Conti.txt";
	public static String fileNamePiani = "\\src\\main\\java\\it\\unicam\\cs\\pa\\jbudget097670\\view\\Piani.txt";
	
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
	
	public static void scritturaFilePiani(Piano p) {
		File file = new File("");
		System.out.println(file.getAbsolutePath()+fileNamePiani);
		FileWriter writer;
		try {
			writer = new FileWriter(file.getAbsolutePath()+fileNamePiani);
			writer.write("\n" + p.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
		
	
	
	
}
