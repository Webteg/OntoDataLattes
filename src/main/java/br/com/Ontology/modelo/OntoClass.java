package br.com.Ontology.modelo;

import java.util.ArrayList;

public class OntoClass {
	private String Titulo;
	private String Tipo;
	private ArrayList<OntoParceiro> ListAutores;
	private int ano;

	public OntoClass(String titulo, String tipo, ArrayList<OntoParceiro> listAutores) {
		super();
		this.Titulo = titulo;
		this.Tipo = tipo;
		this.ListAutores = listAutores;
	}

	public OntoClass(String titulo, String tipo, int ano) {
		super();
		this.Titulo = titulo;
		this.Tipo = tipo;
		this.ListAutores = new ArrayList<>();
		this.ano = ano;
	}

	public String getTitulo() {
		return this.Titulo;
	}

	public void setTitulo(String titulo) {
		this.Titulo = titulo;
	}

	public ArrayList<OntoParceiro> getListAutores() {
		return this.ListAutores;
	}

	public void setListAutores(ArrayList<OntoParceiro> listAutores) {
		this.ListAutores = listAutores;
	}

	public String getTipo() {
		return this.Tipo;
	}

	public void setTipo(String tipo) {
		this.Tipo = tipo;
	}

	@Override
	public String toString() {
		return "\n OntoClass [Titulo=" + this.Titulo + ", Ano=" + this.ano + ", Tipo=" + this.Tipo + "\n, ListAutores="
				+ this.ListAutores.toString() + "]";
	}

	public int getAno() {
		return this.ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

}
