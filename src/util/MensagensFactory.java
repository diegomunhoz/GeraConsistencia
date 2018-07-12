package util;

public class MensagensFactory {

	private static String msgDeErroParenteseSuperior() {
		StringBuffer msg = new StringBuffer();
		msg.append("DCLGEN desalinhada.\n");
		msg.append("Verificar se o parêntese na mesma linha das variaveis.           \n");
		msg.append("Exemplo DCL correta\n");
		msg.append("EXEC SQL DECLARE NOME_DA_TABELA TABLE \n");
		msg.append("( <-- o primeiro campo deve esta a baixo do parenteses\n");
		msg.append(" campo_tabela             DECIMAL (10, 0)");
		return msg.toString().toUpperCase();
	}

	public static final String mensagemDeErroParenteseSuperior = msgDeErroParenteseSuperior();

	private static String msgDeErroParenteseInferior() {
		StringBuffer msg = new StringBuffer();
		msg.append("DCLGEN desalinhada.\n");
		msg.append("Verificar se o parêntese na mesma linha do fim de execução.           \n");
		msg.append("Exemplo DCL correta\n");
		msg.append(") <-- o END-EXEC deve estar na linha de baixo\n");
		msg.append(" END-EXEC.");
		return msg.toString().toUpperCase();
	}

	public static final String mensagemDeErroParenteseInferior = msgDeErroParenteseInferior();
	
	private static String msgDeErroPicDesalinhada() {
		StringBuffer msg = new StringBuffer();
		msg.append("DCLGEN desalinhada.\n");
		msg.append("Verificar se a PIC esta na mesma linha do nome da variavel.\n");
		msg.append("Exemplo DCL correta\n");
		msg.append("05 NOME-DA-VARIAVEL         PIC X(001).");
		return msg.toString().toUpperCase();
	}

	public static final String mensagemDeErroPicDesalinhada = msgDeErroPicDesalinhada();
}
