package me.com.splash.elos.db;

import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import me.com.splash.elos.EloManager;
import me.com.splash.elos.Elos;

public class Manager {
	
	private EloManager user;
    private String name;

    public Manager(EloManager user) {
        this.user = user;
    }

    public Manager(String name) {
        this.name = name;
    }

    public void set() {
        Connection connection;
        PreparedStatement stmt = null;
        connection = new MySQL().pegaConexao();
        try {
            stmt = connection.prepareStatement("INSERT INTO player_ranks VALUES((?),(?))");
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getElo().getQuantKils());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL.fechaConexao(connection, stmt);
        }
    }

    public void update(EloManager user) {
        Connection connection;
        PreparedStatement stmt = null;
        connection = new MySQL().pegaConexao();
        try {
            stmt = connection.prepareStatement("UPDATE player_ranks SET elo_id = (?) WHERE name = (?)");
            stmt.setInt(1, user.getElo().getQuantKils());
            stmt.setString(2, user.getName());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(EloManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL.fechaConexao(connection, stmt);
        }
    }

    public EloManager getUser() {
        Connection connection;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        connection = new MySQL().pegaConexao();
        try {
            stmt = connection.prepareStatement("SELECT * FROM player_ranks WHERE name = (?)");
            stmt.setString(1, user.getName());
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new EloManager().setName(rs.getString("name")).setRank(Elos.values()[rs.getInt("elo_id")]);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL.fechaConexao(connection, stmt, rs);
        }
        return user;
    }

}
