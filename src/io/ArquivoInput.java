package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelos.ArquivoCblTO;

public class ArquivoInput {
	ArquivoCblTO arquivoCBL = new ArquivoCblTO();

	public ArquivoCblTO arquivoUpado() {
		try {
			Upload upload = new Upload();
			arquivoCBL.setProcedimentos(leitura(upload.uploadArquivo()));
			return arquivoCBL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private List<String> leitura(File file) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		try {
			if (file != null) {
				Scanner leitor = new Scanner(new FileReader(String.valueOf(
						file.getAbsolutePath()).replaceAll("\\\\", "/")));
				arquivoCBL.setNomeArquivo(String.valueOf(file.getName()));
				while (leitor.hasNext()) {
					list.add(String.valueOf(leitor.nextLine()));
				}
				leitor.close();
			}
		} catch (FileNotFoundException f) {
			f.printStackTrace();
			throw new Exception("ARQUIVO DE ENTRADA NAO EXISTE");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ERRO NO ARQUIVO DE ENTRADA.");
		}
		return list;
	}
}
