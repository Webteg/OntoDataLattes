package br.com.converter;

import java.util.ArrayList;

import org.semanticweb.owlapi.model.OWLOntology;

import br.com.Ontology.modelo.TriplaOwl;

public class TratamentoDeDados {

	public String corrigirString(String text) {
		return text.replaceAll(">", "").replaceAll("<", "").replaceAll("#", "").replaceAll("\t", "")
				.replaceAll("\n", " ").replaceAll(" ", "_").replaceAll("'", "").toLowerCase();
	}

	public static ArrayList<TriplaOwl> listaObjetosDesnecessarios(OWLOntology ontology) {
		ArrayList<TriplaOwl> listDelete = new ArrayList<>();
		ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get()
						.getIRI().getFragment().contains("Evento"))
				.forEach(w -> {
					if (ontology.objectPropertyAssertionAxioms(w).count() == 1) {
						TriplaOwl triplaOwl = new TriplaOwl(w.getIRI());
						listDelete.add(triplaOwl);
					}
				});
		return listDelete;
	}
	
	
}
