package br.com.OntologyTeste;

import java.io.File;
import java.util.ArrayList;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.FunctionalSyntaxDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.ReasonerProgressMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import org.w3c.dom.Document;

import br.com.Ontology.BuscarXmlToPessoa;
import br.com.Ontology.OntologyDAO;
import br.com.Ontology.modelo.OntoClass;
import br.com.Ontology.modelo.OntoPessoa;
import br.com.converter.ConverterFile;
import br.com.converter.TratamentoDeDados;

public class ReasonTeste {

	@Test
	public void TesteProjetoPesquisa() throws Exception {
		// FuncTesteProjetoPesquisa();
	}

	@Test
	public void TesteBanca() throws Exception {
		// FuncTesteBanca();
	}

	@Test
	public void TesteOrientacao() throws Exception {
		// FuncTesteOrientacao();
	}

	// gargalo
	@Test
	public void TestetrabalhoEvento() throws Exception {
		// FuncTestetrabalhoEvento();
	}

	@Test
	public void TesteEvento() throws Exception {
		FuncTesteEvento();
	}

	@Test
	public void TesteCompleto() throws Exception {
		// FuncTesteCompleto();
	}

	@Test
	public void TestesalvarInferenciaTeste() throws Exception {
		// salvarInferenciaTeste();
	}

	@Test
	public void TesteLeitura() throws Exception {
		// Leitura();
	}

	public void Leitura() throws Exception {
		OWLOntologyManager manager;
		OWLOntology ontology;
		IRI DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");
		String nomeFile = "Completo.owl";
		File owlfile = new File(System.getProperty("user.dir") + "/" + nomeFile);
		manager = OWLManager.createOWLOntologyManager();
		ontology = manager.loadOntologyFromOntologyDocument(owlfile);
		OWLDataFactory factory = manager.getOWLDataFactory();
		ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get().getIRI()
						.getFragment().contains("Pessoa"))
				.forEach(w -> {
					System.out.println("@@@@" + w.getIRI());
					ontology.objectPropertyAssertionAxioms(w).forEach(p -> {
						if (p.toString().startsWith(
								"ObjectPropertyAssertion(<http://www.datalattes.com/ontologies/datalattes.owl#relacao"))
							System.out.println("YYY" + p.toString());
					});
				});
	}

	public ArrayList<String> ListaDeArquivos(int tam) {
		// Tam max 44
		ArrayList<String> Namexml = new ArrayList<>();
		ArrayList<String> result = new ArrayList<>();
		Namexml.add("Alessandreiacurriculo.xml");
		Namexml.add("Alexcurriculo.xml");
		Namexml.add("AndreLuizcurriculo.xml");
		Namexml.add("BernardoMartinscurriculo.xml");
		Namexml.add("Carloscurriculo.xml");
		Namexml.add("Cirocurriculo.xml");
		Namexml.add("Edelbertocurriculo.xml");
		Namexml.add("EdmarOliveiracurriculo.xml");
		Namexml.add("EduardoBarrelecurriculo.xml");
		Namexml.add("EduardoPaganicurriculo.xml");
		Namexml.add("FabricioMartinscurriculo.xml");
		Namexml.add("Fernandacurriculo.xml");
		Namexml.add("Gleiphcurriculo.xml");
		Namexml.add("Hedercurriculo.xml");
		Namexml.add("Heliocurriculo.xml");
		Namexml.add("IgorKnopcurriculo.xml");
		Namexml.add("Itamarcurriculo.xml");
		Namexml.add("IuryHigorcurriculo.xml");
		Namexml.add("Jairocurriculo.xml");
		Namexml.add("JoseMariacurriculo.xml");
		Namexml.add("LeonardoVieiracurriculo.xml");
		Namexml.add("Liamaracurriculo.xml");
		Namexml.add("Lorenzacurriculo.xml");
		Namexml.add("LucianaBrugiolocurriculo.xml");
		Namexml.add("LucianaCamposcurriculo.xml");
		Namexml.add("LucianoJerezcurriculo.xml");
		Namexml.add("LuizFelipecurriculo.xml");
		Namexml.add("MarceloBernardescurriculo.xml");
		Namexml.add("MarceloCaniatocurriculo.xml");
		Namexml.add("MarceloLoboscocurriculo.xml");
		Namexml.add("MarceloMorenocurriculo.xml");
		Namexml.add("MarcoAntoniocurriculo.xml");
		Namexml.add("MarcosPassinicurriculo.xml");
		Namexml.add("MarioAntoniocurriculo.xml");
		Namexml.add("PriscilaCaprilescurriculo.xml");
		Namexml.add("RafaelAlvescurriculo.xml");
		Namexml.add("RaulFonsecacurriculo.xml");
		Namexml.add("ReginaBragacurriculo.xml");
		Namexml.add("RodrigoLuiscurriculo.xml");
		Namexml.add("RodrigoWebercurriculo.xml");
		Namexml.add("SauloMoraescurriculo.xml");
		Namexml.add("Steniocurriculo.xml");
		Namexml.add("Vâniacurriculo.xml");
		Namexml.add("VictorStroelecurriculo.xml");
		Namexml.add("Wagnercurriculo.xml");

		for (int i = 0; i < tam; i++)
			result.add(Namexml.get(i));
		return result;
	}

	public void salvarInferenciaTeste() throws Exception {
		System.out.println("InserirInferencia");
		OWLOntologyManager manager;
		OWLOntology ontology;
		IRI DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");
		String nomeFile = "InserirInferencia.owl";
		OntologyDAO ontoDao = new OntologyDAO(nomeFile);
		TratamentoDeDados tratamentoDeDados = new TratamentoDeDados();
		// Primeira pessoa
		OntoPessoa pessoa = new OntoPessoa(tratamentoDeDados.corrigirString("Nome1"),
				tratamentoDeDados.corrigirString("8888"), tratamentoDeDados.corrigirString("12/05/2001"));
		ArrayList<OntoClass> ListOntoEvento = new ArrayList<>();
		ListOntoEvento.add(new OntoClass("nomeEvento", "Evento", 2000));
		pessoa.setListOntoEvento(ListOntoEvento);
		ontoDao.preencherDadosGerais(pessoa);
		ontoDao.preencherEvento(pessoa);

		pessoa = new OntoPessoa(tratamentoDeDados.corrigirString("Nome2"), tratamentoDeDados.corrigirString("101010"),
				tratamentoDeDados.corrigirString("06/10/2001"));
		ListOntoEvento = new ArrayList<>();
		ListOntoEvento.add(new OntoClass("nomeEvento", "Evento", 2000));
		pessoa.setListOntoEvento(ListOntoEvento);
		ontoDao.preencherDadosGerais(pessoa);
		ontoDao.preencherEvento(pessoa);
		ontoDao.saveOntologyDAO(new FunctionalSyntaxDocumentFormat());
	}

	public void FuncTesteCompleto() throws Exception {
		long tempoInicio = System.currentTimeMillis();
		System.out.println("Completo");
		OWLOntologyManager manager;
		OWLOntology ontology;
		IRI DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");
		String nomeFile = "Completo.owl";
		OntologyDAO ontoDao = new OntologyDAO(nomeFile);
		TratamentoDeDados tratamentoDeDados = new TratamentoDeDados();
		ArrayList<String> Namexml = ListaDeArquivos(20);
		for (String string : Namexml) {
			File owlfile = new ClassPathResource("static/testFile/" + string).getFile();
			Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
			BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
			OntoPessoa pessoa = new OntoPessoa(tratamentoDeDados.corrigirString(preencherXMLtoOnto.NomeCompleto()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.IDLattes()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.UltimaAtualizacao()));
			preencherXMLtoOnto.buscarXML(pessoa);
			ontoDao.preencherOnto(pessoa);
			System.out.println(string);
		}

		ontoDao.saveOntologyDAO(new FunctionalSyntaxDocumentFormat());
		File owlfile = new File(System.getProperty("user.dir") + "/" + nomeFile);
		manager = OWLManager.createOWLOntologyManager();
		ontology = manager.loadOntologyFromOntologyDocument(owlfile);
		OWLDataFactory factory = manager.getOWLDataFactory();
		ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get().getIRI()
						.getFragment().contains("Pessoa"))
				.forEach(w -> {
					System.out.println("@@@@" + w.getIRI());
					ontology.objectPropertyAssertionAxioms(w).forEach(p -> {
						if (p.toString().startsWith(
								"ObjectPropertyAssertion(<http://www.datalattes.com/ontologies/datalattes.owl#relacao"))
							System.out.println("YYY" + p.toString());
					});
				});
		System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));
	}

	public void FuncTesteEvento() throws Exception {
		long tempoInicio = System.currentTimeMillis();
		System.out.println("Evento");
		OWLOntologyManager manager;
		OWLOntology ontology;
		IRI DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");
		String nomeFile = "Evento.owl";
		OntologyDAO ontoDao = new OntologyDAO(nomeFile);
		TratamentoDeDados tratamentoDeDados = new TratamentoDeDados();
		ArrayList<String> Namexml = ListaDeArquivos(0);
		for (String string : Namexml) {
			File owlfile = new ClassPathResource("static/testFile/" + string).getFile();
			Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
			BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
			OntoPessoa pessoa = new OntoPessoa(tratamentoDeDados.corrigirString(preencherXMLtoOnto.NomeCompleto()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.IDLattes()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.UltimaAtualizacao()));
			preencherXMLtoOnto.buscarXML(pessoa);
			ontoDao.preencherDadosGerais(pessoa);
			ontoDao.preencherEvento(pessoa);
			System.out.println(string);

		}
		ontoDao.saveOntologyDAO(new FunctionalSyntaxDocumentFormat());
		File owlfile = new File(System.getProperty("user.dir") + "/" + nomeFile);
		manager = OWLManager.createOWLOntologyManager();
		ontology = manager.loadOntologyFromOntologyDocument(owlfile);

		SQWRLQueryEngine queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(ontology);
		// SQWRLResult result = queryEngine.runSQWRLQuery("q1",
		// "Pessoa(?x) ^ NomeCompleto(?x,?nome) -> sqwrl:select(?nome)");
		//
		// while (result.next()) {
		// System.out.println("Name: " + result.getLiteral("nome").getString());
		// }

		OWLDataFactory factory = manager.getOWLDataFactory();
		ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get().getIRI()
						.getFragment().contains("Pessoa"))
				.forEach(w -> {
					System.out.println("@@@@" + w.getIRI());
					ontology.objectPropertyAssertionAxioms(w).forEach(p -> {
						if (p.toString().startsWith(
								"ObjectPropertyAssertion(<http://www.datalattes.com/ontologies/datalattes.owl#relacao"))
							System.out.println("YYY" + p.toString());
					});
				});
		System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));
	}

	// Gargalo
	public void FuncTestetrabalhoEvento() throws Exception {
		long tempoInicio = System.currentTimeMillis();
		System.out.println("TrabalhoEvento");
		OWLOntologyManager manager;
		OWLOntology ontology;
		IRI DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");
		String nomeFile = "TrabalhoEvento.owl";
		OntologyDAO ontoDao = new OntologyDAO(nomeFile);
		TratamentoDeDados tratamentoDeDados = new TratamentoDeDados();
		ArrayList<String> Namexml = ListaDeArquivos(20);
		for (String string : Namexml) {
			File owlfile = new ClassPathResource("static/testFile/" + string).getFile();
			Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
			BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
			OntoPessoa pessoa = new OntoPessoa(tratamentoDeDados.corrigirString(preencherXMLtoOnto.NomeCompleto()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.IDLattes()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.UltimaAtualizacao()));
			preencherXMLtoOnto.buscarXML(pessoa);
			ontoDao.preencherDadosGerais(pessoa);
			ontoDao.preencherEvento(pessoa);
			ontoDao.preencherTrabalhoEvento(pessoa);

			System.out.println(string);
		}
		ontoDao.saveOntologyDAO(new FunctionalSyntaxDocumentFormat());
		File owlfile = new File(System.getProperty("user.dir") + "/" + nomeFile);
		manager = OWLManager.createOWLOntologyManager();
		ontology = manager.loadOntologyFromOntologyDocument(owlfile);
		OWLDataFactory factory = manager.getOWLDataFactory();
		ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get().getIRI()
						.getFragment().contains("Pessoa"))
				.forEach(w -> {
					System.out.println("@@@@" + w.getIRI());
					ontology.objectPropertyAssertionAxioms(w).forEach(p -> {
						if (p.toString().startsWith(
								"ObjectPropertyAssertion(<http://www.datalattes.com/ontologies/datalattes.owl#relacao"))
							System.out.println("YYY" + p.toString());
					});
				});
		System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));
	}

	public void FuncTesteOrientacao() throws Exception {
		long tempoInicio = System.currentTimeMillis();
		System.out.println("Orientacao");
		OWLOntologyManager manager;
		OWLOntology ontology;
		IRI DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");
		String nomeFile = "Orientacao.owl";
		OntologyDAO ontoDao = new OntologyDAO(nomeFile);
		TratamentoDeDados tratamentoDeDados = new TratamentoDeDados();
		ArrayList<String> Namexml = ListaDeArquivos(44);
		for (String string : Namexml) {
			File owlfile = new ClassPathResource("static/testFile/" + string).getFile();
			Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
			BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
			OntoPessoa pessoa = new OntoPessoa(tratamentoDeDados.corrigirString(preencherXMLtoOnto.NomeCompleto()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.IDLattes()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.UltimaAtualizacao()));
			preencherXMLtoOnto.buscarXML(pessoa);
			ontoDao.preencherDadosGerais(pessoa);
			ontoDao.preencherFormacao(pessoa);

		}
		ontoDao.saveOntologyDAO(new FunctionalSyntaxDocumentFormat());
		File owlfile = new File(System.getProperty("user.dir") + "/" + nomeFile);
		manager = OWLManager.createOWLOntologyManager();
		ontology = manager.loadOntologyFromOntologyDocument(owlfile);
		OWLDataFactory factory = manager.getOWLDataFactory();
		ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get().getIRI()
						.getFragment().contains("Pessoa"))
				.forEach(w -> {
					System.out.println("@@@@" + w.getIRI());
					ontology.objectPropertyAssertionAxioms(w).forEach(p -> {
						if (p.toString().startsWith(
								"ObjectPropertyAssertion(<http://www.datalattes.com/ontologies/datalattes.owl#relacao"))
							System.out.println("YYY" + p.toString());
					});
				});
		System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));
	}

	public void FuncTesteBanca() throws Exception {
		long tempoInicio = System.currentTimeMillis();
		System.out.println("Banca");
		OWLOntologyManager manager;
		OWLOntology ontology;
		IRI DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");
		String nomeFile = "Banca.owl";
		OntologyDAO ontoDao = new OntologyDAO(nomeFile);
		TratamentoDeDados tratamentoDeDados = new TratamentoDeDados();
		ArrayList<String> Namexml = ListaDeArquivos(44);
		for (String string : Namexml) {
			File owlfile = new ClassPathResource("static/testFile/" + string).getFile();
			Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
			BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
			OntoPessoa pessoa = new OntoPessoa(tratamentoDeDados.corrigirString(preencherXMLtoOnto.NomeCompleto()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.IDLattes()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.UltimaAtualizacao()));
			preencherXMLtoOnto.buscarXML(pessoa);
			ontoDao.preencherDadosGerais(pessoa);
			ontoDao.preencherBanca(pessoa);
			System.out.println(string);

		}
		ontoDao.saveOntologyDAO(new FunctionalSyntaxDocumentFormat());
		File owlfile = new File(System.getProperty("user.dir") + "/" + nomeFile);
		manager = OWLManager.createOWLOntologyManager();
		ontology = manager.loadOntologyFromOntologyDocument(owlfile);
		OWLDataFactory factory = manager.getOWLDataFactory();
		ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get().getIRI()
						.getFragment().contains("Pessoa"))
				.forEach(w -> {
					System.out.println("@@@@" + w.getIRI());
					ontology.objectPropertyAssertionAxioms(w).forEach(p -> {
						if (p.toString().startsWith(
								"ObjectPropertyAssertion(<http://www.datalattes.com/ontologies/datalattes.owl#relacao"))
							System.out.println("YYY" + p.toString());
					});
				});
		System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));
	}

	public void FuncTesteProjetoPesquisa() throws Exception {
		long tempoInicio = System.currentTimeMillis();
		System.out.println("ProjetoPesuqisa");
		OWLOntologyManager manager;
		OWLOntology ontology;
		IRI DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");
		String nomeFile = "ProjetoPesquisa.owl";
		OntologyDAO ontoDao = new OntologyDAO(nomeFile);
		TratamentoDeDados tratamentoDeDados = new TratamentoDeDados();
		ArrayList<String> Namexml = ListaDeArquivos(44);
		for (String string : Namexml) {
			File owlfile = new ClassPathResource("static/testFile/" + string).getFile();
			Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
			BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
			OntoPessoa pessoa = new OntoPessoa(tratamentoDeDados.corrigirString(preencherXMLtoOnto.NomeCompleto()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.IDLattes()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.UltimaAtualizacao()));
			preencherXMLtoOnto.buscarXML(pessoa);
			ontoDao.preencherDadosGerais(pessoa);
			ontoDao.preencherProjetoPesquisa(pessoa);

		}
		ontoDao.saveOntologyDAO(new FunctionalSyntaxDocumentFormat());
		File owlfile = new File(System.getProperty("user.dir") + "/" + nomeFile);
		manager = OWLManager.createOWLOntologyManager();
		ontology = manager.loadOntologyFromOntologyDocument(owlfile);
		OWLDataFactory factory = manager.getOWLDataFactory();
		ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get().getIRI()
						.getFragment().contains("Pessoa"))
				.forEach(w -> {
					System.out.println("@@@@" + w.getIRI());
					ontology.objectPropertyAssertionAxioms(w).forEach(p -> {
						if (p.toString().startsWith(
								"ObjectPropertyAssertion(<http://www.datalattes.com/ontologies/datalattes.owl#relacao"))
							System.out.println("YYY" + p.toString());
					});
				});
		System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));
	}

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
