package br.com.Modelo;

/**
 * 
 */
public class Tipo8 extends Tipo6 {

	/**
	 * 
	 * /**
	 * 
	 */
	private String tipo;

	/**
	 * 
	 */

	public Tipo8(String titulo, int ano, String natureza, String tipo) {
		super(titulo, ano, natureza);
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void imprimirTipo8() {
		imprimirTipo6();
		System.out.println("tipo: " + tipo);
	}

}