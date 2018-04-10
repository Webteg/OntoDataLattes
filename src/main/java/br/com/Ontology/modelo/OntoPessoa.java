package br.com.Ontology.modelo;

import java.util.ArrayList;

public class OntoPessoa {
	private String NomeCompleto;
	private String IdLattes;
	private String Data;
	private ArrayList<OntoClass> ListOntoAreaAtuacao;
	private ArrayList<OntoClass> ListOntoEvento;
	private ArrayList<OntoClass> ListOntoFormacao;
	private ArrayList<OntoClass> ListOntoProducao;
	private ArrayList<OntoClass> ListOntoProjetoPesquisa;

	public OntoPessoa(String nomeCompleto, String idLattes, String data) {
		super();
		this.NomeCompleto = nomeCompleto;
		this.IdLattes = idLattes;
		this.Data = data;
	}

	public String getNomeCompleto() {
		return this.NomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.NomeCompleto = nomeCompleto.replaceAll(" ", "_");
	}

	public String getIdLattes() {
		return this.IdLattes;
	}

	public void setIdLattes(String idLattes) {
		this.IdLattes = idLattes;
	}

	public String getData() {
		return this.Data;
	}

	public void setData(String data) {
		this.Data = data;
	}

	public ArrayList<OntoClass> getListOntoAreaAtuacao() {
		return this.ListOntoAreaAtuacao;
	}

	public void setListOntoAreaAtuacao(ArrayList<OntoClass> listOntoAreaAtuacao) {
		this.ListOntoAreaAtuacao = listOntoAreaAtuacao;
	}

	public ArrayList<OntoClass> getListOntoEvento() {
		return this.ListOntoEvento;
	}

	public void setListOntoEvento(ArrayList<OntoClass> listOntoEvento) {
		this.ListOntoEvento = listOntoEvento;
	}

	public ArrayList<OntoClass> getListOntoFormacao() {
		return this.ListOntoFormacao;
	}

	public void setListOntoFormacao(ArrayList<OntoClass> listOntoFormacao) {
		this.ListOntoFormacao = listOntoFormacao;
	}

	public ArrayList<OntoClass> getListOntoProducao() {
		return this.ListOntoProducao;
	}

	public void setListOntoProducao(ArrayList<OntoClass> listOntoProducao) {
		this.ListOntoProducao = listOntoProducao;
	}

	public ArrayList<OntoClass> getListOntoProjetoPesquisa() {
		return this.ListOntoProjetoPesquisa;
	}

	public void setListOntoProjetoPesquisa(ArrayList<OntoClass> listOntoProjetoPesquisa) {
		this.ListOntoProjetoPesquisa = listOntoProjetoPesquisa;
	}

	@Override
	public String toString() {
		return "OntoPessoa [NomeCompleto=" + this.NomeCompleto + "\n, IdLattes=" + this.IdLattes + "\n, Data="
				+ this.Data + "\n, ListOntoAreaAtuacao=" + this.ListOntoAreaAtuacao.toString() + "\n, ListOntoEvento="
				+ this.ListOntoEvento.toString() + "\n, ListOntoFormacao=" + this.ListOntoFormacao.toString()
				+ "\n, ListOntoProducao=" + this.ListOntoProducao.toString() + "\n, ListOntoProjetoPesquisa="
				+ this.ListOntoProjetoPesquisa.toString() + "]";
	}

}
