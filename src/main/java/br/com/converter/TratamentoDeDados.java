package br.com.converter;

import java.util.ArrayList;

import org.semanticweb.owlapi.model.OWLOntology;

import br.com.Ontology.modelo.OntoClass;
import br.com.Ontology.modelo.OntoPessoa;
import br.com.Ontology.modelo.TriplaOwl;

public class TratamentoDeDados {

	public String corrigirString(String text) {
		return text.replaceAll(">", "").replaceAll("<", "").replaceAll("#", "").replaceAll("\t", "")
				.replaceAll("-", "").replaceAll("&", "").replaceAll(":", "").replaceAll("\n", " ").replaceAll(" ", "_")
				.replaceAll("'", "").toLowerCase();
	}

	public static void EventoeTrabalho(OntoPessoa pessoa) {
		int antes = pessoa.getListOntoEvento().size();
		ArrayList<OntoClass> aux = new ArrayList<>();
		pessoa.getListOntoEvento().forEach(ev -> {
			pessoa.getListOntoTrabalhoEvento().forEach(tr -> {
				if(ev.getTitulo().contentEquals(tr.getEvento().getTitulo()))
					aux.add(ev);
			});
		});
		aux.forEach(ev -> pessoa.getListOntoEvento().remove(ev));
		
		int depois = pessoa.getListOntoEvento().size();
		// if (antes != depois)
		// System.out.println("antes: " + antes + " depois: " + depois);
	}

	public static ArrayList<TriplaOwl> listaEventoDesnecessario(OWLOntology ontology) {
		ArrayList<TriplaOwl> listDelete = new ArrayList<>();
		ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get().getIRI()
						.getFragment().contains("Evento"))
				.forEach(w -> {
					if (ontology.objectPropertyAssertionAxioms(w).count() == 1) {
						TriplaOwl triplaOwl = new TriplaOwl(w.getIRI());
						listDelete.add(triplaOwl);
					}
				});
		return listDelete;
	}

	public static ArrayList<TriplaOwl> listaPessoaDesnecessario(OWLOntology ontology) {
		ArrayList<TriplaOwl> listDelete = new ArrayList<>();
		ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get().getIRI()
						.getFragment().contains("Pessoa"))
				.forEach(w -> {
					if (ontology.objectPropertyAssertionAxioms(w).count() == 1) {
						TriplaOwl triplaOwl = new TriplaOwl(w.getIRI());
						listDelete.add(triplaOwl);
					}
				});
		return listDelete;
	}

}
