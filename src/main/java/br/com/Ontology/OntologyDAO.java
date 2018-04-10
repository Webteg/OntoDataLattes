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

	private ReadFile read;
	private File file;
	private OWLOntologyManager manager;
	private OWLOntology ontology;
	private IRI DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");

	public OntologyDAO() throws OWLOntologyCreationException {
		this.manager = OWLManager.createOWLOntologyManager();
		this.file = this.read.PegarFile();
		this.ontology = this.manager.loadOntologyFromOntologyDocument(this.file);
	}

	@PreDestroy
	public void saveOntologyDAO() throws OWLOntologyStorageException, FileNotFoundException {
		this.manager.saveOntology(this.ontology, new FunctionalSyntaxDocumentFormat(), new FileOutputStream(this.file));
	}

	public void imprimir() {
		//
		this.ontology.classesInSignature().forEach(cls -> System.out.println(cls.getIRI().getFragment()));
		// this.ontology.logicalAxioms().forEach(System.out::println);
		this.ontology.signature().forEach(System.out::println);
		//
		// this.ontology.signature().filter(u
		// −>
		// !u.isBuiltIn()&&u.getIRI().getRemainder().orElse("")).forEach(System.out::println);
	}

	public void preencherOnto(OntoPessoa pessoa) throws OWLOntologyStorageException, FileNotFoundException {
		String nome = pessoa.getIdLattes();

		addIndividual(nome, "Pessoa");
		addAtribNoIndivido(nome, pessoa.getIdLattes(), "IdLattes");
		addAtribNoIndivido(nome, pessoa.getNomeCompleto(), "NomeCompleto");
		addAtribNoIndivido(nome, pessoa.getData(), "DataAtualizacao");
		pessoa.getListOntoProjetoPesquisa().forEach(u -> {
			addIndividual(u.getTitulo(), u.getTipo());
			addAtribNoIndivido(u.getTitulo(), u.getTitulo(), "Titulo");
			addRelacaoInd(nome, u.getTitulo(), "TrabalhouEmProjeto");
			u.getListAutores().forEach(t -> {
				addIndividual(t, "Pessoa");
				addRelacaoInd(t, u.getTitulo(), "TrabalhouEmProjeto");
			});

		});
		pessoa.getListOntoEvento().forEach(u -> {
			addIndividual(u.getTitulo(), u.getTipo());
			addAtribNoIndivido(u.getTitulo(), u.getTitulo(), "Titulo");
			addRelacaoInd(nome, u.getTitulo(), "Participou");

		});
		pessoa.getListOntoFormacao().forEach(u -> {
			addIndividual(u.getTitulo(), u.getTipo());
			addAtribNoIndivido(u.getTitulo(), u.getTitulo(), "TituloTrabalhoFinal");
			addRelacaoInd(nome, u.getTitulo(), "Éformado");
			u.getListAutores().forEach(t -> {
				addIndividual(t, "Pessoa");
				addRelacaoInd(t, nome, "Orientou");
			});
		});

		saveOntologyDAO();
		// imprimir();
	}

	public void addIndividual(String Nome, String Tipo) {
		OWLDataFactory factory = this.manager.getOWLDataFactory();
		OWLIndividual nome = factory.getOWLNamedIndividual(this.DATALATTESIRI + "#", Nome);
		OWLClass personClass = factory.getOWLClass(this.DATALATTESIRI + "#", Tipo);
		OWLClassAssertionAxiom da = factory.getOWLClassAssertionAxiom(personClass, nome);
		this.ontology.add(da);
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
