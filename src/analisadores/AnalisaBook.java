package analisadores;

import java.awt.DisplayMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modelos.BookTO;
import modelos.VariavelTO;
import util.MensagensFactory;

public class AnalisaBook {

	private String[] linhaAux;
	private List<VariavelTO> vars = new ArrayList<VariavelTO>();
	private BookTO book;
	private Set<Integer> listaNiveis = new HashSet<Integer>();;
	VariavelTO aux;

	public BookTO ler(String nomeBook, List<String> arq) throws Exception {
		return leituraBook(nomeBook, arq);
	}
	public List<String> carregarCoordenador(List<String> arq) throws Exception {
		return armazenaVariaveisCoordenador(arq);
	}

	public List<String> carregaDclgen(List<String> arq) throws Exception {
		return armazenaVariaveisDclgen(arq);
	}

	public List<String> carregaDclgenNulos(List<String> arq) throws Exception {
		return armazenaVariaveisNulas(arq);
	}
/**
	 * Metodo para leitura de books
	 * @throws Exception 
	 */

	private BookTO leituraBook(String nomeBook, List<String> arq) throws Exception {
		nomeBook = nomeBook.replace(".CPY", "");
		for (String linha : arq) {
			if (!linha.trim().equals("")){
				if(!linha.trim().startsWith("*")){
					try{
						aux = new VariavelTO();
						// separando o nivel da variavel do resto da linha	
						aux.setNivel(encontraNivel(linha));
						listaNiveis.add(aux.getNivel());
						// lendo o nome da variavel
						aux.setNome(encontraNome());
						if(linha.contains("PIC")){
							// separando a palavra "PIC" do restante da linha e lendo o tipo
							// da variavel
							aux.setPic(encontraPic());
							detalhaPic();
						}else{
							if(aux.getNivel() == 88){
								aux.setIsItenDeGrupo(false);
							}else{
								aux.setIsItenDeGrupo(true);
							}		
						}
						vars.add(aux);					
					}catch(NumberFormatException e){
						throw new Exception(MensagensFactory.mensagemDeErroPicDesalinhada);
					}
				}
			}
		}

		book = new BookTO();
		book.setNome(nomeBook);
		picVarDeGrupo();
		book.setVariaveis(vars);
		return book;
	}

	private List<String> armazenaVariaveisCoordenador(List<String> arq) throws Exception {
		List<String> listaCampos = new ArrayList<String>();
		boolean occurs = false;
		for (String campo : arq) {
			if (!campo.contains("*") && !campo.contains("COD-LAYOUT")
					&& !campo.isEmpty()
					&& !campo.contains("TAM-LAYOUT")
					&& !campo.contains("E-REGISTRO")
					&& !campo.contains("E-HEADER")
					&& !campo.contains("E-BLOCO-ENTRADA")) {
				if (campo.contains("OCCURS")
						&& !campo.contains("MAX-OCCURS")) {
					occurs = true;
				}
				if (campo.contains("9(") || campo.contains("X(")) {
					if (occurs == true) {
						campo = campo + " OCCURS";
						listaCampos.add(campo.trim());
					} else {
						listaCampos.add(campo.trim());
					}
				}
			}
		}
		return listaCampos;
	}

	private List<String> armazenaVariaveisDclgen(List<String> arq) throws Exception {
		List<String> listaCampos = new ArrayList<String>();
		for (String campo : arq) {
			if (campo.contains("9(") || campo.contains("X(")) {
				listaCampos.add(campo.trim());				
			}
		}
		return listaCampos;
	}
	
	private List<String> armazenaVariaveisNulas(List<String> arq) throws Exception {
		List<String> listaCampos = new ArrayList<String>();
		for (String campo : arq) {
			if (campo.contains("_")) {
				if (!campo.contains("NOT NULL") && !campo.contains("DB2PRD")) {
					listaCampos.add(campo.trim());				
				}
			}
		}
		return listaCampos;
	}
	
	private int encontraNivel(String linha){
		linhaAux = linha.trim().replaceFirst(" ", "¥").split("¥");
		return Integer.parseInt(linhaAux[0]);
	}

	private String encontraNome(){
		linhaAux = linhaAux[1].trim().replaceFirst(" ", "¥").split("¥");
		if(linhaAux[0].endsWith(".")){
			linhaAux[0] = linhaAux[0].replace(".", "");
		}
		return linhaAux[0];
	}

	private String encontraPic(){
		linhaAux[1] = linhaAux[1].trim().substring(4);
		linhaAux = linhaAux[1].trim().replaceFirst(" ", "¥").replace(
				".", "¥").split("¥");
		if (linhaAux[0].contains("S")) {
			aux.setIsSinalizado(true);			
			linhaAux[0] = linhaAux[0].replace("S", "").trim();
		}
		if (linhaAux[0].endsWith("V")){
			linhaAux[0] = linhaAux[0].replace("V", "").trim();
		}
		return linhaAux[0];
	}

	private void detalhaPic(){
		if(aux.getPic().startsWith("X")){
			aux.isAlfaNumerica(true);
			if(aux.getPic().contains("(")){
				aux.setInteiro(Integer.parseInt(aux.getPic().replace("X", "").replace("(", "").replace(")", "")));	
			}else{
				aux.setInteiro(aux.getPic().length());
			}
		} else if(aux.getPic().startsWith("9")){
			aux.setIsNumerica(true);
			if(aux.getPic().contains("V")){
				aux.setHaveDecimais(true);
				if(aux.getPic().split("V")[0].contains("(")){
					aux.setInteiro(Integer.parseInt(aux.getPic().split("V")[0].replaceFirst("9", "").replace("(", "").replace(")", "")));	
				}else{
					aux.setInteiro(aux.getPic().split("V")[0].length());
				}										
				if(aux.getPic().split("V")[1].contains("(")){
					aux.setDecimais(Integer.parseInt(aux.getPic().split("V")[1].replaceFirst("9", "").replace("(", "").replace(")", "")));	
				}else{
					aux.setDecimais(aux.getPic().split("V")[1].length());
				}								
			}else{
				if(aux.getPic().contains("(")){
					aux.setInteiro(Integer.parseInt(aux.getPic().replaceFirst("9", "").replace("(", "").replace(")", "")));	
				}else{
					aux.setInteiro(aux.getPic().length());
				}
			}
		}
	}

	private void picVarDeGrupo(){
		int soma=0, i=0, j;
		String pic="";
		for (VariavelTO v : vars){
			if(v.isItenDeGrupo()){
				j=i+1;
				while (j <= vars.size()-1){
					if(vars.get(j).getNivel() != 88){
						if(!vars.get(j).isItenDeGrupo()){
							if(v.getNivel() < vars.get(j).getNivel()){
								if (vars.get(j).getDecimais() == null){
									soma += vars.get(j).getInteiro();
								}else{
									soma += vars.get(j).getInteiro() + vars.get(j).getDecimais();
								}
							}else{
								j=9998;
							}
						}if(v.getNivel() >= vars.get(j).getNivel()){
							j=9998;
						}
					}
					j++;
				}
				v.setInteiro(soma);
				soma=0;
			}
			if(v.getNivel() != 88){
				if(v.isAlfaNumerica()||v.isItenDeGrupo()){
					pic="X";
				}else{
					if(v.isNumerica()){
						pic="9";
					}
					if(v.isSinalizado()){
						pic="S"+pic;
					}
				}
				if(v.getInteiro().toString().length() == 1){
					v.setPic(pic+"(00"+v.getInteiro()+")");
				}else if(v.getInteiro().toString().length() == 2){
					v.setPic(pic+"(0"+v.getInteiro()+")");
				}else{
					v.setPic(pic+"("+v.getInteiro()+")");
				}
				if(v.haveDecimais()){
					if(v.getDecimais().toString().length() == 1){
						v.setPic(v.getPic()+"V9(00"+v.getDecimais()+")");
					}else if(v.getDecimais().toString().length() == 2){
						v.setPic(v.getPic()+"V9(0"+v.getDecimais()+")");
					}else{
						v.setPic(v.getPic()+"V9("+v.getDecimais()+")");
					}	
				}
			}
			i++;
		}

	}
}
