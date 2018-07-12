package model;

import io.ArquivoInputCPY;
import io.ArquivoOutput;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelos.ArquivoCblTO;
import util.ListasDeGravacao;
import util.UtilProjeto;
import analisadores.AnalisaBook;

public class ConsistenciaCoordenador {

	private PrintStream gravador;
	private List<String> listaCampos = new ArrayList<String>();
	private String[] variavel;
	private String aux1;
	private String aux2;
	private String spaces;
	private int ind;
	private int tamanhoAux1;
	private int tamanhoAux2;
	private boolean occurs = false;
	private boolean perform = false;

	public void processar() {
		try {

			JOptionPane.showMessageDialog(null,
					"Consistencia de Coordenador CICS, selecione o Book"
							.toUpperCase());

			ArquivoCblTO arquivo = new ArquivoInputCPY().arquivoUpado();

			if (arquivo == null || arquivo.getProcedimentos() == null
					|| arquivo.getProcedimentos().isEmpty()) {
				return;
			}

			try {
				this.listaCampos = new AnalisaBook()
						.carregarCoordenador(arquivo.getProcedimentos());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage()
						.toUpperCase());
				return;
			}

			if (this.listaCampos.size() > 0) {
				this.gravador = new PrintStream("saida.txt");
				this.montarCondicional();
				this.gravador.close();
				JOptionPane.showMessageDialog(null, "Processamento Concluído.");
			} else {
				JOptionPane.showMessageDialog(null, "Book vazio, repita o processamento!");
			}

			System.exit(0);			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "FALHA NO PROCESSAMENTO!");
		}
	}

	private void montarCondicional() {

		for (String a : this.listaCampos) {
			ind++;
			if (occurs == true) {
				if (this.perform == false) {
					this.perform = true;
					this.gravador
							.println("           PERFORM VARYING IND-CONSIST FROM 1 BY 1");
					this.gravador
							.println("                     UNTIL IND-CONSIST GREATER FEMPWXXX-E-QTD-XXXXXXXX");
					this.gravador.println("");
				}
				this.variavel = a.split(" ");
				this.gravador.println("               IF ("
						+ this.variavel[1].replace('_', '-') + "(IND-CONSIST)");
				if (a.contains("9(")) {
					this.gravador
							.println("                                       NOT NUMERIC)");
				} else {
					this.gravador
							.println("                                       EQUAL LOW-VALUES)");
				}
				this.gravador.println("               OR ("
						+ this.variavel[1].replace('_', '-') + "(IND-CONSIST)");
				if (a.contains("9(")) {
					this.gravador
							.println("                                       EQUAL ZEROS)");
				} else {
					this.gravador
							.println("                                       EQUAL SPACES)");
				}
				this.gravador
						.println("                   MOVE 08             TO FRWKGLAQ-COD-RETORNO");
				if (ind < 10) {
					this.gravador.println("                   MOVE '00" + ind
							+ "0'         TO FRWKGLAQ-COD-ERRO");
				} else {
					this.gravador.println("                   MOVE '0" + ind
							+ "0'         TO FRWKGLAQ-COD-ERRO");

				}
				this.gravador
						.println("                   MOVE 'FEMP1739'     TO FRWKGLAQ-COD-MENSAGEM");
				this.gravador
						.println("                   PERFORM 3000-FINALIZAR");
				this.gravador.println("               END-IF");
				this.gravador.println("");

			} else {
				this.variavel = a.split(" ");
				System.out.println(a);
				if (this.variavel[1].isEmpty()) {
					this.aux1 = ("           IF (" + this.variavel[2].replace(
							'_', '-'));
				} else {
					this.aux1 = ("           IF (" + this.variavel[1].replace(
							'_', '-'));
				}
				this.tamanhoAux1 = this.aux1.length();
				if (this.tamanhoAux1 >= 39) {
					this.gravador.println(this.aux1);
					if (a.contains("9(")) {
						this.gravador
								.println("                                       NOT NUMERIC)");
					} else {
						this.gravador
								.println("                                       EQUAL LOW-VALUES)");
					}
				} else {
					this.tamanhoAux1 = 39 - this.tamanhoAux1;
					this.spaces = UtilProjeto.getEspacos(this.tamanhoAux1);
					if (a.contains("9(")) {
						this.gravador.println(this.aux1 + this.spaces
								+ "NOT NUMERIC)");
					} else {
						this.gravador.println(this.aux1 + this.spaces
								+ "EQUAL LOW-VALUES)");
					}
				}
				if (this.variavel[1].isEmpty()) {
					this.aux2 = ("           OR (" + this.variavel[2].replace(
							'_', '-'));

				} else {
					this.aux2 = ("           OR (" + this.variavel[1].replace(
							'_', '-'));
				}
				this.tamanhoAux2 = this.aux2.length();
				if (this.tamanhoAux2 >= 39) {
					this.gravador.println(this.aux2);
					if (a.contains("9(")) {
						this.gravador
								.println("                                       EQUAL ZEROS)");
					} else {
						this.gravador
								.println("                                       EQUAL SPACES)");
					}
				} else {
					this.tamanhoAux2 = 39 - this.tamanhoAux2;
					this.spaces = UtilProjeto.getEspacos(this.tamanhoAux2);
					if (a.contains("9(")) {
						this.gravador.println(this.aux2 + this.spaces
								+ "EQUAL ZEROS)");
					} else {
						this.gravador.println(this.aux2 + this.spaces
								+ "EQUAL SPACES)");
					}
				}

				this.gravador
						.println("               MOVE 08                 TO FRWKGLAQ-COD-RETORNO");
				if (ind < 10) {
					this.gravador.println("               MOVE '00" + ind
							+ "0'             TO FRWKGLAQ-COD-ERRO");
				} else {
					this.gravador.println("               MOVE '0" + ind
							+ "0'             TO FRWKGLAQ-COD-ERRO");

				}
				this.gravador
						.println("               MOVE 'FEMP1739'         TO FRWKGLAQ-COD-MENSAGEM");
				this.gravador.println("               PERFORM 3000-FINALIZAR");
				this.gravador.println("           END-IF.");
				this.gravador.println("");
			}
		}
		if (this.perform == true) {
			this.gravador.println("           END-PERFORM.");
			this.gravador.println("");
		}

	}

}
