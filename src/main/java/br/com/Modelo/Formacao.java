package br.com.Modelo;

public class Formacao extends ObjetoCriterio {
	private String tituloTrabalho;
	private String nomeOrientador;
	private String nomeInstituicao;
	private String status;



	public Formacao(int ano, String tituloTrabalho, String nomeOrientador, String nomeInstituicao,
			String status, String nomeCurso) {
		super(nomeCurso, ano);
		this.tituloTrabalho = tituloTrabalho;
		this.nomeOrientador = nomeOrientador;
		this.nomeInstituicao = nomeInstituicao;
		this.status = status;

	}

	public String getTituloTrabalho() {
		return this.tituloTrabalho;
	}

	public void setTituloTrabalho(String tituloTrabalho) {
		this.tituloTrabalho = tituloTrabalho;
	}

	public String getNomeInstituicao() {
		return this.nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNomeOrientador() {
		return this.nomeOrientador;
	}

	public void setNomeOrientador(String nomeOrientador) {
		this.nomeOrientador = nomeOrientador;
	}


}
