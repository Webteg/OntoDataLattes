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

import br.com.Ontology.modelo.OntoClass;
import br.com.Ontology.modelo.OntoPessoa;

public class PreencherXMLtoOnto {
	XPath xpath;
	public Document xmlfile;

	public PreencherXMLtoOnto(Document xmlfile) {
		XPathFactory xPathfactory = XPathFactory.newInstance();
		this.xpath = xPathfactory.newXPath();
		this.xmlfile = xmlfile;
	}

	public OntoPessoa buscarXML(OntoPessoa pessoa) throws XPathExpressionException {
		pessoa.setListOntoAreaAtuacao(listOntoAreaAtuacao());
		pessoa.setListOntoEvento(listOntoEvento());
		pessoa.setListOntoFormacao(listOntoFormacao());
		pessoa.setListOntoProducao(listOntoProducao());
		pessoa.setListOntoProjetoPesquisa(listOntoProjetoPesquisa());
		return pessoa;
	}

	private ArrayList<OntoClass> listOntoProjetoPesquisa() throws XPathExpressionException {
		ArrayList<OntoClass> result = BuscaProjetoPesquisa();
		return result;
	}

	private ArrayList<OntoClass> listOntoProducao() {
		ArrayList<OntoClass> result = new ArrayList<>();
		return result;
	}

	private ArrayList<OntoClass> listOntoFormacao() throws XPathExpressionException {
		ArrayList<OntoClass> result = new ArrayList<>();
		result.addAll(buscaFormacao("//GRADUACAO", "Graduacao", 1, "TITULO-DO-TRABALHO-DE-CONCLUSAO-DE-CURSO",
				"NUMERO-ID-ORIENTADOR"));
		result.addAll(
				buscaFormacao("//ESPECIALIZACAO", "Especializacao", 1, "TITULO-DA-MONOGRAFIA", "NOME-DO-ORIENTADOR"));
		result.addAll(buscaFormacao("//MESTRADO", "MestradoAcademico", 1, "TITULO-DA-DISSERTACAO-TESE",
				"NUMERO-ID-ORIENTADOR"));
		result.addAll(
				buscaFormacao("//DOUTORADO", "Doutorado", 1, "TITULO-DA-DISSERTACAO-TESE", "NUMERO-ID-ORIENTADOR"));
		result.addAll(
				buscaFormacao("//POS-DOUTORADO", "Pos-Doutorado", 1, "TITULO-DO-TRABALHO", "NUMERO-ID-ORIENTADOR"));
		result.addAll(buscaFormacao("//MESTRADO-PROFISSIONALIZANTE", "MestradoProfissional", 1,
				"TITULO-DA-DISSERTACAO-TESE", "NUMERO-ID-ORIENTADOR"));
		result.addAll(
				buscaFormacao("//APERFEICOAMENTO", "Aperfeicoamento", 1, "TITULO-DA-MONOGRAFIA", "NOME-DO-ORIENTADOR"));
		return result;
	}

	private ArrayList<OntoClass> listOntoEvento() throws XPathExpressionException {
		ArrayList<OntoClass> result = new ArrayList<>();
		result.addAll(buscaEvento("//PARTICIPACAO-EM-CONGRESSO", "Congresso", 1, "NOME-DO-EVENTO"));
		result.addAll(buscaEvento("//PARTICIPACAO-EM-FEIRA", "Feira", 1, "NOME-DO-EVENTO"));
		result.addAll(buscaEvento("//PARTICIPACAO-EM-SEMINARIO", "Seminario", 1, "NOME-DO-EVENTO"));
		result.addAll(buscaEvento("//PARTICIPACAO-EM-SIMPOSIO", "Simposio", 1, "NOME-DO-EVENTO"));
		result.addAll(buscaEvento("//PARTICIPACAO-EM-OFICINA", "Oficina", 1, "NOME-DO-EVENTO"));
		result.addAll(buscaEvento("//PARTICIPACAO-EM-ENCONTRO", "Encontro", 1, "NOME-DO-EVENTO"));
		result.addAll(buscaEvento("//PARTICIPACAO-EM-EXPOSICAO", "Exposicao", 1, "NOME-DO-EVENTO"));
		result.addAll(buscaEvento("//PARTICIPACAO-EM-OLIMPIADA", "Olimpiada", 1, "NOME-DO-EVENTO"));
		return result;
	}

	private ArrayList<OntoClass> listOntoAreaAtuacao() {
		ArrayList<OntoClass> result = new ArrayList<>();
		return result;
	}

	private ArrayList<OntoClass> buscaEvento(String raiz, String tipo, int NumTitulo, String Nometitulo)
			throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile(raiz);
		NodeList livros = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		ArrayList<OntoClass> listResult = new ArrayList<>();
		for (int i = 0; i < livros.getLength(); i++) {
			Node TipoNode = livros.item(i);
			String titulo = TipoNode.getChildNodes().item(NumTitulo).getAttributes().getNamedItem(Nometitulo)
					.getTextContent().replaceAll("\n", " ");

			ArrayList<String> listAutores = new ArrayList<>();
			OntoClass eve = new OntoClass(titulo, tipo, listAutores);
			listResult.add(eve);
		}
		return listResult;
	}

	private ArrayList<OntoClass> buscaFormacao(String raiz, String tipo, int NumTitulo, String Nometitulo,
			String NomeOrientador) throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile(raiz);
		NodeList livros = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		ArrayList<OntoClass> listResult = new ArrayList<>();
		for (int i = 0; i < livros.getLength(); i++) {
			Node TipoNode = livros.item(i);
			if (TipoNode.getAttributes().getNamedItem("STATUS-DO-CURSO").getTextContent().contentEquals("CONCLUIDO")) {
				String titulo = TipoNode.getAttributes().getNamedItem(Nometitulo).getTextContent();
				String nomeOrientador = TipoNode.getAttributes().getNamedItem(NomeOrientador).getTextContent();
				ArrayList<String> listAutores = new ArrayList<>();
				if ((titulo.isEmpty() || titulo == null) && (nomeOrientador.isEmpty() || nomeOrientador == null)) {

				} else {
					listAutores.add(nomeOrientador);
					OntoClass eve = new OntoClass(titulo, tipo, listAutores);
					listResult.add(eve);
				}

			}

		}
		return listResult;
	}

	private ArrayList<OntoClass> BuscaProjetoPesquisa() throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile("//PARTICIPACAO-EM-PROJETO");
		NodeList projetos = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		ArrayList<OntoClass> listResult = new ArrayList<>();
		for (int i = 0; i < projetos.getLength(); i++) {
			Node TipoNode = projetos.item(i);
			if (TipoNode.getChildNodes().getLength() > 1) {
				String titulo = TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("NOME-DO-PROJETO")
						.getTextContent();
				if (titulo.isEmpty() || titulo == null) {

				} else {
					NodeList auxlist = TipoNode.getChildNodes().item(0).getChildNodes();
					ArrayList<String> listAutores = new ArrayList<>();
					for (int j = 0; j < auxlist.getLength(); j++) {
						Node aux = auxlist.item(j);
						if (aux.getNodeName().contentEquals("EQUIPE-DO-PROJETO")) {
							NodeList NodelistAutoresProjeto = aux.getChildNodes();
							for (int t = 0; t < NodelistAutoresProjeto.getLength(); t++) {
								Node Autores = NodelistAutoresProjeto.item(t);
								// String aux0 =
								// Autores.getAttributes().getNamedItem("NOME-COMPLETO").getTextContent());

								Node nn = Autores.getAttributes().getNamedItem("NRO-ID-CNPQ");
								if (nn != null) {
									String aux0 = nn.getTextContent();
									listAutores.add(aux0.replaceAll(" ", "_"));
								}
							}
						}
					}
					OntoClass eve = new OntoClass(titulo, "ProjetoPesquisa", listAutores);
					listResult.add(eve);
				}

			}
		}
		return listResult;
	}

}
