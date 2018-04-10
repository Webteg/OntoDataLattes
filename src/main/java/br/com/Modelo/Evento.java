package br.com.Modelo;

public class Evento extends ObjetoCriterio {
	private String NomeEvento;
	private String pais;

	public Evento(String titulo, int ano, String nomeEvento, String pais) {
		super(titulo, ano);
		this.NomeEvento = nomeEvento;
		this.pais = pais;
	}

	public String getNomeEvento() {
		return this.NomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.NomeEvento = nomeEvento;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}


}
