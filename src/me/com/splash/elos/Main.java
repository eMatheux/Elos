package me.com.splash.elos;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.com.splash.elos.commands.EloCommand;
import me.com.splash.elos.db.MySQL;
import me.com.splash.elos.db.PlayerDB;
import me.com.splash.elos.listeners.MainEvent;
import me.com.splash.elos.listeners.TagListener;
import me.com.splash.elos.utils.ConfigApi;

public class Main extends JavaPlugin {
	
	private static ConfigApi config;
	private static Main instance;
	//MYSQL
	public static String Host;
	public static String Senha;
	public static String Banco;
	public static String Usuario;
	public static String Port;
	
	@Override
	public void onEnable() {
		config = new ConfigApi("config.yml" , this);
		config.saveConfig();
		startDB();
		new PlayerDB().loadFromSQL();
		getCommand("elos").setExecutor(new EloCommand());
		Bukkit.getPluginManager().registerEvents(new MainEvent(), this);
		Bukkit.getPluginManager().registerEvents(new TagListener(), this);
	}
	@Override
	public void onDisable() {
	}
	
	public static Main getI() {
		return instance;
	}
	
	public static void severe(String msg) {
		Main.getI().getLogger().severe(msg);
	}
	public void startDB() {
		Host = config.getString("MySQL.Host");
		Senha = config.getString("MySQL.Senha");
		Banco = config.getString("MySQL.Banco");
		Usuario = config.getString("MySQL.Usuario");
		Port = config.getString("MySQL.Port");
		new MySQL().initBanco();
	}

}
