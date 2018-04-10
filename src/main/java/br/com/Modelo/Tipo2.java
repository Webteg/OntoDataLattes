package br.com.Modelo;

/**
 * 
 */
public class Tipo2 extends Tipo8 {

	/**
	 * Default constructor
	 * 
	 * /**
	 * 
	 */
	private String codigo;

	public Tipo2(String titulo, int ano, String natureza, String tipo, String codigo) {
		super(titulo, ano, natureza, tipo);
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void imprimirTipo2() {
		imprimirTipo8();
		System.out.println("codigo: " + codigo);
	}

}