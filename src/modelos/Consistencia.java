package modelos;

import java.util.ArrayList;

public class Consistencia {
	private StringBuffer s = new StringBuffer();
	private char[] bookPrefixo = new char[4];
	private Integer contador = new Integer(0);

	public Integer getContador() {
		return contador;
	}

	public String consistir(ArrayList<VariavelTO> variaveis, String prefixo) {
		prefixo.getChars(4, 8, bookPrefixo, 0);
		

		for (int i = 0; i < variaveis.size(); i++) {
			contador += 10;
			espacamento(10);
			s.append("IF  "
					+ String.valueOf(bookPrefixo).toUpperCase()
					+ "-"
					+ variaveis.get(i).getNome().toUpperCase()
							.replace("_", "-"));
			if (variaveis.get(i).getNome().length() + 19 >= 38) {
				s.append("\n");
				espacamento(38);

			} else {
				espacamento(40 - (variaveis.get(i).getNome().length() + 22));
			}
				if (variaveis.get(i).isNumerica()) {
					s.append("NOT NUMERIC OR\n");

					espacamento(14);
					s.append(String.valueOf(bookPrefixo).toUpperCase()
							+ "-"
							+ variaveis.get(i).getNome().toUpperCase().replace(
									"_", "-"));
					if (variaveis.get(i).getNome().length() + 19 >= 38) {
						s.append("\n");
						espacamento(38);

					} else {
						espacamento(40 - (variaveis.get(i).getNome().length() + 22));
					}
					s.append("EQUAL ZEROS");

					if (variaveis.get(i).getNome().toUpperCase().replace("_",
							"-").equals("MAX-OCORR")) {
						s.append(" OR\n");
						espacamento(14);
						s.append(String.valueOf(bookPrefixo).toUpperCase()
								+ "-"
								+ variaveis.get(i).getNome().toUpperCase()
										.replace("_", "-"));
						if (variaveis.get(i).getNome().length() + 19 >= 38) {
							s.append("\n");
							espacamento(38);

						} else {
							espacamento(40 - (variaveis.get(i).getNome()
									.length() + 22));
						}
						s.append("GREATER 30\n");
					} else {
						s.append("\n");
					}
				} else {

					s.append("EQUAL SPACES OR\n");

					espacamento(14);
					s.append(String.valueOf(bookPrefixo).toUpperCase()
							+ "-"
							+ variaveis.get(i).getNome().toUpperCase().replace(
									"_", "-"));
					if (variaveis.get(i).getNome().length() + 19 >= 38) {
						s.append("\n");
						espacamento(38);

					} else {
						espacamento(40 - (variaveis.get(i).getNome().length() + 22));
					}
					s.append("EQUAL LOW-VALUES\n");

				}
				espacamento(14);

				s.append("MOVE 08                 TO FRWKGLAQ-COD-RETORNO\n");
				espacamento(14);
				s.append("MOVE '" + String.format("%04d", contador)
						+ "'             TO FRWKGLAQ-COD-ERRO\n");
				espacamento(14);
				s.append("MOVE 'UORG0000'         TO FRWKGLAQ-COD-MENSAGEM\n");
				espacamento(14);
				s.append("PERFORM 5000-FINALIZAR\n");
				espacamento(10);
				s.append("END-IF.\n\n");

			}
		

		return s.toString();
	}

	private void espacamento(int i) {
		for (int j = 0; j <= i; j++) {
			s.append(" ");
		}
	}

}
