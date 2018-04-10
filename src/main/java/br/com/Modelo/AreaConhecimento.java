package br.com.Modelo;

/**
 * 
 */
public class AreaConhecimento {

	private String grandeArea;

	private String areaConhecimento;

	private String subArea;

	public AreaConhecimento(String grandeArea, String areaConhecimento, String subArea) {
		super();
		this.grandeArea = grandeArea;
		this.areaConhecimento = areaConhecimento;
		this.subArea = subArea;
	}

	public String getGrandeArea() {
		return grandeArea;
	}

	public void setGrandeArea(String grandeArea) {
		this.grandeArea = grandeArea;
	}

	public String getAreaConhecimento() {
		return areaConhecimento;
	}

	public void setAreaConhecimento(String areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	public String getSubArea() {
		return subArea;
	}

	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}
	public void imprimir() {
		System.out.println("grandeArea: " + grandeArea);
		System.out.println("areaConhecimento: " + areaConhecimento);
		System.out.println("subArea: " + subArea);
	}

}