package modelos;

import java.util.ArrayList;
import java.util.List;

public class BookTO {

	private String nome;
	private List<VariavelTO> variaveis = new ArrayList<VariavelTO>();
	private StringBuffer bookFormatada = new StringBuffer();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<VariavelTO> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(List<VariavelTO> variaveis) {
		this.variaveis = variaveis;
	}

	public void setBookFormatada(StringBuffer bookFormatada) {
		this.bookFormatada = bookFormatada;
	}

	public StringBuffer getBookFormatada() {
		return bookFormatada;
	}

}
