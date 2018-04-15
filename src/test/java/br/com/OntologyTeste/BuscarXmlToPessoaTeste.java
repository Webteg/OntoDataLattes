package br.com.OntologyTeste;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;

import br.com.Ontology.BuscarXmlToPessoa;
import br.com.Ontology.modelo.AreaConhecimento;
import br.com.Ontology.modelo.OntoClass;
import br.com.Ontology.modelo.TrabalhoEvento;

public class BuscarXmlToPessoaTeste {
	private static BuscarXmlToPessoa searchXML;

	@Test
	public void DadosGerais() throws Exception {
		File file = new ClassPathResource("static/testFile/FabricioMartinscurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new BuscarXmlToPessoa(xmlfile);

		assertEquals("Fabricio Martins Mendonça", searchXML.NomeCompleto());
		assertEquals("04/01/2018 ", searchXML.UltimaAtualizacao());
		assertEquals("7587726616949092", searchXML.IDLattes());
		assertEquals("MENDONÇA, F. M.", searchXML.NomeCitacao());
	}

	@Test
	public void Graduacao() throws Exception {
		File file = new ClassPathResource("static/testFile/Jairocurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new BuscarXmlToPessoa(xmlfile);
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
		searchXML = new BuscarXmlToPessoa(xmlfile);
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
		searchXML = new BuscarXmlToPessoa(xmlfile);
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
		searchXML = new BuscarXmlToPessoa(xmlfile);
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
		searchXML = new BuscarXmlToPessoa(xmlfile);
		ArrayList<OntoClass> listprod = searchXML.listOntoEvento();
		// listprod.forEach(u -> System.out.println(u.toString()));

		assertEquals("CBSoft_-_Congresso_Brasileiro_de_Software:_Teoria_e_Prática", listprod.get(3).getTitulo());
		assertEquals("Congresso", listprod.get(3).getTipo());
		assertEquals(2015, listprod.get(3).getAno());
	}

	@Test
	public void Seminario() throws Exception {
		File file = new ClassPathResource("static/testFile/Alessandreiacurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new BuscarXmlToPessoa(xmlfile);
		ArrayList<OntoClass> listprod = searchXML.listOntoEvento();
		// listprod.forEach(u -> System.out.println(u.toString()));

		assertEquals("VII_Seminário_de_Inciação_Científica", listprod.get(0).getTitulo());
		assertEquals("Seminario", listprod.get(0).getTipo());
		assertEquals(2005, listprod.get(0).getAno());
	}

	@Test
	public void Simposio() throws Exception {
		File file = new ClassPathResource("static/testFile/Wagnercurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new BuscarXmlToPessoa(xmlfile);
		ArrayList<OntoClass> listprod = searchXML.listOntoEvento();
		// listprod.forEach(u -> System.out.println(u.toString()));

		assertEquals("Seminários_da_Computação", listprod.get(14).getTitulo());
		assertEquals("Seminario", listprod.get(14).getTipo());
		assertEquals(2012, listprod.get(14).getAno());
	}

	@Test
	public void Encontro() throws Exception {
		File file = new ClassPathResource("static/testFile/Wagnercurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new BuscarXmlToPessoa(xmlfile);
		ArrayList<OntoClass> listprod = searchXML.listOntoEvento();
		// listprod.forEach(u -> System.out.println(u.toString()));

		assertEquals("IV_Semana_de_Informática", listprod.get(48).getTitulo());
		assertEquals("Encontro", listprod.get(48).getTipo());
		assertEquals(1998, listprod.get(48).getAno());
	}

	@Test
	public void ProjetoPesquisa() throws Exception {
		File file = new ClassPathResource("static/testFile/Carloscurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new BuscarXmlToPessoa(xmlfile);
		ArrayList<OntoClass> listprod = searchXML.listOntoProjetoPesquisa();
		// listprod.forEach(u -> System.out.println(u.toString()));

		assertEquals("Atual_Seleção_Genômica_em_Raças_Leiteiras_no_Brasil",
				listprod.get(1).getTitulo());
		assertEquals("ProjetoPesquisa", listprod.get(1).getTipo());
		assertEquals("3572434390881704", listprod.get(1).getListAutores().get(1).getId());
		assertEquals("FONSECA_NETO,_R.", listprod.get(1).getListAutores().get(1).getCitacao());
		assertEquals("Raul_Fonseca_Neto", listprod.get(1).getListAutores().get(1).getNome());
	}

	@Test
	public void AreaAtuacao() throws Exception {
		File file = new ClassPathResource("static/testFile/Carloscurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new BuscarXmlToPessoa(xmlfile);
		ArrayList<AreaConhecimento> listprod = searchXML.listOntoAreaAtuacao();
		// listprod.forEach(u -> System.out.println(u.toString()));

		assertEquals("Matemática", listprod.get(0).getAreaConhecimento());
		assertEquals("Matemática Aplicada", listprod.get(0).getSubAreaConhecimento());
		assertEquals("Análise Numérica", listprod.get(0).getNomeEspecialidade());
	}

	@Test
	public void BancaGraduacao() throws Exception {
		File file = new ClassPathResource("static/testFile/Edelbertocurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new BuscarXmlToPessoa(xmlfile);
		ArrayList<OntoClass> listprod = searchXML.listOntoBanca();
		// listprod.forEach(u -> System.out.println(u.toString()));

		assertEquals("CLOUD_COMPUTING_-_CONCEITOS_E_APLICAÇÕES", listprod.get(0).getTitulo());
		assertEquals("BancaGraduacao", listprod.get(0).getTipo());
		assertEquals("5960813120832073", listprod.get(0).getListAutores().get(0).getId());
		assertEquals("DEMBOGURSKI, B. J.", listprod.get(0).getListAutores().get(0).getCitacao());
		assertEquals("Bruno José Dembogurski", listprod.get(0).getListAutores().get(0).getNome());
	}

	@Test
	public void BancaMestrado() throws Exception {
		File file = new ClassPathResource("static/testFile/Edelbertocurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new BuscarXmlToPessoa(xmlfile);
		ArrayList<OntoClass> listprod = searchXML.listOntoBanca();
		// listprod.forEach(u -> System.out.println(u.toString()));

		assertEquals("Aumentando_a_resiliência_em_SDN_quando_o_Plano_de_Controle_se_encontra_sob_ataque",
				listprod.get(7).getTitulo());
		assertEquals("BancaMestrado", listprod.get(7).getTipo());
		assertEquals("", listprod.get(7).getListAutores().get(1).getId());
		assertEquals("VIEIRA, A. B.", listprod.get(7).getListAutores().get(1).getCitacao());
		assertEquals("Alex Borges Vieira", listprod.get(7).getListAutores().get(1).getNome());
	}

	@Test
	public void BancaDoutorado() throws Exception {
		File file = new ClassPathResource("static/testFile/Itamarcurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new BuscarXmlToPessoa(xmlfile);
		ArrayList<OntoClass> listprod = searchXML.listOntoBanca();
		// listprod.forEach(u -> System.out.println(u.toString()));

		assertEquals(
				"Aplicação_de_Programação_Genética_Gramatical_Multiobjetiva_no_Estudo_do_Efeito_de_Múltiplas_Infecções_e_Ambiente_no_Desenvolvimento_de_Atopia_e_Fenótipos_de_Asma",
				listprod.get(18).getTitulo());
		assertEquals("BancaDoutorado", listprod.get(18).getTipo());
		assertEquals("", listprod.get(18).getListAutores().get(0).getId());
		assertEquals("BARBOSA, H. J. C.", listprod.get(18).getListAutores().get(0).getCitacao());
		assertEquals("Helio José Corrêa Barbosa", listprod.get(18).getListAutores().get(0).getNome());
	}

	@Test
	public void BancaQualificacao() throws Exception {
		File file = new ClassPathResource("static/testFile/Jairocurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new BuscarXmlToPessoa(xmlfile);
		ArrayList<OntoClass> listprod = searchXML.listOntoBanca();
		// listprod.forEach(u -> System.out.println(u.toString()));

		assertEquals("Estratégia_computacional_para_avaliação_de_propriedades_mecânicas_de_concreto",
				listprod.get(9).getTitulo());
		assertEquals("BancaQualificacao", listprod.get(9).getTipo());
		assertEquals("", listprod.get(9).getListAutores().get(0).getId());
		assertEquals("FONSECA, L. G.", listprod.get(9).getListAutores().get(0).getCitacao());
		assertEquals("Leonardo Goliatt da Fonseca", listprod.get(9).getListAutores().get(0).getNome());
	}

	@Test
	public void BancaAperEspeci() throws Exception {
		File file = new ClassPathResource("static/testFile/JoseMariacurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new BuscarXmlToPessoa(xmlfile);
		ArrayList<OntoClass> listprod = searchXML.listOntoBanca();
		// listprod.forEach(u -> System.out.println(u.toString()));
		// System.out.println(listprod.size());

		assertEquals(
				"Avaliação_de_Propostas_para_a_Implementação_de_qualidade_de_serviços_na_internet:_uma_comparação_entre_o_modelo_ATM_da_internet_e_o_DIFFSERV",
				listprod.get(92).getTitulo());
		assertEquals("BancaAperEspe", listprod.get(92).getTipo());
		assertEquals("", listprod.get(92).getListAutores().get(0).getId());
		assertEquals("MICHELI, M. P.", listprod.get(92).getListAutores().get(0).getCitacao());
		assertEquals("Milena Pessoa Micheli", listprod.get(92).getListAutores().get(0).getNome());
	}

	@Test
	public void OrgEvento() throws Exception {
		File file = new ClassPathResource("static/testFile/JoseMariacurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new BuscarXmlToPessoa(xmlfile);
		ArrayList<OntoClass> listprod = searchXML.listOrganizacaoEvento();
		// listprod.forEach(u -> System.out.println(u.toString()));
		// System.out.println(listprod.size());

		assertEquals(
				"I_Seminário_de_Iniciação_Científica",
				listprod.get(0).getTitulo());
		assertEquals("OrganizacaoEvento", listprod.get(0).getTipo());
		assertEquals(2007, listprod.get(0).getAno());
	}

	@Test
	public void TrabalhoEmEvento() throws Exception {
		File file = new ClassPathResource("static/testFile/Gleiphcurriculo.xml").getFile();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmlfile = docBuilder.parse(file);
		searchXML = new BuscarXmlToPessoa(xmlfile);
		ArrayList<TrabalhoEvento> listprod = searchXML.listOntoTrabalhoEvento();
		listprod.forEach(u -> System.out.println(u.toString()));
		System.out.println(listprod.size());

		assertEquals(
				"Ouriço: Uma Abordagem para Manutenção da Consistência de Repositórios de Gerência de Configuração",
				listprod.get(0).getTituloTrabalho());
		assertEquals("Simpósio_Brasileiro_de_Qualidade_de_Software", listprod.get(0).getEvento().getTitulo());
		assertEquals("TrabalhoEmEvento", listprod.get(0).getEvento().getTipo());
		assertEquals(2012, listprod.get(0).getEvento().getAno());
	}

}
