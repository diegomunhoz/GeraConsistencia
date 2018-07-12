package model;

import javax.swing.JOptionPane;

public class Roteador {

	private int opcao;

	public Roteador() {
		try {
			this.opcao = Integer.parseInt(JOptionPane.showInputDialog(null,
					            "1 - CONSISTENCIA COORDENADOR"
							+ "\n2 - CONSISTENCIA FUNCIONAL"
							+ "\n3 - CONSISTENCIA BASICO"
							+ "\n4 - MOVE COMMAREA"
							+ "\n5 - MOVE SAIDA "
							+ "\n6 - CONSISTENCIA BATCH"
							+ "\n7 - BOOK SAIDA FUNCIONAL"
							+ "\n8 - BOOK BATCH SYSIN"
							,"AUXILIAR JAVA", 3));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "\nProcessamento Finalizado.");
			System.exit(0);
		}
		if (this.opcao == 1) {
			new ConsistenciaCoordenador().processar();
		} else if (this.opcao == 2) {
			new ConsistenciaFuncional().processar();
		} else if (this.opcao == 3) {
			new ConsistenciaBasico().processar();
		} else if (this.opcao == 4) {
			new GerarMoveEntrada().processar();
		} else if (this.opcao == 5) {
			new GerarMoveSaida().processar();
		} else if (this.opcao == 6) {
			new ConsistenciaBatch().processar();
		} else if (this.opcao == 7) {
			new GeraBookSaida().processar();
		} else if (this.opcao == 8) {
			new GeraBookUnload().processar();			
		} else {
			JOptionPane.showMessageDialog(null, "\nProcessamento Finalizado.");
		}
		System.exit(0);
	}
}
