package it.polito.tdp.corsi.db;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Statistiche;

public class CorsoDAO {
	
	/**
	 * ritorna tutti gli elementi della tabella CORSO
	 * @return
	 */
	public List<Corso> listAll() {
		List<Corso> result = new ArrayList<>();
		String sql ="select codins, crediti, nome, pd  " + 
				"from corso";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				Corso c = new Corso(res.getString("codIns"), 
						res.getInt("crediti"),
						res.getString("nome"), 
						res.getInt("pd"));
				result.add(c);
			}
			
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
/**
 * ritorna i corsi della tabella CORSO che sono tenuti nel periodo didattico passato
 * @param periodoDidattico
 * @return
 */
	public List<Corso> listByPeriodoDidattico(int periodoDidattico) {
		
		List<Corso> result = new ArrayList<>();
		String sql ="select codins, crediti, nome, pd  " + 
				"from corso "+ "where pd = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodoDidattico);
			
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				result.add(new Corso(res.getString("codins"),
						res.getInt("crediti"),
						res.getString("nome"),
						res.getInt("pd")));
			}
			
			conn.close();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
		return result;
	}
public Statistiche getStatisticheByCodIns(String codins) {
	String sql = "select cds, count(cds) as count from studente as s, iscrizione as i where s.matricola = i.matricola and i.codins = ? and cds<> \"\" group by cds";
	
	Statistiche stat = new Statistiche();
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, codins);
		
		ResultSet res = st.executeQuery();
		
		while(res.next()) {
			stat.getMappaCDS().put(res.getString("CDS"), res.getInt("count"));
		}
		
		conn.close();
		
	}catch(SQLException sqle) {
		throw new RuntimeException(sqle);
	}
	
	return stat;
}
}
