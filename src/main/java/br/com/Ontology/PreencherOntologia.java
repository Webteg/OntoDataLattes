package br.com.Ontology;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.xpath.XPathExpressionException;

import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import br.com.Ontology.modelo.OntoPessoa;
import br.com.converter.ConverterFile;

@Service
public class PreencherOntologia {
	@Autowired
	OntologyDAO ontologyDAO;
	String NomeCurriculo;
	Document xmlfile;

	public void inserirFile(File xmlfile) {
		this.xmlfile = ConverterFile.ConverterFileToDocument(xmlfile);
		try {
			inserirDadosGerais();
		} catch (XPathExpressionException | OWLOntologyStorageException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void inserirDadosGerais()
			throws XPathExpressionException, OWLOntologyStorageException, FileNotFoundException {
		PreencherXMLtoOnto preencherXMLtoOnto = new PreencherXMLtoOnto(this.xmlfile);
		OntoPessoa pessoa = new OntoPessoa(preencherXMLtoOnto.NomeCompleto().replaceAll(" ", "_"),
				preencherXMLtoOnto.IDLattes(), preencherXMLtoOnto.UltimaAtualizacao());
		preencherXMLtoOnto.buscarXML(pessoa);
		System.out.println(pessoa.toString());
		this.ontologyDAO.preencherOnto(pessoa);

		// this.ontologyDAO.addIndividual("WWW", "Pessoa");
		// this.ontologyDAO.addAtribNoIndivido(this.NomeCurriculo, searchXML.IDLattes(),
		// "IdLattes");
		// this.ontologyDAO.addAtribNoIndivido(this.NomeCurriculo,
		// searchXML.NomeCompleto(), "NomeCompleto");
		this.imprimir();
		// this.ontologyDAO.saveOntologyDAO();
	}

	public void imprimir() {
		this.ontologyDAO.imprimir();
	}

}
