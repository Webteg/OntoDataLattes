package br.com.Ontology;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.annotation.PreDestroy;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.FunctionalSyntaxDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLDocumentFormat;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.stereotype.Service;

import br.com.DAO.ReadFile;
import br.com.Ontology.modelo.OntoPessoa;

@Service
public class OntologyDAO {

	private File file;
	private OWLOntologyManager manager;
	private OWLOntology ontology;
	private IRI DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");

	public OntologyDAO() throws OWLOntologyCreationException {
		this.manager = OWLManager.createOWLOntologyManager();
		this.file = ReadFile.PegarFile();
		this.ontology = this.manager.loadOntologyFromOntologyDocument(this.file);
	}

	public OntologyDAO(String NomeArq) throws OWLOntologyCreationException {
		this.manager = OWLManager.createOWLOntologyManager();
		this.file = ReadFile.PegarFile(NomeArq);
		this.ontology = this.manager.loadOntologyFromOntologyDocument(this.file);
	}

	public File getFile() {
		return this.file;
	}

	@PreDestroy
	public void saveOntologyDAO() throws OWLOntologyStorageException, FileNotFoundException {
		diferentIndividual();
		this.manager.saveOntology(this.ontology, new FunctionalSyntaxDocumentFormat(), new FileOutputStream(this.file));
	}

	public void saveOntologyDAO(OWLDocumentFormat formato) throws OWLOntologyStorageException, FileNotFoundException {
		diferentIndividual();
		this.manager.saveOntology(this.ontology, formato, new FileOutputStream(this.file));
	}


	public void preencherOnto(OntoPessoa pessoa) throws OWLOntologyStorageException, FileNotFoundException {
		preencherDadosGerais(pessoa);
		preencherProjetoPesquisa(pessoa);
		preencherEvento(pessoa);
		preencherOrgEvento(pessoa);
		preencherFormacao(pessoa);
		preencherBanca(pessoa);
		preencherTrabalhoEvento(pessoa);
		saveOntologyDAO();
	}

	public void preencherDadosGerais(OntoPessoa pessoa) {
		String nome = pessoa.getIdLattes();
		// Add dados gerais
		addIndividual(nome, "Pessoa");
		addAtribNoIndivido(nome, pessoa.getIdLattes(), "IdLattes");
		addAtribNoIndivido(nome, pessoa.getNomeCompleto(), "NomeCompleto");
		addAtribNoIndivido(nome, pessoa.getData(), "DataAtualizacao");
	}

	public void preencherProjetoPesquisa(OntoPessoa pessoa) {
		pessoa.getListOntoProjetoPesquisa().forEach(u -> {
			addIndividual(u.getTitulo(), u.getTipo());
			addRelacaoInd(pessoa.getIdLattes(), u.getTitulo(), "TrabalhoEmProjetoPesquisa");
			u.getListAutores().forEach(t -> {
				addIndividual((t.getId() == "" || t.getId().isEmpty() || t.getId() == null) ? t.getNome() : t.getId(),
						"Pessoa");
				addRelacaoInd((t.getId() == "" || t.getId().isEmpty() || t.getId() == null) ? t.getNome() : t.getId(),
						u.getTitulo(), "TrabalhoEmProjetoPesquisa");
			});
		});
	}

	public void preencherEvento(OntoPessoa pessoa) {
		pessoa.getListOntoEvento().forEach(u -> {
			addIndividual(u.getTitulo(), u.getTipo());
			addRelacaoInd(pessoa.getIdLattes(), u.getTitulo(), "participouEvento");
		});
	}

	public void preencherOrgEvento(OntoPessoa pessoa) {
		pessoa.getListOntoOrgEvento().forEach(u -> {
			addIndividual(u.getTitulo(), u.getTipo());
			addRelacaoInd(pessoa.getIdLattes(), u.getTitulo(), "organizouEvento");
		});
	}
	public void preencherFormacao(OntoPessoa pessoa) {
		pessoa.getListOntoFormacao().forEach(u -> {
			addIndividual(u.getTitulo(), u.getTipo());
			addRelacaoInd(pessoa.getIdLattes(), u.getTitulo(), "eFormado");
			u.getListAutores().forEach(t -> {
				addIndividual((t.getId() == "" || t.getId().isEmpty() || t.getId() == null) ? t.getNome() : t.getId(),
						"Pessoa");
				addRelacaoInd((t.getId() == "" || t.getId().isEmpty() || t.getId() == null) ? t.getNome() : t.getId(),
						pessoa.getIdLattes(), "orientou");
			});
		});
	}

	public void preencherBanca(OntoPessoa pessoa) {
		pessoa.getListOntoBanca().forEach(u -> {
			addIndividual(u.getTitulo(), u.getTipo());
			addRelacaoInd(pessoa.getIdLattes(), u.getTitulo(), "participouDeBanca");
			u.getListAutores().forEach(t -> {
				addIndividual((t.getId() == "" || t.getId().isEmpty() || t.getId() == null) ? t.getNome() : t.getId(),
						"Pessoa");
				addRelacaoInd((t.getId() == "" || t.getId().isEmpty() || t.getId() == null) ? t.getNome() : t.getId(),
						u.getTitulo(), "participouDeBanca");
			});
		});
	}

	public void preencherTrabalhoEvento(OntoPessoa pessoa) {
		pessoa.getListOntoTrabalhoEvento().forEach(u -> {
			addIndividual(u.getTituloTrabalho(), "Producao");
			addIndividual(u.getEvento().getTitulo(), "Evento");
			addRelacaoInd(pessoa.getIdLattes(), u.getTituloTrabalho(), "apresentouTrabalhoEvento");
			addRelacaoInd(u.getTituloTrabalho(), u.getEvento().getTitulo(), "trabalhoEmEvento");
		});
	}

	public void diferentIndividual() {
		OWLDataFactory factory = this.manager.getOWLDataFactory();
		OWLDifferentIndividualsAxiom diffInd = factory
				.getOWLDifferentIndividualsAxiom(this.ontology.getIndividualsInSignature());
		this.ontology.add(diffInd);
	}


	public void addIndividual(String Nome, String Tipo) {
		OWLDataFactory factory = this.manager.getOWLDataFactory();
		OWLIndividual nome = factory.getOWLNamedIndividual(this.DATALATTESIRI + "#", Nome);
		OWLDifferentIndividualsAxiom diffInd = factory.getOWLDifferentIndividualsAxiom(nome);
		OWLClass personClass = factory.getOWLClass(this.DATALATTESIRI + "#", Tipo);
		OWLClassAssertionAxiom da = factory.getOWLClassAssertionAxiom(personClass, nome);
		this.ontology.add(da);
		this.ontology.add(diffInd);
	}

	public void addAtribNoIndivido(String Nome, String valor, String Tipo) {
		OWLDataFactory factory = this.manager.getOWLDataFactory();
		OWLIndividual individual = factory.getOWLNamedIndividual(this.DATALATTESIRI + "#", Nome);
		OWLDataProperty dataProp = factory.getOWLDataProperty(this.DATALATTESIRI + "#", Tipo);
		OWLDataPropertyAssertionAxiom da = factory.getOWLDataPropertyAssertionAxiom(dataProp, individual, valor);
		this.ontology.add(da);
	}

	public void addRelacaoInd(String NomePrimeiro, String NomeSegundo, String Relacao) {
		OWLDataFactory factory = this.manager.getOWLDataFactory();
		OWLIndividual individual = factory.getOWLNamedIndividual(this.DATALATTESIRI + "#", NomePrimeiro);
		OWLIndividual individual2 = factory.getOWLNamedIndividual(this.DATALATTESIRI + "#", NomeSegundo);
		OWLObjectProperty obj = factory.getOWLObjectProperty(this.DATALATTESIRI + "#", Relacao);
		OWLObjectPropertyAssertionAxiom da = factory.getOWLObjectPropertyAssertionAxiom(obj, individual, individual2);
		this.ontology.add(da);
	}

}
