package modelos;

public class VariavelTO {

	private String nome;
	private String pic;
	private Integer inteiro;
	private Integer decimais;
	private Boolean haveDecimais = false;
	private Boolean isNumerica = false;
	private Boolean isAlfaNumerica = false;
	private Boolean isSinalizado = false;
	private Boolean isNotNull = false;
	private Boolean isItenDeGrupo = false;
	private Integer nivel;
	private Boolean entrada = false;
	private Boolean saida = false;
	private Boolean where = false;
	private Boolean paginacao = false;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getInteiro() {
		return inteiro;
	}

	public void setInteiro(Integer inteiro) {
		this.inteiro = inteiro;
	}

	public Integer getDecimais() {
		return decimais;
	}

	public void setDecimais(Integer decimais) {
		this.decimais = decimais;
	}

	public Boolean haveDecimais() {
		return haveDecimais;
	}

	public void setHaveDecimais(Boolean haveDecimais) {
		this.haveDecimais = haveDecimais;
	}

	public Boolean isNumerica() {
		return isNumerica;
	}

	public void setIsNumerica(Boolean isNumerica) {
		this.isNumerica = isNumerica;
	}

	public Boolean isAlfaNumerica() {
		return isAlfaNumerica;
	}

	public void isAlfaNumerica(Boolean isAlfaNumerica) {
		this.isAlfaNumerica = isAlfaNumerica;
	}

	public Boolean isSinalizado() {
		return isSinalizado;
	}

	public void setIsSinalizado(Boolean isSinalizado) {
		this.isSinalizado = isSinalizado;
	}

	public Boolean isNotNull() {
		return isNotNull;
	}

	public void setIsNotNull(Boolean isNotNull) {
		this.isNotNull = isNotNull;
	}

	public Boolean isItenDeGrupo() {
		return isItenDeGrupo;
	}

	public void setIsItenDeGrupo(Boolean isItenDeGrupo) {
		this.isItenDeGrupo = isItenDeGrupo;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Boolean isEntrada() {
		return entrada;
	}

	public void setEntrada(Boolean entrada) {
		this.entrada = entrada;
	}

	public Boolean isSaida() {
		return saida;
	}

	public void setSaida(Boolean saida) {
		this.saida = saida;
	}

	public Boolean isWhere() {
		return where;
	}

	public void setWhere(Boolean where) {
		this.where = where;
	}

	public Boolean isPaginacao() {
		return paginacao;
	}

	public void setPaginacao(Boolean paginacao) {
		this.paginacao = paginacao;
	}

}
