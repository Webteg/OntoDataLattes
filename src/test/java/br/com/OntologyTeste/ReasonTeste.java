package br.com.OntologyTeste;

import java.io.File;

import org.junit.Test;
import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.springframework.core.io.ClassPathResource;

public class ReasonTeste {
	@Test
	public void DadosGerais() throws Exception {
		OWLOntologyManager manager;
		OWLOntology ontology;
		IRI DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");
		File owlfile = new ClassPathResource("static/OWL/datalattesFull.owl").getFile();
		manager = OWLManager.createOWLOntologyManager();
		ontology = manager.loadOntologyFromOntologyDocument(owlfile);
		OWLDataFactory factory = manager.getOWLDataFactory();
		// this.ontology.classesInSignature().forEach(cls ->
		// System.out.println(cls.getIRI().getFragment()));
		// ontology.logicalAxioms().forEach(System.out::println);
		// ontology.objectPropertiesInSignature().forEach(System.out::println);
		OWLObjectProperty obj = factory.getOWLObjectProperty(DATALATTESIRI + "#", "orientou");

		// ontology.signature().forEach(System.out::println);
		OWLReasonerFactory rf = new ReasonerFactory();
		OWLReasoner r = rf.createReasoner(ontology);
		r.precomputeInferences(InferenceType.CLASS_HIERARCHY);
		ontology.classesInSignature().forEach(u -> {
			NodeSet<OWLNamedIndividual> instances = r.getInstances(u, true);
			instances.entities().forEach(i -> {
				i.getIRI();
			});
		});

		System.out.println("pça");
	}
}
