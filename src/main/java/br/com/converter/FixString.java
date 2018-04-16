package br.com.converter;

public class FixString {

	public String corrigirString(String text) {
		return text.replaceAll(">", "").replaceAll("<", "").replaceAll("#", "").replaceAll("\t", "")
				.replaceAll("\n", " ").replaceAll(" ", "_").replaceAll("'", "").toLowerCase();
	}
}
