package br.com.Ontology.modelo;

/**
 * 
 */
public class AreaConhecimento {

	private String areaConhecimento;

	private String subAreaConhecimento;

	private String nomeEspecialidade;

	public AreaConhecimento(String areaConhecimento, String subAreaConhecimento, String nomeEspecialidade) {
		super();
		this.areaConhecimento = areaConhecimento.replaceAll(" ", "_");
		this.subAreaConhecimento = subAreaConhecimento.replaceAll(" ", "_");
		this.nomeEspecialidade = nomeEspecialidade.replaceAll(" ", "_");
	}

	public String getAreaConhecimento() {
		return this.areaConhecimento;
	}

	public void setAreaConhecimento(String areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	public String getSubAreaConhecimento() {
		return this.subAreaConhecimento;
	}

	public void setSubAreaConhecimento(String subAreaConhecimento) {
		this.subAreaConhecimento = subAreaConhecimento;
	}

	public String getNomeEspecialidade() {
		return this.nomeEspecialidade;
	}

	public void setNomeEspecialidade(String nomeEspecialidade) {
		this.nomeEspecialidade = nomeEspecialidade;
	}

	@Override
	public String toString() {
		return "AreaConhecimento [areaConhecimento=" + this.areaConhecimento + ", subAreaConhecimento="
				+ this.subAreaConhecimento + ", nomeEspecialidade=" + this.nomeEspecialidade + "]";
	}

}