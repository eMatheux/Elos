package me.com.splash.elos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import me.com.splash.elos.EloManager;
import me.com.splash.elos.Elos;
import me.com.splash.elos.Main;

public class PlayerDB {
	
	public static HashMap<String, EloManager> users = new HashMap<>();
	
	public PlayerDB loadFromSQL() {
		
		Connection c;
		PreparedStatement stmt = null;
		ResultSet rs= null;
		c = new MySQL().pegaConexao();
		try {
			stmt = c.prepareStatement("SELECT * FROM player_ranks");
			rs =stmt.executeQuery();
			while(rs.next()) {
				users.put(rs.getString("name"), new EloManager().setName(rs.getString("name")).setRank(Elos.values()[rs.getInt("elo_id")]));
			}
		}catch(SQLException e) {
			Main.severe("Erro.");
		}finally {
			MySQL.fechaConexao(c , stmt, rs);
		}
		
		return this;
	}

}
