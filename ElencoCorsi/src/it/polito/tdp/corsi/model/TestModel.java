package it.polito.tdp.corsi.model;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model mod = new Model();
		
		System.out.println("Lista corsi secondo periodo didattico: ");
		for(Corso c: mod.listaCorsiSemestre(2))
			System.out.println(c.toString());
		
		int matricola = 146101;
		String result = mod.getNomeCognomeByMatricola(matricola);
		System.out.println("matricola: " + matricola + " nome e cognome studente: "+result);
		
		String resultStatistiche;
		resultStatistiche = mod.getStatisticheFromCorsi();
		System.out.println("Statistiche: \n"+resultStatistiche);
	}

}
