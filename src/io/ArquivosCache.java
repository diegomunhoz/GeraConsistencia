package io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArquivosCache {
	
	static List<File> listEntrada = new ArrayList<File>();
	static List<File> listSaida = new ArrayList<File>();
	
	public static List<File> getListEntrada() {
		return listEntrada;
	}
	public static void setListEntrada(List<File> listEntrada) {
		ArquivosCache.listEntrada = listEntrada;
	}
	public static List<File> getListSaida() {
		return listSaida;
	}
	public static void setListSaida(List<File> listSaida) {
		ArquivosCache.listSaida = listSaida;
	}
	 

}
