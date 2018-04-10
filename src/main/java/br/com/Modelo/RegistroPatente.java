package br.com.Modelo;

/**
 * 
 */
public class RegistroPatente {
	private String tipoPatente;
	private String codigoPatente;
	private String dataConcessao;
	private String nomeTitular;

	public RegistroPatente(String tipoPatente, String codigoPatente, String dataConcessao, String nomeTitular) {
		super();
		this.tipoPatente = tipoPatente;
		this.codigoPatente = codigoPatente;
		this.dataConcessao = dataConcessao;
		this.nomeTitular = nomeTitular;
	}

	public String getTipoPatente() {
		return this.tipoPatente;
	}

	public void setTipoPatente(String tipoPatente) {
		this.tipoPatente = tipoPatente;
	}

	public String getCodigoPatente() {
		return this.codigoPatente;
	}

	public void setCodigoPatente(String codigoPatente) {
		this.codigoPatente = codigoPatente;
	}

	public String getDataConcessao() {
		return this.dataConcessao;
	}

	public void setDataConcessao(String dataConcessao) {
		this.dataConcessao = dataConcessao;
	}

	public String getNomeTitular() {
		return this.nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public void imprimir() {
		System.out.println("tipoPatente: " + this.tipoPatente);
		System.out.println("codigoPatente: " + this.codigoPatente);
		System.out.println("dataConcessao: " + this.dataConcessao);
		System.out.println("nomeTitular: " + this.nomeTitular);
	}

}