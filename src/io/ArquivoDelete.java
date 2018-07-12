package io;

import java.io.File;
public class ArquivoDelete {

	 public void deletar(){
		 if(!ArquivosCache.listEntrada.isEmpty()){
			 for (int i =0; i <ArquivosCache.listEntrada.size();i++){
				 this.remover(ArquivosCache.listEntrada.get(i));
			 }
		 }
		 if(!ArquivosCache.listSaida.isEmpty()){
			 for (int i =0; i<ArquivosCache.listSaida.size();i++){
				 this.remover(ArquivosCache.listSaida.get(i));
			 }
		 }
	 }
	 
	 private void remover(File f){
		 if (f.isDirectory()) {  
	            File[] files = f.listFiles();  
	            for (int i = 0; i < files.length; ++i) {  
	                remover (files[i]);  
	            }  
	        }  
	        f.delete();   
	 }
	 
	 
	 
}
