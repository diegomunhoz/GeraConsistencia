package io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import modelos.ArquivoCblTO;

public class ArquivoInputCPY extends JDialog{
	ArquivoCblTO arquivoCBL = new ArquivoCblTO();

	@SuppressWarnings("deprecation")
	public ArquivoCblTO arquivoUpado() {
		this.setIconImage(new ImageIcon(this.getClass().getResource(
		"/icones/logo.png")).getImage());
		try {
			
//			JFileChooser fileChooser = new JFileChooser();
//			fileChooser
//					.setFileFilter(new FileNameExtensionFilter("CPY", "cpy"));
//			fileChooser.setAcceptAllFileFilterUsed(false);

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(true);
			
			fileChooser.setDialogTitle("SELECIONE O BOOK PARA ANALISE");

			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				URL mediaURL = null;
				try {
					mediaURL = fileChooser.getSelectedFile().toURL();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

				if (mediaURL != null) {
					this.arquivoCBL.setNomeArquivo(fileChooser
							.getSelectedFile().getName());
					String path = String.valueOf(mediaURL);
					path = path.substring(6, path.length()).replaceAll("/",
							"//");
					String ar[] = path.split("//");
					StringBuffer b = new StringBuffer();
					for (int i = 0; i < ar.length; i++) {
						if (i != ar.length - 1) {
							b.append(ar[i]);
							if (i != ar.length - 2) {
								b.append("//");
							}
						}
					}
					ArquivoCache.pathSaida = String.valueOf(b);
					this.arquivoCBL.setProcedimentos(this.leitura(path));
				}
			}
			return arquivoCBL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private List<String> leitura(String pathArq) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		try {
			if (pathArq != null) {
				Scanner leitor = new Scanner(new FileReader(pathArq));
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
