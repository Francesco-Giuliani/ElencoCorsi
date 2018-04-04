package it.polito.tdp.corsi.model;

import java.util.*;

import it.polito.tdp.corsi.db.*;

public class Model {

	private List<Corso> listaCorsi;
	private CorsoDAO corsoDAO; //non voglio creare un nuovo dao per ogni metodo
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	    this.listaCorsi = new ArrayList<>();
	}
	
	public List<Corso> listaCorsiSemestre(int periodoDidattico){
		//due modi per avere la lista di corsi nel periodo didattico
		//passato: (v. pattern DAO) quale scegliere dipende da vari fattori:
		//meno righe java scrivo meglio è: quindi più posso fare al DB meglio è
		
		//OPZIONE 1: leggo tutto e filtro io
		
//		this.listaCorsi=corsoDAO.listAll();
//		
//		List<Corso> risultato = new ArrayList<>();
//		for(Corso c : this.listaCorsi)
//			if(c.getPeriodoDidattico() == periodoDidattico)
//				risultato.add(c);
//		
//		return risultato;
//		
		//OPZIONE 2: faccio lavorare il DB
		List<Corso> risultato2 = new ArrayList<>( corsoDAO.listByPeriodoDidattico(periodoDidattico));
		return risultato2;
	}

	public String getNomeCognomeByMatricola(int matricola) {
		
		Studente studente = this.studenteDAO.getStudenteByMatricola(matricola);
		
		if(studente == null)
			return "Nessuno studente trovato per la matricola fornita.";
		
		return studente.getCognome()+" "+studente.getNome();
	}

	public String getStatisticheFromCorsi() {
		this.listaCorsi = this.corsoDAO.listAll();
		
		StringBuilder sb = new StringBuilder();
		for(Corso c: this.listaCorsi) {
			Statistiche stat = corsoDAO.getStatisticheByCodIns(c.getCodiceInsegnamento());
			sb.append("codins: "+c.getCodiceInsegnamento()+ " "+ stat.toString()+ "\n");
			for(String cds : stat.getMappaCDS().keySet())
				sb.append("-" + cds + " " + stat.getMappaCDS().get(cds)+"\n");
		}
		return sb.toString();
	}
}
