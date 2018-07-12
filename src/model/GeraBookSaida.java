package model;

import io.ArquivoInputCPY;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelos.ArquivoCblTO;
import analisadores.AnalisaBook;

public class GeraBookSaida {

	private String prefixo;
	private String aux1;
	private PrintStream gravador;
	private List<String> listaCampos = new ArrayList<String>();

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

			if (this.listaCampos.size() > 0) {
				this.gravador = new PrintStream("saida.txt");
				try {
					this.prefixo = JOptionPane.showInputDialog(null,
							"NOME DO BOOK:", "GERADOR BOOK SAIDA:", 3).replace(
							" ", "");
					this.montarSaida();
					this.gravador.close();
					JOptionPane.showMessageDialog(null, "Processamento Concluído.");
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

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "FALHA NO PROCESSAMENTO!");
		}
	}

	private void montarSaida() {
		for (String a : this.listaCampos) {
			this.aux1 = a.replace("10 ", "                   20  "
					+ this.prefixo.toUpperCase() + "-S-").replace("V USAGE COMP-3.", ".")
					.replace("PIC S9(", "PIC  9(").replace("PIC X", "PIC  X")
					.replace(" USAGE COMP-3.", ".")
					.replace(" USAGE COMP", ".");
			this.gravador.println(aux1);
		}
	}	
	
}