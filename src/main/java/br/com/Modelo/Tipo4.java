package br.com.Modelo;




/**
 * 
 */
public class Tipo4 extends Tipo3 {

	/**
	 * Default constructor
	 */


	/**
	 * 
	 */
	private String natureza;

	public Tipo4(String titulo, int ano, 
			RegistroPatente regPatente, String natureza) {
		super(titulo, ano,  regPatente);
		this.natureza = natureza;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}
	public void imprimirTipo4() {
		imprimirTipo3();
		System.out.println("natureza: " + natureza);
	}
}