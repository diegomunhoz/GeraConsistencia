package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFileChooser;

public class Upload {

	ArquivoInput arquivo = new ArquivoInput();

	private static byte[] recuperaFileEmByte(File inFile) {
		InputStream is = null;
		byte[] buffer = null;
		try {
			is = new FileInputStream(inFile);
			buffer = new byte[is.available()];
			is.read(buffer);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public File uploadArquivo()  {
		File arquivoCriado = null;
		try {
			JFileChooser fileChooser = new JFileChooser();

			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				URL mediaURL = null;
				try {
					mediaURL = fileChooser.getSelectedFile().toURL();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

				if (mediaURL != null) {

					File arquivoSelecionado = fileChooser.getSelectedFile();
					String nomeArquivo = fileChooser.getSelectedFile()
							.getName();
					InputStream caminhoDestinoArquivo = Upload.class
							.getResourceAsStream("/util/ArquivosEntrada");
					File pastaCoipia = new File(String
							.valueOf(caminhoDestinoArquivo));
					ArquivosCache.listEntrada.add(pastaCoipia);
		//			System.out.println(String.valueOf(caminhoDestinoArquivo));
					pastaCoipia.mkdir();
					arquivoCriado = new File(String
							.valueOf(caminhoDestinoArquivo), nomeArquivo);
					// System.out.println(String.valueOf(arquivoCriado.getAbsolutePath()).replaceAll("\\\\",
					// "/"));
					try {
						// byte[] imagem = new
						// byte[(int)arquivoSelecionado.length()];
						// System.out.println("Lendo: " +
						// arquivoSelecionado.length());

						FileOutputStream out = new FileOutputStream(
								arquivoCriado);
						out.write(recuperaFileEmByte(arquivoSelecionado));
						out.flush();
						out.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return arquivoCriado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
