package br.com.Modelo;

/**
 * 
 */
public class Tipo1 extends Producao {

	/**
	 * Default constructor
	 */

	/**
	 * 
	 */
	private String codigo;

	public Tipo1(String titulo, int ano,  String codigo) {
		super(titulo, ano);
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public void imprimirTipo1() {
		imprimirProducao();
		System.out.println("codigo: " + codigo);
	}

}