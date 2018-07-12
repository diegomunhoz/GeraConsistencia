package modelos;

import java.util.ArrayList;
import java.util.List;

public class DclgenTO {

	private String nomeDaTabela;
	private String nomeDaDclgen;
	private List<VariavelTO> variaveisDaDCLGEN = new ArrayList<VariavelTO>();
	private List<String> listaDeNotNull = new ArrayList<String>();
	private List<String> listaDeNaoNotNull = new ArrayList<String>();

	public String getNomeDaTabela() {
		return nomeDaTabela;
	}

	public void setNomeDaTabela(String nomeDaTabela) {
		this.nomeDaTabela = nomeDaTabela;
	}

	public String getNomeDaDclgen() {
		return nomeDaDclgen;
	}

	public void setNomeDaDclgen(String nomeDaDclgen) {
		this.nomeDaDclgen = nomeDaDclgen;
	}

	public List<VariavelTO> getVariaveisDaDCLGEN() {
		return variaveisDaDCLGEN;
	}

	public void setVariaveisDaDCLGEN(List<VariavelTO> variaveisDaDCLGEN) {
		this.variaveisDaDCLGEN = variaveisDaDCLGEN;
	}

	public List<String> getListaDeNotNull() {
		return listaDeNotNull;
	}

	public void setListaDeNotNull(List<String> listaDeNotNull) {
		this.listaDeNotNull = listaDeNotNull;
	}

	public List<String> getListaDeNaoNotNull() {
		return listaDeNaoNotNull;
	}

	public void setListaDeNaoNotNull(List<String> listaDeNaoNotNull) {
		this.listaDeNaoNotNull = listaDeNaoNotNull;
	}

}
