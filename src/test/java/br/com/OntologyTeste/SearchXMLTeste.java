package br.com.OntologyTeste;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;

import br.com.Ontology.PreencherXMLtoOnto;
import br.com.Ontology.modelo.OntoClass;

public class SearchXMLTeste {
	private static Document xmlfile;
	private static PreencherXMLtoOnto searchXML;

	@Test
	public void Graduacao() throws Exception {
		File file = new ClassPathResource("static/testFile/Jairocurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new PreencherXMLtoOnto(xmlfile);

		ArrayList<OntoClass> listprod = searchXML.listOntoFormacao();
		assertEquals("Um_Aporte_de_Web_Mining_para_a_Web_Semântica", listprod.get(0).getTitulo());
		assertEquals("Graduacao", listprod.get(0).getTipo());
		assertEquals("Tarcísio de Souza Lima", listprod.get(0).getListAutores().get(0).getNome());
		assertEquals("", listprod.get(0).getListAutores().get(0).getId());
	}

	@Test
	public void Especializacao() throws Exception {
		File file = new ClassPathResource("static/testFile/Fernandacurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new PreencherXMLtoOnto(xmlfile);

		ArrayList<OntoClass> listprod = searchXML.listOntoFormacao();
		assertEquals("Aplicações_em_Engenharia_de_Software", listprod.get(1).getTitulo());
		assertEquals("Especializacao", listprod.get(1).getTipo());
		assertEquals("", listprod.get(1).getListAutores().get(0).getNome());
		assertEquals("", listprod.get(1).getListAutores().get(0).getId());
	}

	@Test
	public void Mestrado() throws Exception {
		File file = new ClassPathResource("static/testFile/EduardoPaganicurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new PreencherXMLtoOnto(xmlfile);

		ArrayList<OntoClass> listprod = searchXML.listOntoFormacao();
		// listprod.forEach(u -> System.out.println(u.toString()));
		assertEquals(
				"Uma_Arquitetura_de_Sistemas_de_Detecção_de_Intrusão_em_Redes_Ad_Hoc_Sem_Fio_usando_Esteganografia_e_Mecanismos_de_Reputação",
				listprod.get(2).getTitulo());
		assertEquals("MestradoAcademico", listprod.get(2).getTipo());
		assertEquals("Célio Vinicius Neves de Albuquerque", listprod.get(2).getListAutores().get(0).getNome());
		assertEquals("4641684220602580", listprod.get(2).getListAutores().get(0).getId());
	}

	@Test
	public void Doutorado() throws Exception {
		File file = new ClassPathResource("static/testFile/EduardoBarrelecurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new PreencherXMLtoOnto(xmlfile);

		ArrayList<OntoClass> listprod = searchXML.listOntoFormacao();
		// listprod.forEach(u -> System.out.println(u.toString()));
		assertEquals(
				"ANÁLISE_DE_INTERAÇÃO_NO_PROCESSO_DE_INSTANCIAÇÃO_DE_OBJETOS_3D", listprod.get(3).getTitulo());
		assertEquals("Doutorado", listprod.get(3).getTipo());
		assertEquals("Claudio Esperança", listprod.get(3).getListAutores().get(0).getNome());
		assertEquals("9237788190989316", listprod.get(3).getListAutores().get(0).getId());
	}

	@Test
	public void Congresso() throws Exception {
		File file = new ClassPathResource("static/testFile/Gleiphcurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new PreencherXMLtoOnto(xmlfile);

		ArrayList<OntoClass> listprod = searchXML.listOntoEvento();
		listprod.forEach(u -> System.out.println(u.toString()));
		assertEquals("CBSoft_-_Congresso_Brasileiro_de_Software:_Teoria_e_Prática", listprod.get(3).getTitulo());
		assertEquals("Congresso", listprod.get(3).getTipo());
		assertEquals(2015, listprod.get(3).getAno());
	}

}
