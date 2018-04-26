package br.com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.Ontology.PreencherOntologia;

@Controller
public class AnaliseController {
	private String path = "views/";
	@Autowired
	private PreencherOntologia preencherOntologia;

	@RequestMapping("/")
	public String home() {
		return this.path + "index";
	}

	@RequestMapping("/index")
	public String index() {
		return this.path + "index";
	}

	@RequestMapping("/grafo")
	public String grafo(Model model) {
		// model.addAttribute("cy-style.json", arg1);
		// model.addAttribute("data.json", arg1);
		return this.path + "grafo";
	}

	@RequestMapping("/Onto")
	public String onto() {

		return this.path + "analise/uploadOnto";
	}

	@RequestMapping("/uploadOnto")
	public String uploadFileToOntology(@RequestParam("file") MultipartFile[] uploadingFiles) {
		try {

			for (MultipartFile uploadedFile : uploadingFiles) {
				System.out.println(uploadedFile.getOriginalFilename());
				File xmlfile = new File(uploadedFile.getOriginalFilename());

				xmlfile.createNewFile();

				FileOutputStream fos = new FileOutputStream(xmlfile);
				fos.write(uploadedFile.getBytes());
				fos.close();

				this.preencherOntologia.inserirFile(xmlfile);

				xmlfile.delete();
			}
			// this.preencherOntologia.imprimir();
			// File xmlfile = new File(file.getOriginalFilename());
			// File xmlfile = new
			// ClassPathResource("static/testFile/Jairocurriculo.xml").getFile();
			// xmlfile.createNewFile();
			// this.preencherOntologia.inserirFile(xmlfile);
			// xmlfile.delete();
			System.out.println("fim");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.path + "index";
	}

}
