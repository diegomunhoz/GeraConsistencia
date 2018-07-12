package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ConsistenciaBasico {
	private Scanner leitorArquivo;
	private PrintStream gravador;
	private List<String> listaArquivos = new ArrayList<String>();
	private int ind;
	private String prefixo;
	private String campo;
	private String[] variavel;
	private boolean envio = false;

	public void processar() {
		try {
			this.leitorArquivo = new Scanner(new FileReader("entrada.txt"));

			while (this.leitorArquivo.hasNext()) {
				this.montarLista();
			}

			if (this.listaArquivos.size() > 0) {
				this.gravador = new PrintStream("saida.txt");
				this.envio = true;
				this.prefixo = JOptionPane.showInputDialog(null,
						"NOME DO BOOK:", "GERADOR DE CONSISTENCIA:", 3);
				this.montarCondicional();
			} else {
				this.envio = false;
				JOptionPane.showMessageDialog(null, "FALHA NO PROCESSAMENTO!");
			}

			if (this.envio == true) {
				this.gravador.close();
				JOptionPane.showMessageDialog(null, "Processamento Concluído.");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	private void montarCondicional() {
		for (String a : this.listaArquivos) {
			ind++;
			this.variavel = a.split(" ");
			this.gravador.println("           IF ("
					+ this.prefixo.toUpperCase() + "-E-"
					+ this.variavel[0].replace('_', '-'));
			if (a.contains("DECIMAL")) {
				this.gravador
						.println("                                       NOT NUMERIC)");
			} else {
				this.gravador
						.println("                                       EQUAL LOW-VALUES)");
			}
			if (a.contains("NOT NULL")) {
				this.gravador.println("           OR ("
						+ this.prefixo.toUpperCase() + "-E-"
						+ this.variavel[0].replace('_', '-'));
				if (a.contains("DECIMAL")) {
					this.gravador
							.println("                                       EQUAL ZEROS)");
				} else {
					this.gravador
							.println("                                       EQUAL SPACES)");
				}
			}
			this.gravador
					.println("               MOVE 08                 TO FEMPW000-R-COD-RETORNO");
			if (ind < 10) {
				this.gravador.println("               MOVE '00" + ind
						+ "0'             TO FEMPW000-R-COD-ERRO");
			} else {
				this.gravador.println("               MOVE '0" + ind
						+ "0'             TO FEMPW000-R-COD-ERRO");

			}
			this.gravador
					.println("               MOVE 'FEMP1739'         TO FEMPW000-R-COD-MENSAGEM");
			this.gravador.println("               PERFORM 3000-FINALIZAR");
			this.gravador.println("           END-IF.");
			this.gravador.println("");
		}
	}
	private void montarCondicionalOccurs() {
		for (String a : this.listaArquivos) {
			ind++;
			this.variavel = a.split(" ");
			this.gravador.println("               IF ("
					+ this.prefixo.toUpperCase() + "-E-"
					+ this.variavel[0].replace('_', '-') + "(IND-CONSIST)");
			if (a.contains("DECIMAL")) {
				this.gravador
						.println("                                       NOT NUMERIC)");
			} else {
				this.gravador
						.println("                                       EQUAL LOW-VALUES)");
			}
			if (a.contains("NOT NULL")) {
				this.gravador.println("               OR ("
						+ this.prefixo.toUpperCase() + "-E-"
						+ this.variavel[0].replace('_', '-') + "(IND-CONSIST)");
				if (a.contains("DECIMAL")) {
					this.gravador
							.println("                                       EQUAL ZEROS)");
				} else {
					this.gravador
							.println("                                       EQUAL SPACES)");
				}
			}
			this.gravador
					.println("                   MOVE 08             TO FEMPW000-R-COD-RETORNO");
			if (ind < 10) {
				this.gravador.println("                   MOVE '00" + ind
						+ "0'         TO FEMPW000-R-COD-ERRO");
			} else {
				this.gravador.println("                   MOVE '0" + ind
						+ "0'         TO FEMPW000-R-COD-ERRO");

			}
			this.gravador
					.println("                   MOVE 'FEMP1739'     TO FEMPW000-R-COD-MENSAGEM");
			this.gravador.println("                   PERFORM 3000-FINALIZAR");
			this.gravador.println("               END-IF");
			this.gravador.println("");
		}
	}

	private void montarLista() {
		try {
			this.campo = (String) this.leitorArquivo.nextLine().trim().replace("( ", "");
			if ((this.campo.contains("DECIMAL") || this.campo.contains("CHAR")
					|| this.campo.contains("TIMESTAMP")
					|| this.campo.contains("DATE")
					|| this.campo.contains("TIME")
					|| this.campo.contains("CLOB")
					|| this.campo.contains("INTEGER"))
					&& !this.campo.contains("*")
					&& !this.campo.isEmpty()){
				this.listaArquivos.add(this.campo);
				System.out.println(this.campo);
			}else{
				System.out.println(this.campo);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao montar lista.");
		}
	}

}
