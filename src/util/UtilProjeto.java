package util;

import java.util.ArrayList;
import java.util.List;

import perfis.PerfilBradesco;

public class UtilProjeto {

	/**
	 * metodo para trocar um char por outro dentro de uma lista
	 * 
	 * @param lista
	 * @param oldChar
	 * @param newChar
	 * @return
	 */

	public void mudarAparencia() {
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> replaceList(List<String> lista, String oldChar,
			String newChar) {
		List<String> listaString = new ArrayList<String>();
		for (String s : lista) {
			listaString.add(s.toString().replace(oldChar, newChar));
		}
		return listaString;
	}

	public static String geraLinhaCondicional(Integer numeroDeEspacamentos,
			String primeiraSintaxe, String primeiraVariavel,
			String segundaSintaxe) {
		return getCondicionalSimples(numeroDeEspacamentos, primeiraSintaxe,
				primeiraVariavel, segundaSintaxe);
	}

	/**
	 * metodo que gera uma condicional simples no padrao
	 * 
	 * @param numeroDeEspacamentos
	 * @param primeiraSintaxe
	 * @param primeiraVariavel
	 * @param segundaSintaxe
	 * @return
	 */
	private static String getCondicionalSimples(Integer numeroDeEspacamentos,
			String primeiraSintaxe, String primeiraVariavel,
			String segundaSintaxe) {

		StringBuffer linhaCondicional = new StringBuffer();

		linhaCondicional.append(getEspacos(numeroDeEspacamentos));
		linhaCondicional.append(primeiraSintaxe + "  ");
		linhaCondicional.append(primeiraVariavel);

		if (linhaCondicional.length() < (PerfilBradesco.colunaPadrao - PerfilBradesco.taxaDePerdaDoCarro)) {
			linhaCondicional
					.append(getEspacos(PerfilBradesco.colunaPadrao
							- (linhaCondicional.length() + PerfilBradesco.taxaDePerdaDoCarro)));
			linhaCondicional.append(segundaSintaxe);
		} else {
			linhaCondicional.append("\n");
			linhaCondicional
					.append(getEspacos((PerfilBradesco.colunaPadrao - PerfilBradesco.taxaDePerdaDoCarro)));
			linhaCondicional.append(segundaSintaxe);
		}

		return linhaCondicional.toString();
	}

	public static String geraMovimentacoes(Integer numeroDeEspacamentos,
			String primeiraSintaxe, String primeiraVariavel,
			String segundaSintaxe, String segundaVariavel,
			String terceiraSintaxe, String terceiraVariavel) {

		return getMovimentacoes(numeroDeEspacamentos, primeiraSintaxe,
				primeiraVariavel, segundaSintaxe, segundaVariavel,
				terceiraSintaxe, terceiraVariavel);
	}

	/**
	 * metodo que gera movimentação simples
	 * 
	 * @param numeroDeEspacamentos
	 * @param primeiraSintaxe
	 * @param primeiraVariavel
	 * @param segundaSintaxe
	 * @param segundaVariavel
	 * @param terceiraSintaxe
	 * @param terceiraVariavel
	 * @return
	 */
	private static String getMovimentacoes(Integer numeroDeEspacamentos,
			String primeiraSintaxe, String primeiraVariavel,
			String segundaSintaxe, String segundaVariavel,
			String terceiraSintaxe, String terceiraVariavel) {
		Boolean quebraLinha40 = false;
		List<String> linhaDaMovimentacao = new ArrayList<String>();
		StringBuffer aux = new StringBuffer();

		aux.append((getEspacos(numeroDeEspacamentos)) + primeiraSintaxe + " "
				+ primeiraVariavel);

		if (aux.length() < (PerfilBradesco.colunaPadrao - PerfilBradesco.taxaDePerdaDoCarro)) {
			aux.append(getEspacos(PerfilBradesco.colunaPadrao
					- (aux.length() + PerfilBradesco.taxaDePerdaDoCarro)));
			aux.append(segundaSintaxe + " ");
			aux.append(segundaVariavel);
			linhaDaMovimentacao.add(0, aux.toString());
		} else {
			linhaDaMovimentacao.add(0, aux.toString());
			aux = new StringBuffer();
			quebraLinha40 = true;
			aux.append("\n");

			aux.append(getEspacos(PerfilBradesco.colunaPadrao
					- PerfilBradesco.taxaDePerdaDoCarro));
			aux.append(segundaSintaxe + " ");
			aux.append(segundaVariavel);
			linhaDaMovimentacao.add(1, aux.toString());
		}

		if (terceiraSintaxe != null && !terceiraSintaxe.isEmpty()) {

			if (quebraLinha40) {
				if (linhaDaMovimentacao.get(1).length() < (PerfilBradesco.colunaSecundaria - PerfilBradesco.taxaDePerdaDoCarro)) {

					aux = new StringBuffer(linhaDaMovimentacao.get(1));
					Integer n = (PerfilBradesco.colunaSecundaria - (linhaDaMovimentacao
							.get(1).length()));
					aux
							.append(getEspacos(n));

					aux.append(terceiraSintaxe + " ");
					aux.append(terceiraVariavel);

					linhaDaMovimentacao.set(1, aux.toString());

				} else {

					aux = new StringBuffer();
					aux.append("\n");
					aux.append(getEspacos(PerfilBradesco.colunaPadrao
							- PerfilBradesco.taxaDePerdaDoCarro));

					aux
							.append(getEspacos((PerfilBradesco.colunaSecundaria - (linhaDaMovimentacao
									.get(1).length() + PerfilBradesco.taxaDePerdaDoCarro)))
									+ terceiraSintaxe + " ");
					aux.append(terceiraVariavel);

					linhaDaMovimentacao.add(2, aux.toString());

				}
			} else {
				if (linhaDaMovimentacao.get(0).length() < (PerfilBradesco.colunaSecundaria - PerfilBradesco.taxaDePerdaDoCarro)) {

					aux = new StringBuffer(linhaDaMovimentacao.get(0));
					aux
							.append(getEspacos((PerfilBradesco.colunaSecundaria - (linhaDaMovimentacao
									.get(0).length() + PerfilBradesco.taxaDePerdaDoCarro)))
									+ terceiraSintaxe + " ");
					aux.append(terceiraVariavel);

					linhaDaMovimentacao.set(0, aux.toString());

				} else {

					aux = new StringBuffer();
					aux.append("\n");
					aux.append(getEspacos(PerfilBradesco.colunaPadrao
							- PerfilBradesco.taxaDePerdaDoCarro));

					aux
							.append(getEspacos((PerfilBradesco.colunaSecundaria - (linhaDaMovimentacao
									.get(0).length() + PerfilBradesco.taxaDePerdaDoCarro)))
									+ terceiraSintaxe + " ");
					aux.append(terceiraVariavel);

					linhaDaMovimentacao.add(1, aux.toString());

				}
			}
		}

		StringBuffer retorno = new StringBuffer();
		for (String saida : linhaDaMovimentacao) {
			retorno.append(saida);
		}
		return retorno.toString();
	}

	/*
	 * metodo que retorna espaçamentos
	 */
	public static String getEspacos(Integer numeroDeEspacos) {

		StringBuffer acuEspacos = new StringBuffer();

		for (Integer ind = 0; ind < numeroDeEspacos; ind++) {
			acuEspacos.append(" ");
		}
		return String.valueOf(acuEspacos);
	}

	public static String geraVariaveisDeNulidade(
			List<String> variaveisNaoNotNull, String prefixoNulidade,
			String sufixoNulidade) {
		return getVariaveisDeNulidade(variaveisNaoNotNull, prefixoNulidade,
				sufixoNulidade);
	}

	/**
	 * gera variaveis de nulidade
	 * 
	 * @param variaveisNaoNotNull
	 * @param prefixoNulidade
	 * @param sufixoNulidade
	 * @return
	 */
	private static String getVariaveisDeNulidade(
			List<String> variaveisNaoNotNull, String prefixoNulidade,
			String sufixoNulidade) {

		StringBuffer variaveisDeNulidade = new StringBuffer();

		StringBuffer linhaDaVariavel;

		variaveisDeNulidade.append(getEspacos(PerfilBradesco.coluna8)
				+ "01  WRK-NULIDADE.");
		for (String variavel : variaveisNaoNotNull) {

			linhaDaVariavel = new StringBuffer();
			linhaDaVariavel.append("\n");

			linhaDaVariavel
					.append(getEspacos((PerfilBradesco.coluna8 + PerfilBradesco.espacamentoDeTabulacao)));
			linhaDaVariavel.append("05  ");
			linhaDaVariavel.append(prefixoNulidade);
			linhaDaVariavel.append(variavel.trim().replace("_", "-"));
			linhaDaVariavel.append(sufixoNulidade);
			if (linhaDaVariavel.toString().length() < PerfilBradesco.colunaPadrao) {
				linhaDaVariavel.append(getEspacos(PerfilBradesco.colunaPadrao
						- linhaDaVariavel.toString().length()));
			} else {
				linhaDaVariavel.append("\n");
				linhaDaVariavel.append(getEspacos(PerfilBradesco.colunaPadrao
						- PerfilBradesco.taxaDePerdaDoCarro));
			}
			linhaDaVariavel.append("PIC S9(004) COMP    VALUE ZEROS.");

			variaveisDeNulidade
					.append(linhaDaVariavel.toString().toUpperCase());
		}
		return variaveisDeNulidade.toString().toUpperCase();
	}

}
