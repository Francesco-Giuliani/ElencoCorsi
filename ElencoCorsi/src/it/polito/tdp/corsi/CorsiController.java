package it.polito.tdp.corsi;

import it.polito.tdp.corsi.model.*;
import java.util.*;
import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CorsiController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtSemestre;

    @FXML
    private Button btnCerca;

    @FXML
    private TextArea txtLog;

    @FXML
    void handleCerca(ActionEvent event) {
    	txtLog.clear();
    	try {
    		int pd = Integer.parseInt(this.txtSemestre.getText());
    		if(pd != 1 && pd != 2)
    			throw new NumberFormatException();
    		
	    	List<Corso> risultato = new ArrayList<>(model.listaCorsiSemestre(pd));
	    	for(Corso c : risultato)
	    		this.txtLog.appendText(c.toString()+"\n");
	    	
    	}catch(Exception nfe) {
    		nfe.printStackTrace();
    		this.txtLog.appendText("Inserisci un numero per il periodo didattico: 1 o 2");
    	}
    }

    @FXML
    void initialize() {
        assert txtSemestre != null : "fx:id=\"txtSemestre\" was not injected: check your FXML file 'Corsi.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Corsi.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'Corsi.fxml'.";

    }

	public void setModel(Model mod) {
		this.model = mod;
	}
}

