package br.com.Modelo;

/**
 * 
 */
public class Tipo0 extends Tipo6 {

	/**
	 * Default constructor
	 */

	/**
	 * 
	 */
	private String codigo;

	public Tipo0(String titulo, int ano, String natureza, String codigo) {
		super(titulo, ano, natureza);
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public void imprimirTipo0() {
		imprimirTipo6();
		System.out.println("codigo: "+ codigo );
	}
}