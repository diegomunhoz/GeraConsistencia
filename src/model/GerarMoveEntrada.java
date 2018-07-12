package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class GerarMoveEntrada {
	private Scanner leitorArquivo;
	private PrintStream gravador;
	private List<String> listaCampos = new ArrayList<String>();
	private String campo;
	private String bookEntrada;
	private String bookInterface;
	private String[] variavel;
	private boolean envio = false;
	private boolean occurs = false;
	private boolean perform = false;

	public void processar() {
		try {
			this.leitorArquivo = new Scanner(new FileReader("entrada.txt"));

			while (this.leitorArquivo.hasNext()) {
				this.montarLista();
			}

			if (this.listaCampos.size() > 0) {
				this.gravador = new PrintStream("saida.txt");
				this.envio = true;
				this.bookEntrada = JOptionPane.showInputDialog(null,
						"NOME DO BOOK DE ENTRADA: ", 
						"MOVE COMMAREA", 3);
				this.bookInterface = JOptionPane.showInputDialog(null,
						"NOME DO BOOK DE INTERFACE: ",
						"MOVE COMMAREA", 3);
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
		this.perform = false;
		for (String a : this.listaCampos) {
			if (a.contains("OCCURS")) {
				if (this.perform == false) {
					this.perform = true;
					this.gravador.println("");
					this.gravador
							.println("           PERFORM VARYING IND-ENVIA  FROM 1 BY 1");
					this.gravador
							.println("                     UNTIL IND-ENVIA  GREATER FEMPWXXX-E-QTD-XXXXXXXX");
					this.gravador.println("");
				}
				this.variavel = a.split(" ");
				if (this.variavel[1].isEmpty()) {
					this.gravador.println("               MOVE "
							+ this.variavel[2].replace('_', '-') + "(WRK-IND)");
					this.gravador
							.println("                                       TO "
									+ this.variavel[2].replace(this.bookEntrada,
											this.bookInterface));
				}else {
					this.gravador.println("               MOVE "
							+ this.variavel[1].replace('_', '-') + "(WRK-IND)");
					this.gravador
							.println("                                       TO "
									+ this.variavel[1].replace(this.bookEntrada,
											this.bookInterface));					
				}
				this.gravador
						.println("                                                             (IND-ENVIA)");

			} else {
				this.variavel = a.split(" ");
				if (this.variavel[1].isEmpty()) {
					this.gravador.println("           MOVE "
							+ this.variavel[2].replace('_', '-'));
					this.gravador
							.println("                                       TO "
									+ this.variavel[2].replace(this.bookEntrada,
											this.bookInterface) + ".");				
				}else {
					this.gravador.println("           MOVE "
							+ this.variavel[1].replace('_', '-'));
					this.gravador
							.println("                                       TO "
									+ this.variavel[1].replace(this.bookEntrada,
											this.bookInterface) + ".");					
				}
			}
		}
		if (this.perform == true) {
			this.gravador.println("           END-PERFORM.");
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
				if (this.campo.contains("OCCURS") && !this.campo.contains("MAX-OCCURS")) {
					occurs = true;
				}
				if (this.campo.contains("9(") || this.campo.contains("X(") || !this.campo.contains("-S-")) {
					if (occurs == true) {
						this.listaCampos.add(this.campo + " OCCURS");
					} else {
						this.listaCampos.add(this.campo);
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao montar lista.");
		}
	}

}
