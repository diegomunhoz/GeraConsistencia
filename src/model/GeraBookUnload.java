package model;

import io.ArquivoInputCPY;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelos.ArquivoCblTO;
import util.Calendario;
import analisadores.AnalisaBook;

public class GeraBookUnload {

	private Calendario objCalendario = new Calendario();
	private String[] variavelCampos;
	private String[] variavelNulos;
	private String prefixo;
	private String aux1;
	private String dataAtual;
	private PrintStream gravador;
	private List<String> listaCampos = new ArrayList<String>();
	private List<String> listaNulos = new ArrayList<String>();

	public void processar() {
		try {

			ArquivoCblTO arquivo = new ArquivoInputCPY().arquivoUpado();

			if (arquivo == null || arquivo.getProcedimentos() == null
					|| arquivo.getProcedimentos().isEmpty()) {
				return;
			}

			try {
				this.listaCampos = new AnalisaBook().carregaDclgen(arquivo
						.getProcedimentos());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage()
						.toUpperCase());
				return;
			}

			try {
				this.listaNulos = new AnalisaBook().carregaDclgenNulos(arquivo
						.getProcedimentos());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage()
						.toUpperCase());
				return;
			}

			if (this.listaCampos.size() > 0) {
				try {
					this.prefixo = JOptionPane.showInputDialog(null,
							"NOME DO BOOK:", "GERADOR BOOK SAIDA:", 3).replace(
							" ", "");
					if (this.prefixo.isEmpty()) {
						this.gravador.close();
						JOptionPane.showMessageDialog(null,
								"Processamento Finalizado.");
						System.exit(0);
					}
					
					this.gravador = new PrintStream(this.prefixo + ".txt");
					this.montarSaida();
					this.gravador.close();
					JOptionPane.showMessageDialog(null,
							"Processamento Concluído.");
				} catch (Exception e) {
					this.gravador.close();
					JOptionPane.showMessageDialog(null,
							"Processamento Finalizado.");
					System.exit(0);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Book vazio, repita o processamento!");
			}

			System.exit(0);

		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "FALHA NO PROCESSAMENTO!");
		}
	}

	private void montarSaida() {

		this.dataAtual = this.objCalendario.getMesAtual() + "/"
				+ this.objCalendario.getAnoAtual();

		this.gravador
				.println("      ******************************************************************");
		this.gravador.println("      *    NOME DA INC = "
				+ this.prefixo.toUpperCase()
				+ " - SYSIN DA TABELA FEMPBXXX           *");
		this.gravador
				.println("      *    RESPONSAVEL = BSI TECNOLOGIA                                *");
		this.gravador.println("      *    DATA        = " + this.dataAtual
				+ "                                       *");
		this.gravador
				.println("      ******************************************************************");
		this.gravador.println("");
		this.gravador.println("       01  REG-" + this.prefixo.toUpperCase()
				+ ".");

		for (String a : this.listaCampos) {

			this.aux1 = a
					.replace(
							"10 ",
							"           05  " + this.prefixo.toUpperCase()
									+ "-")
					.replace("V USAGE COMP-3.", " COMP-3.")
					.replace("PIC S9(", "     PIC S9(")
					.replace("PIC X", "     PIC  X")
					.replace(" USAGE COMP-3.", " COMP-3.");
			this.gravador.println(aux1);

			this.variavelCampos = a.split(" ");

			if (this.listaNulos.size() > 0) {
				for (String b : this.listaNulos) {
					this.variavelNulos = b.split(" ");
					if (this.variavelNulos[0].replace("_", "-").equals(
							this.variavelCampos[1])) {
						this.gravador.println("           05  "
								+ this.prefixo.toUpperCase() + "-"
								+ this.variavelCampos[1] + "-NULL      PIC  X(1).");
					}
				}				
			}

		}
	}

}