package br.com.Modelo;



/**
 * 
 */
public class Tipo5 extends Tipo8 {

	
	private RegistroPatente regPatente;



	public Tipo5(String titulo, int ano,  String natureza,
			String tipo, RegistroPatente regPatente) {
		super(titulo, ano,  natureza, tipo );
		this.regPatente = regPatente;
	}

	public RegistroPatente getRegPatente() {
		return regPatente;
	}

	public void setRegPatente(RegistroPatente regPatente) {
		this.regPatente = regPatente;
	}
	public void imprimirTipo5() {
		imprimirTipo8();
		this.regPatente.imprimir();
	}

}