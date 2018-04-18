package br.com.OntologyTeste;

import java.io.File;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.ReasonerProgressMonitor;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class ReasonTeste {
	@Test
	public void DadosGerais() throws Exception {
		System.out.println("Funcional");
		long tempoInicio = System.currentTimeMillis();
		OWLOntologyManager manager;
		OWLOntology ontology;
		IRI DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");
		File owlfile = new ClassPathResource("static/OWL/datalattesSimples.owl").getFile();
		manager = OWLManager.createOWLOntologyManager();
		ontology = manager.loadOntologyFromOntologyDocument(owlfile);
		OWLDataFactory factory = manager.getOWLDataFactory();
		// ontology.classesInSignature().forEach(cls ->
		// System.out.println(cls.getIRI().getFragment()));
		// ontology.logicalAxioms().forEach(System.out::println);
		// ontology.objectPropertiesInSignature().forEach(System.out::println);
//		 OWLObjectProperty obj = factory.getOWLObjectProperty(DATALATTESIRI + "#",
//		 "orientou");
		// ontology.objectPropertiesInSignature().forEach(System.out::println);
		// ontology.classesInSignature().forEach(u ->
		// System.out.println(u.getIRI()));
		// ontology.objectPropertiesInSignature().forEach(u -> System.out.println(u.));
		
		// ontology.signature().forEach(System.out::println);
		// ontology.signature().filter(e ->
		// !e.isBuiltIn() && e.getIRI().getRemainder().orElse("").startsWith("r")
		// ).forEach(System.out::println);
		 OWLObjectProperty obj = factory.getOWLObjectProperty(DATALATTESIRI + "#",
				"relacaoBanca");
		Logger LOG = LoggerFactory.getLogger(ReasonTeste.class);
		ReasonerProgressMonitor progressMonitor = new LoggingReasonerProgressMonitor(LOG, "Loginference");
		OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
		OWLReasonerFactory rf = new ReasonerFactory();
		OWLReasoner r = rf.createReasoner(ontology, config);
		r.precomputeInferences(InferenceType.CLASS_HIERARCHY, InferenceType.CLASS_ASSERTIONS,

				InferenceType.OBJECT_PROPERTY_ASSERTIONS);
		// r.precomputeInferences(InferenceType.OBJECT_PROPERTY_ASSERTIONS);
		// r.precomputeInferences(InferenceType.OBJECT_PROPERTY_ASSERTIONS);
		r.getObjectPropertyDomains(obj).forEach(System.out::println);
		System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));
		
		// ontology.classesInSignature().forEach(u -> {
		// // System.out.println(u.getIRI());
		//
		// NodeSet<OWLClass> instances = r.getObjectPropertyDomains(obj);
		// // NodeSet<OWLNamedIndividual> instances = r.getInstances(u,true);
		// instances.entities().forEach(i -> {
		// // System.out.println(i.getIRI());
		// });
		// });

	}
	// @Test
	// public void DadosGeraisRDF() throws Exception {
	// System.out.println("RDF");
	// long tempoInicio = System.currentTimeMillis();
	// OWLOntologyManager manager;
	// OWLOntology ontology;
	// IRI DATALATTESIRI =
	// IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");
	// File owlfile = new
	// ClassPathResource("static/OWL/datalattesFullRDF.owl").getFile();
	// manager = OWLManager.createOWLOntologyManager();
	// ontology = manager.loadOntologyFromOntologyDocument(owlfile);
	// OWLDataFactory factory = manager.getOWLDataFactory();
	// // this.ontology.classesInSignature().forEach(cls ->
	// // System.out.println(cls.getIRI().getFragment()));
	// // ontology.logicalAxioms().forEach(System.out::println);
	// // ontology.objectPropertiesInSignature().forEach(System.out::println);
	// // OWLObjectProperty obj = factory.getOWLObjectProperty(DATALATTESIRI + "#",
	// // "orientou");
	//
	// // ontology.signature().forEach(System.out::println);
	// OWLReasonerFactory rf = new ReasonerFactory();
	// OWLReasoner r = rf.createReasoner(ontology);
	// r.precomputeInferences(InferenceType.CLASS_HIERARCHY);
	// System.out.println("Tempo Total: " + (System.currentTimeMillis() -
	// tempoInicio));
	// // ontology.classesInSignature().forEach(u -> {
	// // // System.out.println(u.getIRI());
	// //
	// // NodeSet<OWLClass> instances = r.getObjectPropertyDomains(obj);
	// // // NodeSet<OWLNamedIndividual> instances = r.getInstances(u,true);
	// // instances.entities().forEach(i -> {
	// // // System.out.println(i.getIRI());
	// // });
	// // });
	// }
	public static class LoggingReasonerProgressMonitor implements ReasonerProgressMonitor {

		private static final long serialVersionUID = 40000L;
		private static Logger logger;

		public LoggingReasonerProgressMonitor(Logger log) {
			logger = log;
		}

		public LoggingReasonerProgressMonitor(@Nonnull Logger log, String methodName) {
			String loggerName = log.getName() + '.' + methodName;
			logger = LoggerFactory.getLogger(loggerName);
		}

		@Override
		public void reasonerTaskStarted(String taskName) {
			logger.info("Reasoner Task Started: {}.", taskName);
		}

		@Override
		public void reasonerTaskStopped() {
			logger.info("Task stopped.");
		}

		@Override
		public void reasonerTaskProgressChanged(int value, int max) {
			logger.info("Reasoner Task made progress: {}/{}", value, max);
		}

		@Override
		public void reasonerTaskBusy() {
			logger.info("Reasoner Task is busy");
		}
	}
}
