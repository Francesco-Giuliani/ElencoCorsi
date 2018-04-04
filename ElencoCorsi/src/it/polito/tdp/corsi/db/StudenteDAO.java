package it.polito.tdp.corsi.db;

import java.util.*;
import it.polito.tdp.corsi.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudenteDAO {

	
	
	
	
	public List<Studente> listAll() {
		List<Studente> result = new ArrayList<>();
		String sql ="select matricola, cognome, nome, CDS " + 
				"from studente";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				Studente s = new Studente(res.getInt("matricola"), 
						res.getString("cognome"),
						res.getString("nome"), 
						res.getString("CDS"));
				result.add(s);
			}
			
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public Studente getStudenteByMatricola(int matricola) {
		Studente result = null;
		String sql ="select matricola, cognome, nome, CDS " + 
				"from studente where matricola = ? ";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet res = st.executeQuery();
			
			if(res.next()) {
				Studente s = new Studente(res.getInt("matricola"), 
						res.getString("cognome"),
						res.getString("nome"), 
						res.getString("CDS"));
				result = s;
			}
			
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
}

