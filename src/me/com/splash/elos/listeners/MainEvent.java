package me.com.splash.elos.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.com.splash.elos.EloManager;
import me.com.splash.elos.Elos;
import me.com.splash.elos.Main;
import me.com.splash.elos.db.Manager;
import me.com.splash.elos.db.PlayerDB;

public class MainEvent implements Listener{

	@EventHandler
	public void killEvent(PlayerDeathEvent e) {
		Player killer = e.getEntity().getKiller();
		
		EloManager user = PlayerDB.users.get(killer.getName());
		if(user.getElo().getQuantKils() > 0) {
			if(PlayerDB.users.get(killer.getName()).getElo().getKills() == killer.getStatistic(Statistic.PLAYER_KILLS)) {
				Bukkit.getPluginManager().callEvent(new EloUP(killer));
				killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 10L, 10L);
				killer.sendMessage("");
				killer.sendMessage("§cVocê evoluiu para a próxima liga, parabéns.");
				killer.sendMessage("");
			}
		}
		
	}
	@EventHandler
	public void join (PlayerJoinEvent e) {
		if(!PlayerDB.users.containsKey(e.getPlayer().getName())) {
			EloManager user = new EloManager().setName(e.getPlayer().getName()).setRank(Elos.values()[7]);
			PlayerDB.users.put(e.getPlayer().getName(), user);
			new Manager(user).set();
			e.getPlayer().setStatistic(Statistic.PLAYER_KILLS, 0);
		}
	}
	
	@EventHandler
	public void onRank(EloUP e) {
		EloManager p = e.getUser();
		PlayerDB.users.get(e.getPlayer().getName()).setRank(e.getNextElo());
	}
	
}
