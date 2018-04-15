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

import br.com.Ontology.modelo.AreaConhecimento;
import br.com.Ontology.modelo.OntoClass;
import br.com.Ontology.modelo.OntoParceiro;
import br.com.Ontology.modelo.OntoPessoa;
import br.com.Ontology.modelo.TrabalhoEvento;

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
		pessoa.setListOntoTrabalhoEvento(listOntoTrabalhoEvento());
		pessoa.setListOntoEvento(listOntoEvento());
		pessoa.setListOntoOrgEvento(listOrganizacaoEvento());
		pessoa.setListOntoFormacao(listOntoFormacao());
		pessoa.setListOntoProducao(listOntoProducao());
		pessoa.setListOntoProjetoPesquisa(listOntoProjetoPesquisa());
		pessoa.setListOntoBanca(listOntoBanca());
		return pessoa;
	}

	public String UltimaAtualizacao() throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile("string(/CURRICULO-VITAE[1]/@DATA-ATUALIZACAO)");
		StringBuilder sb = new StringBuilder(expr.evaluate(this.xmlfile));
		sb.insert(8, " ");
		sb.insert(4, "/");
		sb.insert(2, "/");
		return sb.toString();
	}

	public String IDLattes() throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile("string(/CURRICULO-VITAE[1]/@NUMERO-IDENTIFICADOR)");
		return expr.evaluate(this.xmlfile);
	}

	public String NomeCompleto() throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile("string(/*/DADOS-GERAIS[1]/@NOME-COMPLETO)");
		return expr.evaluate(this.xmlfile);
	}

	public String NomeCitacao() throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile("string(/*/DADOS-GERAIS[1]/@NOME-EM-CITACOES-BIBLIOGRAFICAS)");
		return expr.evaluate(this.xmlfile);
	}

	public String ResumoCV() throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile("string(/*/DADOS-GERAIS/RESUMO-CV[1]/@TEXTO-RESUMO-CV-RH)");
		return expr.evaluate(this.xmlfile);
	}

	public boolean DedicaoExclusiva() throws XPathExpressionException {
		XPathExpression expr = this.xpath
				.compile("//ATUACAO-PROFISSIONAL/VINCULOS[@FLAG-DEDICACAO-EXCLUSIVA='SIM' and  @ANO-FIM='']");
		NodeList dedicacao = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		if (dedicacao.getLength() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<OntoClass> listOntoProducao() {
		ArrayList<OntoClass> result = new ArrayList<>();
		return result;
	}

	public ArrayList<TrabalhoEvento> listOntoTrabalhoEvento() throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile("//TRABALHO-EM-EVENTOS");
		NodeList livros = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		ArrayList<TrabalhoEvento> listResult = new ArrayList<>();
		for (int i = 0; i < livros.getLength(); i++) {
			Node TipoNode = livros.item(i);
			String tituloTrabalho = TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("TITULO-DO-TRABALHO")
					.getTextContent().replaceAll("\n", " ");
			String tituloEvento = TipoNode.getChildNodes().item(1).getAttributes().getNamedItem("NOME-DO-EVENTO")
					.getTextContent().replaceAll("\n", " ");
			int ano = Integer.valueOf(
					TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("ANO-DO-TRABALHO").getTextContent());

			OntoClass evento = new OntoClass(tituloEvento, "TrabalhoEmEvento", ano);
			TrabalhoEvento item = new TrabalhoEvento(tituloTrabalho, evento);
			listResult.add(item);
		}
		return listResult;
	}

	public ArrayList<OntoClass> listOntoBanca() throws XPathExpressionException {
		ArrayList<OntoClass> result = new ArrayList<>();
		result.addAll(BuscaBanca("//PARTICIPACAO-EM-BANCA-DE-GRADUACAO", "BancaGraduacao"));
		result.addAll(BuscaBanca("//PARTICIPACAO-EM-BANCA-DE-MESTRADO", "BancaMestrado"));
		result.addAll(BuscaBanca("//PARTICIPACAO-EM-BANCA-DE-DOUTORADO", "BancaDoutorado"));
		result.addAll(BuscaBanca("//PARTICIPACAO-EM-BANCA-DE-EXAME-QUALIFICACAO", "BancaQualificacao"));
		result.addAll(BuscaBanca("//PARTICIPACAO-EM-BANCA-DE-APERFEICOAMENTO-ESPECIALIZACAO", "BancaAperEspe"));
		return result;
	}

	public ArrayList<OntoClass> listOntoFormacao() throws XPathExpressionException {
		ArrayList<OntoClass> result = new ArrayList<>();
		result.addAll(buscaFormacao("//GRADUACAO", "Graduacao", 1, "TITULO-DO-TRABALHO-DE-CONCLUSAO-DE-CURSO",
				"NOME-DO-ORIENTADOR", "NUMERO-ID-ORIENTADOR"));
		// não testado
		result.addAll(buscaFormacao("//APERFEICOAMENTO", "Aperfeicoamento", 1, "TITULO-DA-MONOGRAFIA",
				"NOME-DO-ORIENTADOR", null));
		result.addAll(buscaFormacao("//ESPECIALIZACAO", "Especializacao", 1, "TITULO-DA-MONOGRAFIA",
				"NOME-DO-ORIENTADOR", null));
		result.addAll(buscaFormacao("//MESTRADO", "MestradoAcademico", 1, "TITULO-DA-DISSERTACAO-TESE",
				"NOME-COMPLETO-DO-ORIENTADOR", "NUMERO-ID-ORIENTADOR"));
		result.addAll(buscaFormacao("//DOUTORADO", "Doutorado", 1, "TITULO-DA-DISSERTACAO-TESE",
				"NOME-COMPLETO-DO-ORIENTADOR", "NUMERO-ID-ORIENTADOR"));
		// Não testado
		result.addAll(buscaFormacao("//MESTRADO-PROFISSIONALIZANTE", "MestradoProfissional", 1,
				"TITULO-DA-DISSERTACAO-TESE", "NOME-COMPLETO-DO-ORIENTADOR", "NUMERO-ID-ORIENTADOR"));
		return result;
	}

	public ArrayList<OntoClass> listOrganizacaoEvento() throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile("//ORGANIZACAO-DE-EVENTO");
		NodeList livros = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		ArrayList<OntoClass> listResult = new ArrayList<>();
		for (int i = 0; i < livros.getLength(); i++) {
			Node TipoNode = livros.item(i);
			String titulo = TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("TITULO").getTextContent()
					.replaceAll("\n", " ");
			int ano = Integer
					.valueOf(TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("ANO").getTextContent());

			OntoClass eve = new OntoClass(titulo, "OrganizacaoEvento", ano);
			listResult.add(eve);
		}
		return listResult;
	}

	public ArrayList<OntoClass> listOntoEvento() throws XPathExpressionException {
		ArrayList<OntoClass> result = new ArrayList<>();
		result.addAll(buscaEvento("//PARTICIPACAO-EM-CONGRESSO", "Congresso", 1, "NOME-DO-EVENTO"));
		// não testado
		result.addAll(buscaEvento("//PARTICIPACAO-EM-FEIRA", "Feira", 1, "NOME-DO-EVENTO"));
		result.addAll(buscaEvento("//PARTICIPACAO-EM-SEMINARIO", "Seminario", 1, "NOME-DO-EVENTO"));
		result.addAll(buscaEvento("//PARTICIPACAO-EM-SIMPOSIO", "Simposio", 1, "NOME-DO-EVENTO"));
		result.addAll(buscaEvento("//PARTICIPACAO-EM-ENCONTRO", "Encontro", 1, "NOME-DO-EVENTO"));
		// não testado
		result.addAll(buscaEvento("//PARTICIPACAO-EM-EXPOSICAO", "Exposicao", 1, "NOME-DO-EVENTO"));
		return result;
	}

	public ArrayList<AreaConhecimento> listOntoAreaAtuacao() throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile("//AREA-DE-ATUACAO");
		NodeList listaxml = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		ArrayList<AreaConhecimento> listResult = new ArrayList<>();
		for (int i = 0; i < listaxml.getLength(); i++) {
			Node TipoNode = listaxml.item(i);
			String areaConhecimento = TipoNode.getAttributes().getNamedItem("NOME-DA-AREA-DO-CONHECIMENTO")
					.getTextContent();
			String subAreaConhecimento = TipoNode.getAttributes().getNamedItem("NOME-DA-SUB-AREA-DO-CONHECIMENTO")
					.getTextContent();
			String nomeEspecialidade = TipoNode.getAttributes().getNamedItem("NOME-DA-ESPECIALIDADE").getTextContent();
			listResult.add(new AreaConhecimento(areaConhecimento, subAreaConhecimento, nomeEspecialidade));
		}
		return listResult;
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
			int ano = Integer
					.valueOf(TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("ANO").getTextContent());

			OntoClass eve = new OntoClass(titulo, tipo, ano);
			listResult.add(eve);
		}
		return listResult;
	}

	private ArrayList<OntoClass> buscaFormacao(String raiz, String tipo, int NumTitulo, String Nometitulo,
			String NomeOrientador, String IdOrientador) throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile(raiz);
		NodeList livros = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		ArrayList<OntoClass> listResult = new ArrayList<>();
		for (int i = 0; i < livros.getLength(); i++) {
			Node TipoNode = livros.item(i);
			if (TipoNode.getAttributes().getNamedItem("STATUS-DO-CURSO").getTextContent().contentEquals("CONCLUIDO")) {
				String titulo = TipoNode.getAttributes().getNamedItem(Nometitulo).getTextContent();
				String nomeOrientador = TipoNode.getAttributes().getNamedItem(NomeOrientador).getTextContent();
				String idOrientador = (IdOrientador == null) ? ""
						: TipoNode.getAttributes().getNamedItem(IdOrientador).getTextContent();
				ArrayList<OntoParceiro> listAutores = new ArrayList<>();
				OntoParceiro ontoOrientador = new OntoParceiro(nomeOrientador, idOrientador);
				listAutores.add(ontoOrientador);
				OntoClass eve = new OntoClass(titulo, tipo, listAutores);
				listResult.add(eve);
			}
		}
		return listResult;
	}

	public ArrayList<OntoClass> BuscaBanca(String raiz, String tipo) throws XPathExpressionException {
		XPathExpression expr = this.xpath.compile(raiz);
		NodeList livros = (NodeList) expr.evaluate(this.xmlfile, XPathConstants.NODESET);
		ArrayList<OntoClass> ListArtigoCompleto = new ArrayList<>();
		for (int i = 0; i < livros.getLength(); i++) {
			Node TipoNode = livros.item(i);
			String titulo = TipoNode.getChildNodes().item(0).getAttributes().getNamedItem("TITULO").getTextContent();

			NodeList listAutores = TipoNode.getChildNodes();
			ArrayList<OntoParceiro> listParticipantes = new ArrayList<>();
			for (int j = 0; j < listAutores.getLength(); j++) {
				Node autoresNode = listAutores.item(j);
				if (autoresNode.getNodeName().contentEquals("PARTICIPANTE-BANCA")) {
					String nome = autoresNode.getAttributes().getNamedItem("NOME-COMPLETO-DO-PARTICIPANTE-DA-BANCA")
							.getTextContent();
					String citacao = autoresNode.getAttributes()
							.getNamedItem("NOME-PARA-CITACAO-DO-PARTICIPANTE-DA-BANCA").getTextContent();
					String id = autoresNode.getAttributes().getNamedItem("NRO-ID-CNPQ").getTextContent();
					OntoParceiro ontopar = new OntoParceiro(nome, citacao, id);
					listParticipantes.add(ontopar);
				}
			}
			OntoClass itemBanca = new OntoClass(titulo, tipo, listParticipantes);
			ListArtigoCompleto.add(itemBanca);
		}
		return ListArtigoCompleto;
	}

	public ArrayList<OntoClass> listOntoProjetoPesquisa() throws XPathExpressionException {
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
					ArrayList<OntoParceiro> listAutores = new ArrayList<>();
					for (int j = 0; j < auxlist.getLength(); j++) {
						Node aux = auxlist.item(j);
						if (aux.getNodeName().contentEquals("EQUIPE-DO-PROJETO")) {
							NodeList NodelistAutoresProjeto = aux.getChildNodes();
							for (int t = 0; t < NodelistAutoresProjeto.getLength(); t++) {
								Node Autores = NodelistAutoresProjeto.item(t);
								if (Autores != null) {
									String id = Autores.getAttributes().getNamedItem("NRO-ID-CNPQ").getTextContent();
									String citacao = Autores.getAttributes().getNamedItem("NOME-PARA-CITACAO")
											.getTextContent().replaceAll(" ", "_");
									String nome = Autores.getAttributes().getNamedItem("NOME-COMPLETO").getTextContent()
											.replaceAll(" ", "_");
									listAutores.add(new OntoParceiro(nome, citacao, id));
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
