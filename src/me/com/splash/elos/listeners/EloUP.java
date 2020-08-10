package me.com.splash.elos.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.com.splash.elos.EloManager;
import me.com.splash.elos.Elos;
import me.com.splash.elos.db.PlayerDB;

public class EloUP extends Event{
	
	private final Player p;
	
	public Elos getNextElo() {
		return PlayerDB.users.get(p.getName()).getElo().getQuantKils() > 0 ? Elos.values()[PlayerDB.users.get(p.getName()).getElo().getQuantKils() - 1] : PlayerDB.users.get(p.getName()).getElo();
	}
	
	public EloManager getUser() {
		return PlayerDB.users.get(p.getName());
	}
	public Player getPlayer() {
		return p;
	}
	public EloUP(Player p) {
		this.p =p;
	}
	
	private static final HandlerList handlers = new HandlerList();
	
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
