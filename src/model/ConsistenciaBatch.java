package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ConsistenciaBatch {
	private Scanner leitorArquivo;
	private PrintStream gravador;
	private List<String> listaCampos = new ArrayList<String>();
	private int ind;
	private String campoAuxiliar;
	private String campo;
	private String[] variavel;
	private boolean envio = false;

	public void processar() {
		try {
			this.leitorArquivo = new Scanner(new FileReader("entrada.txt"));

			while (this.leitorArquivo.hasNext()) {
				this.montarLista();
			}

			if (this.listaCampos.size() > 0) {
				this.gravador = new PrintStream("saida.txt");
				this.envio = true;
				this.montarCondicionalBatch();
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

	private void montarCondicionalBatch() {
		for (String a : this.listaCampos) {
			this.variavel = a.split(" ");
			if (a.contains("9(")) {
				if (this.variavel[1].isEmpty()) {
					this.gravador.println("           IF ("
							+ this.variavel[2].replace('_', '-')
							+ "    NOT NUMERIC)");
					ind++;
					if (ind < 10) {
						this.gravador.println("               MOVE '0" + ind
								+ "'               TO WR07-COD-ERRO");
					} else {
						this.gravador.println("               MOVE '" + ind
								+ "'               TO WR07-COD-ERRO");

					}
					this.campoAuxiliar = this.variavel[2].replace("WM07-", "");
					System.out.println(this.campoAuxiliar);
					this.gravador.println("               MOVE '"
							+ this.campoAuxiliar + " NOT NUMERIC'");
					this.gravador
							.println("                                       TO WR07-DESCRICAO");

				} else {
					this.gravador.println("           IF ("
							+ this.variavel[1].replace('_', '-')
							+ "    NOT NUMERIC)");
					ind++;
					if (ind < 10) {
						this.gravador.println("               MOVE '0" + ind
								+ "'               TO WR07-COD-ERRO");
					} else {
						this.gravador.println("               MOVE '" + ind
								+ "'               TO WR07-COD-ERRO");

					}
					this.campoAuxiliar = this.variavel[1].replace("WM07-", "");
					System.out.println(this.campoAuxiliar);
					this.gravador.println("               MOVE '"
							+ this.campoAuxiliar + " NOT NUMERIC'");
					this.gravador
							.println("                                       TO WR07-DESCRICAO");
				}
			} else {
				if (this.variavel[1].isEmpty()) {
					this.gravador.println("           IF ("
							+ this.variavel[2].replace('_', '-')
							+ "    EQUAL LOW-VALUES)");
					ind++;
					if (ind < 10) {
						this.gravador.println("               MOVE '0" + ind
								+ "'               TO WR07-COD-ERRO");
					} else {
						this.gravador.println("               MOVE '" + ind
								+ "'               TO WR07-COD-ERRO");

					}
					this.campoAuxiliar = this.variavel[2].replace("WM07-", "");
					System.out.println(this.campoAuxiliar);
					this.gravador.println("                   MOVE '"
							+ this.campoAuxiliar + " EQUAL LOW-VALUES'");
					this.gravador
							.println("                                       TO WR07-DESCRICAO");
				} else {
					this.gravador.println("           IF ("
							+ this.variavel[1].replace('_', '-')
							+ "    EQUAL LOW-VALUES)");
					ind++;
					if (ind < 10) {
						this.gravador.println("               MOVE '0" + ind
								+ "'               TO WR07-COD-ERRO");
					} else {
						this.gravador.println("               MOVE '" + ind
								+ "'               TO WR07-COD-ERRO");

					}
					this.campoAuxiliar = this.variavel[1].replace("WM07-", "");
					System.out.println(this.campoAuxiliar);
					this.gravador.println("                   MOVE '"
							+ this.campoAuxiliar + " EQUAL LOW-VALUES'");
					this.gravador
							.println("                                       TO WR07-DESCRICAO");
				}

			}

			if (a.contains("9(")) {
				if (this.variavel[1].isEmpty()) {
					this.gravador.println("           OR ("
							+ this.variavel[2].replace('_', '-')
							+ "      EQUAL ZEROS)");

					ind++;
					if (ind < 10) {
						this.gravador.println("               MOVE '0" + ind
								+ "'                TO WR07-COD-ERRO");
					} else {
						this.gravador.println("               MOVE '" + ind
								+ "'                TO WR07-COD-ERRO");
					}

					this.campoAuxiliar = this.variavel[2].replace("WM07-", "");
					System.out.println(this.campoAuxiliar);
					this.gravador.println("                   MOVE '"
							+ this.campoAuxiliar + "     EQUAL ZEROS'");
					this.gravador
							.println("                                       TO WR07-DESCRICAO");

				} else {
					this.gravador.println("           OR ("
							+ this.variavel[1].replace('_', '-')
							+ "    EQUAL ZEROS)");

					ind++;
					if (ind < 10) {
						this.gravador.println("               MOVE '0" + ind
								+ "'               TO WR07-COD-ERRO");
					} else {
						this.gravador.println("               MOVE '" + ind
								+ "'               TO WR07-COD-ERRO");
					}

					this.campoAuxiliar = this.variavel[1].replace("WM07-", "");

					System.out.println(this.campoAuxiliar);
					this.gravador.println("               MOVE '"
							+ this.campoAuxiliar + " EQUAL ZEROS'");
					this.gravador
							.println("                                       TO WR07-DESCRICAO");

				}
			} else {
				if (this.variavel[1].isEmpty()) {
					this.gravador.println("           OR ("
							+ this.variavel[2].replace('_', '-')
							+ "      EQUAL SPACES");

					ind++;
					if (ind < 10) {
						this.gravador.println("               MOVE '0" + ind
								+ "'               TO WR07-COD-ERRO");
					} else {
						this.gravador.println("               MOVE '" + ind
								+ "'               TO WR07-COD-ERRO");
					}

					this.campoAuxiliar = this.variavel[2].replace("WM07-", "");
					System.out.println(this.campoAuxiliar);
					this.gravador.println("                   MOVE '"
							+ this.campoAuxiliar + "     EQUAL SPACES'");
					this.gravador
							.println("                                       TO WR07-DESCRICAO");

				} else {
					this.gravador.println("           OR ("
							+ this.variavel[1].replace('_', '-')
							+ "    EQUAL SPACES");

					ind++;
					if (ind < 10) {
						this.gravador.println("               MOVE '0" + ind
								+ "'               TO WR07-COD-ERRO");
					} else {
						this.gravador.println("               MOVE '" + ind
								+ "'               TO WR07-COD-ERRO");
					}

					this.campoAuxiliar = this.variavel[1].replace("WM07-", "");

					System.out.println(this.campoAuxiliar);
					this.gravador.println("               MOVE '"
							+ this.campoAuxiliar + " EQUAL SPACES'");
					this.gravador
							.println("                                       TO WR07-DESCRICAO");
				}
			}
			this.gravador.println("           END-IF.");
			this.gravador.println("");
		}
	}

	private void montarLista() {
		try {
			this.campo = (String) this.leitorArquivo.nextLine().trim();
			if (!this.campo.contains("*") && !this.campo.contains("COD-LAYOUT")
					&& !this.campo.isEmpty()
					&& !this.campo.contains("TAM-LAYOUT")
					&& !this.campo.contains("E-REGISTRO")
					&& !this.campo.contains("E-HEADER")
					&& !this.campo.contains("E-BLOCO-ENTRADA")) {
				if (this.campo.contains("9(") || this.campo.contains("X(")) {
					this.listaCampos.add(this.campo);
					System.out.println(this.campo);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao montar lista.");
		}
	}
}