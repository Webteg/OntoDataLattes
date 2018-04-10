package br.com.Modelo;

/**
 * 
 */
public class Autores {

	/**
	 * Default constructor
	 */


	/**
	 * 
	 */
	private String nome;

	/**
	 * 
	 */
	private String citacao;

	public Autores(String nome, String citacao) {
		super();
		this.nome = nome;
		this.citacao = citacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCitacao() {
		return citacao;
	}

	public void setCitacao(String citacao) {
		this.citacao = citacao;
	}
	public void imprimir() {
		System.out.println("nome: " + nome);
		System.out.println("citacao: " + citacao);
	}

}