package br.com.Ontology.modelo;

import java.util.ArrayList;

public class OntoClass {
	private String Titulo;
	private String Tipo;
	private ArrayList<String> ListAutores;

	public OntoClass(String titulo, String tipo, ArrayList<String> listAutores) {
		super();
		this.Titulo = titulo.replaceAll(" ", "_");
		this.Tipo = tipo;
		this.ListAutores = listAutores;
	}

	public String getTitulo() {
		return this.Titulo;
	}

	public void setTitulo(String titulo) {
		this.Titulo = titulo.replaceAll(" ", "_");
	}

	public void ifem() {
		this.ListAutores.forEach(u -> u.replaceAll(" ", "_"));
	}

	public ArrayList<String> getListAutores() {
		ifem();
		return this.ListAutores;
	}

	public void setListAutores(ArrayList<String> listAutores) {
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
		return "\n OntoClass [Titulo=" + this.Titulo + ", Tipo=" + this.Tipo + "\n, ListAutores="
				+ this.ListAutores.toString() + "]";
	}

}
