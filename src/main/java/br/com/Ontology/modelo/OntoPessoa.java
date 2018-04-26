package br.com.Ontology.modelo;

import java.util.ArrayList;

public class OntoPessoa {
	private String NomeCompleto;
	private String IdLattes;
	private String Data;
	private ArrayList<AreaConhecimento> ListOntoAreaAtuacao;
	private ArrayList<TrabalhoEvento> ListOntoTrabalhoEvento;
	private ArrayList<OntoClass> ListOntoEvento;
	private ArrayList<OntoClass> ListOntoOrgEvento;
	private ArrayList<OntoClass> ListOntoFormacao;
	private ArrayList<OntoClass> ListOntoProducao;
	private ArrayList<OntoClass> ListOntoProjetoPesquisa;
	private ArrayList<OntoClass> listOntoBanca;

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
		this.NomeCompleto = nomeCompleto;
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

	public ArrayList<AreaConhecimento> getListOntoAreaAtuacao() {
		return this.ListOntoAreaAtuacao;
	}

	public void setListOntoAreaAtuacao(ArrayList<AreaConhecimento> listOntoAreaAtuacao) {
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

	public ArrayList<OntoClass> getListOntoBanca() {
		return this.listOntoBanca;
	}

	public void setListOntoBanca(ArrayList<OntoClass> listOntoBanca) {
		this.listOntoBanca = listOntoBanca;
	}

	@Override
	public String toString() {
		return "OntoPessoa [NomeCompleto=" + this.NomeCompleto + "\n, IdLattes=" + this.IdLattes + "\n, Data="
				+ this.Data + "\n, ListOntoAreaAtuacao=" + this.ListOntoAreaAtuacao.toString() + "\n, ListOntoEvento="
				+ this.ListOntoEvento.toString() + "\n, ListOntoFormacao=" + this.ListOntoFormacao.toString()
				+ "\n, ListOntoProducao=" + this.ListOntoProducao.toString() + "\n, ListOntoProjetoPesquisa="
				+ this.ListOntoProjetoPesquisa.toString() + "]";
	}

	public ArrayList<OntoClass> getListOntoOrgEvento() {
		return this.ListOntoEvento;
	}

	public void setListOntoOrgEvento(ArrayList<OntoClass> listOntoOrgEvento) {
		this.ListOntoEvento.addAll(listOntoOrgEvento);
	}

	public ArrayList<TrabalhoEvento> getListOntoTrabalhoEvento() {
		return this.ListOntoTrabalhoEvento;
	}

	public void setListOntoTrabalhoEvento(ArrayList<TrabalhoEvento> listOntoTrabalhoEvento) {
		this.ListOntoTrabalhoEvento = listOntoTrabalhoEvento;
	}


}
