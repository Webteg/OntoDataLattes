package br.com.Modelo;

public class ObjetoCriterio {
	private String titulo;
	private int ano;

	public ObjetoCriterio(String titulo, int ano) {
		super();
		this.titulo = titulo;
		this.ano = ano;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAno() {
		return this.ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
}
