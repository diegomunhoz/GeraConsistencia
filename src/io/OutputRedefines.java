package io;

import java.util.ArrayList;
import java.util.List;

import modelos.ArquivoCblTO;

public class OutputRedefines {

	private ArquivoCblTO arq = new ArquivoCblTO();
	private ArquivoOutput saida = new ArquivoOutput();
	
	public OutputRedefines(){}
	
	public OutputRedefines(ArrayList<String> lista1, ArrayList<String> lista2) throws Exception{
		gravaArquivoSaida(geraStringBuffer(lista1, lista2));
	}
	
	private StringBuffer geraStringBuffer(ArrayList<String> lista1, ArrayList<String> lista2){
		StringBuffer str = new StringBuffer();
		
		for (String a : lista1){
			str.append(a);
		}
		
		str.append("\n");
		
		for (String b : lista2){
			str.append(b);
		}
		return str;
	}
	
	private void gravaArquivoSaida(StringBuffer str) throws Exception{
		List<ArquivoCblTO> lista = new ArrayList<ArquivoCblTO>();
		arq.setArquivoEmString(str);
		arq.setNomeArquivo("Redefinições.txt");
		lista.add(arq);
		saida.salvarArquivo(lista);
	}
	
}
