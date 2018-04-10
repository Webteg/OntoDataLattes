package br.com.Modelo;

import java.util.ArrayList;

/**
 * 
 */
public class Producao extends ObjetoCriterio {

	private String titulo;
	private int ano;
	private ArrayList<String> palavra_chave;
	private ArrayList<Autores> autores;
	private ArrayList<AreaConhecimento> areaConhecimento;
	private String campAux;
	private String campAux2;
	
	
	
	
	public Producao(String titulo, int ano) {
		super(titulo, ano);
		this.titulo = titulo;
		this.ano = ano;
		this.palavra_chave = new ArrayList<String>();
		this.autores = new ArrayList<Autores>();
		this.areaConhecimento = new ArrayList<AreaConhecimento>();
	}


	@Override
	public String getTitulo() {
		return this.titulo;
	}

	@Override
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public int getAno() {
		return this.ano;
	}

	@Override
	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getCampAux() {
		return this.campAux;
	}

	public void setCampAux(String campAux) {
		this.campAux = campAux;
	}

	public String getCampAux2() {
		return this.campAux2;
	}

	public void setCampAux2(String campAux2) {
		this.campAux2 = campAux2;
	}

	public ArrayList<Autores> getAutores() {
		return this.autores;
	}

	public void AddAutores(Autores autores) {
		this.autores.add(autores);
	}

	public ArrayList<AreaConhecimento> getAreaConhecimento() {
		return this.areaConhecimento;
	}

	public void AddAreaConhecimento(AreaConhecimento areaConhecimento) {
		this.areaConhecimento.add(areaConhecimento);
	}

	public ArrayList<String> getPalavra_chave() {
		return this.palavra_chave;
	}

	public void AddAreaConhecimento(String palavra_chave) {
		this.palavra_chave.add(palavra_chave);
	}

	public void imprimirProducao() {
		System.out.println("************************");
		System.out.println("titulo: " + this.titulo);
		System.out.println("ano: " + this.ano);
		System.out.println("aux: " + this.campAux);
		System.out.println("aux2: " + this.campAux2);
		System.out.println("%%%%%%% AUTORES %%%%%%%");
		this.autores.forEach(n -> n.imprimir());
		System.out.println("%%%%%%% PALAVRA-CHAVE %%%%%%%");
		this.palavra_chave.forEach(n -> System.out.println("Palavra-chave :" + n));
		System.out.println("%%%%%%% AREA CONHECIMENTO %%%%%%%");
		this.areaConhecimento.forEach(n -> n.imprimir());
	}

}