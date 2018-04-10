package br.com.Modelo;

/**
 * 
 */
public class Tipo6 extends Producao {

	private String natureza;

	public Tipo6(String titulo, int ano, String natureza) {
		super(titulo, ano);
		this.natureza = natureza;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}
	public void imprimirTipo6() {
		imprimirProducao();
		System.out.println("natureza: " + natureza);
	}

}