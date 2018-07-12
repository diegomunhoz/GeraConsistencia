package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.swing.JFileChooser;

import modelos.ArquivoCblTO;

public class ArquivoOutput {
	private String nomeArquivo;
	private String[] arrayDados;
	private String pathSalvar = null;

	private void download() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("SELECIONE O DESTINO DE GRAVAÇÃO");

		int ok = chooser.showSaveDialog(null);
		if (ok == JFileChooser.APPROVE_OPTION) {
			this.pathSalvar = (String.valueOf(chooser.getSelectedFile())
					.replaceAll("\\\\", "\\\\\\\\"));
		} else if (ok == JFileChooser.CANCEL_OPTION) {
			this.pathSalvar = null;
		}
	}

	private void rotinaGravacao() throws Exception {
		try {
			if (this.pathSalvar != null) {
				File arquivoSaida = new File(this.pathSalvar, this.nomeArquivo);
				OutputStream out = new FileOutputStream(arquivoSaida);
				OutputStreamWriter outWrite = new OutputStreamWriter(out);
				BufferedWriter w = new BufferedWriter(outWrite);
				for (int i = 0; i < this.arrayDados.length; i++) {
					w.write(String.valueOf(this.arrayDados[i]));
					w.newLine();
				}
				w.close();
			}
		} catch (FileNotFoundException f) {

			if (f.getMessage().contains(
					"(O sistema não pode encontrar o caminho especificado)")) {
				String path = this.pathSalvar;
				String nome = this.pathSalvar;
				try {
					path = path.substring(0, path.lastIndexOf("\\") - 1);
					nome = nome.substring(nome.lastIndexOf("\\") + 1);
					File arquivoSaida = new File(path, nome + ".txt");
					OutputStream out = new FileOutputStream(arquivoSaida);
					OutputStreamWriter outWrite = new OutputStreamWriter(out);
					BufferedWriter w = new BufferedWriter(outWrite);
					for (int i = 0; i < this.arrayDados.length; i++) {
						w.write(String.valueOf(this.arrayDados[i]));
						w.newLine();
					}
					w.close();

				} catch (Exception x) {
					throw new Exception(
							"O sistema não pode encontrar o caminho especificado");

				}
			} else {
				throw new FileNotFoundException(
						"ACESSO NEGADO PARA GRAVAR ARQUIVO DE SAIDA");

			}
		} catch (Exception e) {

			throw new Exception("ERRO AO GRAVR ARQUIVO");
		}
	}

	public void gravarArquivo(StringBuffer conteudo, String nome)
			throws Exception {
		String c = conteudo + "";
		this.arrayDados = c.split("\n");
		this.nomeArquivo = nome;
		this.rotinaGravacao();
	}

	public void salvarArquivo(List<ArquivoCblTO> list) throws Exception {
		if (list != null && !list.isEmpty()) {
			this.download();
			for (int i = 0; i < list.size(); i++) {
				this.gravarArquivo(list.get(i).getArquivoEmString(), list
						.get(i).getNomeArquivo());
			}
		} else {
			throw new Exception("NÃO EXITE ARQUIVO PARA SER GRAVADO");
		}
	}

}
