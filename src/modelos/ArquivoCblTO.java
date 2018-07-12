package modelos;

import java.util.ArrayList;
import java.util.List;

public class ArquivoCblTO {

	private String nomeArquivo;
	private List<String> procedimentos = new ArrayList<String>();
	private String caminhoDoCbl;
	private StringBuffer arquivoEmString;

	public StringBuffer getArquivoEmString() {
		return arquivoEmString;
	}

	public void setArquivoEmString(StringBuffer arquivoEmString) {
		this.arquivoEmString = arquivoEmString;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public List<String> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<String> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public void setCaminhoDoCbl(String caminhoDoCbl) {
		this.caminhoDoCbl = caminhoDoCbl;
	}

	public String getCaminhoDoCbl() {
		return caminhoDoCbl;
	}

}
