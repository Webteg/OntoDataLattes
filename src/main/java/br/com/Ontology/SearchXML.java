package br.com.Ontology;

import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.Modelo.Autores;
import br.com.Modelo.Evento;
import br.com.Modelo.ObjetoCriterio;
import br.com.Modelo.Orientacao;
import br.com.Modelo.Producao;

public class SearchXML {

	XPath xpath;
	public Document xmlfile;

	public SearchXML(Document xmlfile) {
		XPathFactory xPathfactory = XPathFactory.newInstance();
		this.xpath = xPathfactory.newXPath();
		this.xmlfile = xmlfile;
	}

	public ArrayList<ObjetoCriterio> BuscaProducao(String raiz, int a, String aux) throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile(raiz);
		NodeList livros = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		ArrayList<ObjetoCriterio> ListArtigoCompleto = new ArrayList<ObjetoCriterio>();
		for (int i = 0; i < livros.getLength(); i++) {
			Node TipoNode = livros.item(i);
			String titulo = TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("TITULO").getTextContent();
			int ano = Integer
					.valueOf(TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("ANO").getTextContent());
			Producao prod = new Producao(titulo, ano);
			if (aux != null) {
				String campAux = TipoNode.getChildNodes().item(a).getAttributes().getNamedItem(aux).getTextContent();
				prod.setCampAux(campAux);
			}
			NodeList listAutores = TipoNode.getChildNodes();
			for (int j = 0; j < listAutores.getLength(); j++) {
				Node autoresNode = listAutores.item(j);
				if (autoresNode.getNodeName().contentEquals("AUTORES")) {
					String aux0 = autoresNode.getAttributes().getNamedItem("NOME-COMPLETO-DO-AUTOR").getTextContent();
					String aux1 = autoresNode.getAttributes().getNamedItem("NOME-PARA-CITACAO").getTextContent();
					Autores aut = new Autores(aux0, aux1);
					prod.AddAutores(aut);
				}
			}
			ListArtigoCompleto.add(prod);
		}
		return ListArtigoCompleto;
	}

	public ArrayList<ObjetoCriterio> BuscaEvento(String raiz) throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile(raiz);
		NodeList livros = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		ArrayList<ObjetoCriterio> ListArtigoCompleto = new ArrayList<ObjetoCriterio>();
		for (int i = 0; i < livros.getLength(); i++) {
			Node TipoNode = livros.item(i);
			String titulo = TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("TITULO").getTextContent();
			int ano = Integer
					.valueOf(TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("ANO").getTextContent());
			String pais = TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("PAIS")
					.getTextContent();
			String nomeEvento = TipoNode.getChildNodes().item(1).getAttributes().getNamedItem("NOME-DO-EVENTO")
					.getTextContent();
			Evento eve = new Evento(titulo, ano, nomeEvento, pais);

			ListArtigoCompleto.add(eve);
		}
		return ListArtigoCompleto;
	}

	public ArrayList<ObjetoCriterio> BuscaBanca(String raiz, String nomeTitulo, int a, String aux, int b, String aux2)
			throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile(raiz);
		NodeList livros = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		ArrayList<ObjetoCriterio> ListArtigoCompleto = new ArrayList<ObjetoCriterio>();
		for (int i = 0; i < livros.getLength(); i++) {
			Node TipoNode = livros.item(i);
			String titulo = TipoNode.getChildNodes().item(0).getAttributes().getNamedItem(nomeTitulo).getTextContent();
			String natureza = TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("NATUREZA")
					.getTextContent();
			int ano = Integer
					.valueOf(TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("ANO").getTextContent());
			String nome_aluno = TipoNode.getChildNodes().item(1).getAttributes().getNamedItem("NOME-DO-CANDIDATO")
					.getTextContent();
			Orientacao prod = new Orientacao(natureza, titulo, ano, nome_aluno);
			if (aux != null) {
				String campAux = TipoNode.getChildNodes().item(a).getAttributes().getNamedItem(aux).getTextContent();
				prod.setCampAux(campAux);
			}
			if (aux2 != null) {
				String campAux2 = TipoNode.getChildNodes().item(b).getAttributes().getNamedItem(aux2).getTextContent();
				prod.setCampAux2(campAux2);
			}
			NodeList listAutores = TipoNode.getChildNodes();
			for (int j = 0; j < listAutores.getLength(); j++) {
				Node autoresNode = listAutores.item(j);
				if (autoresNode.getNodeName().contentEquals("PARTICIPANTE-BANCA")) {
					String aux0 = autoresNode.getAttributes().getNamedItem("NOME-COMPLETO-DO-PARTICIPANTE-DA-BANCA")
							.getTextContent();
					String aux1 = autoresNode.getAttributes().getNamedItem("NOME-PARA-CITACAO-DO-PARTICIPANTE-DA-BANCA")
							.getTextContent();
					Autores aut = new Autores(aux0, aux1);
					prod.AddAutores(aut);
				}
			}
			ListArtigoCompleto.add(prod);
		}
		return ListArtigoCompleto;
	}

	public ArrayList<ObjetoCriterio> BuscaBancaDif(String raiz, String nomeTitulo, int a, String aux, int b,
			String aux2)
			throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile(raiz);
		NodeList livros = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		ArrayList<ObjetoCriterio> ListArtigoCompleto = new ArrayList<ObjetoCriterio>();
		for (int i = 0; i < livros.getLength(); i++) {
			Node TipoNode = livros.item(i);
			String titulo = TipoNode.getChildNodes().item(0).getAttributes().getNamedItem(nomeTitulo).getTextContent();
			String natureza = TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("NATUREZA")
					.getTextContent();
			int ano = Integer
					.valueOf(TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("ANO").getTextContent());
			Orientacao prod = new Orientacao(natureza, titulo, ano, "");
			if (aux != null) {
				String campAux = TipoNode.getChildNodes().item(a).getAttributes().getNamedItem(aux).getTextContent();
				prod.setCampAux(campAux);
			}
			if (aux2 != null) {
				String campAux2 = TipoNode.getChildNodes().item(b).getAttributes().getNamedItem(aux2).getTextContent();
				prod.setCampAux2(campAux2);
			}
			NodeList listAutores = TipoNode.getChildNodes();
			for (int j = 0; j < listAutores.getLength(); j++) {
				Node autoresNode = listAutores.item(j);
				if (autoresNode.getNodeName().contentEquals("PARTICIPANTE-BANCA")) {
					String aux0 = autoresNode.getAttributes().getNamedItem("NOME-COMPLETO-DO-PARTICIPANTE-DA-BANCA")
							.getTextContent();
					String aux1 = autoresNode.getAttributes().getNamedItem("NOME-PARA-CITACAO-DO-PARTICIPANTE-DA-BANCA")
							.getTextContent();
					Autores aut = new Autores(aux0, aux1);
					prod.AddAutores(aut);
				}
			}
			ListArtigoCompleto.add(prod);
		}
		return ListArtigoCompleto;
	}

	public ArrayList<ObjetoCriterio> BuscaOrientacaoAnd(String raiz, String NomeTitulo, int a, String aux, int b,
			String aux2)
			throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile(raiz);
		NodeList artigos = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		ArrayList<ObjetoCriterio> ListArtigoCompleto = new ArrayList<ObjetoCriterio>();
		for (int i = 0; i < artigos.getLength(); i++) {
			Node artigoNode = artigos.item(i);
			String titulo = artigoNode.getChildNodes().item(0).getAttributes().getNamedItem(NomeTitulo)
					.getTextContent();
			String natureza = artigoNode.getChildNodes().item(0).getAttributes().getNamedItem("NATUREZA")
					.getTextContent();
			int ano = Integer
					.valueOf(artigoNode.getChildNodes().item(0).getAttributes().getNamedItem("ANO").getTextContent());
			String nome_aluno = artigoNode.getChildNodes().item(1).getAttributes().getNamedItem("NOME-DO-ORIENTANDO")
					.getTextContent();

			Orientacao prod = new Orientacao(natureza, titulo, ano, nome_aluno);
			if (aux != null) {
				String campAux = artigoNode.getChildNodes().item(a).getAttributes().getNamedItem(aux).getTextContent();
				prod.setCampAux(campAux);
			}
			if (aux2 != null) {
				String campAux2 = artigoNode.getChildNodes().item(b).getAttributes().getNamedItem(aux2)
						.getTextContent();
				prod.setCampAux2(campAux2);
			}

			ListArtigoCompleto.add(prod);
		}

		return ListArtigoCompleto;
	}

	public ArrayList<ObjetoCriterio> BuscaOrientacaoCon(String raiz, String NomeTitulo, int a, String aux, int b,
			String aux2)
			throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile(raiz);
		NodeList artigos = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		ArrayList<ObjetoCriterio> ListArtigoCompleto = new ArrayList<ObjetoCriterio>();
		for (int i = 0; i < artigos.getLength(); i++) {
			Node artigoNode = artigos.item(i);
			String titulo = artigoNode.getChildNodes().item(0).getAttributes().getNamedItem(NomeTitulo)
					.getTextContent();
			String natureza = artigoNode.getChildNodes().item(0).getAttributes().getNamedItem("NATUREZA")
					.getTextContent();
			int ano = Integer
					.valueOf(artigoNode.getChildNodes().item(0).getAttributes().getNamedItem("ANO").getTextContent());
			String nome_aluno = artigoNode.getChildNodes().item(1).getAttributes().getNamedItem("NOME-DO-ORIENTADO")
					.getTextContent();

			Orientacao prod = new Orientacao(natureza, titulo, ano, nome_aluno);
			if (aux != null) {
				String campAux = artigoNode.getChildNodes().item(a).getAttributes().getNamedItem(aux).getTextContent();
				prod.setCampAux(campAux);
			}
			if (aux2 != null) {
				String campAux2 = artigoNode.getChildNodes().item(b).getAttributes().getNamedItem(aux2)
						.getTextContent();
				prod.setCampAux2(campAux2);
			}

			ListArtigoCompleto.add(prod);
		}
		return ListArtigoCompleto;
	}





}
