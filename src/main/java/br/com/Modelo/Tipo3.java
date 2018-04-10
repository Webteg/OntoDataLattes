package br.com.Modelo;




/**
 * 
 */
public class Tipo3 extends Producao {

	/**
	 * Default constructor
	 */
	/**
	 * 
	 */
	private RegistroPatente regPatente;

	public Tipo3(String titulo, int ano,
			RegistroPatente regPatente) {
		super(titulo, ano );
		this.regPatente = regPatente;
	}

	public RegistroPatente getRegPatente() {
		return regPatente;
	}

	public void setRegPatente(RegistroPatente regPatente) {
		this.regPatente = regPatente;
	}
	public void imprimirTipo3() {
		imprimirProducao();
		regPatente.imprimir();
	}
}