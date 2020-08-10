package me.com.splash.elos.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import me.com.splash.elos.EloManager;
import me.com.splash.elos.Elos;
import me.com.splash.elos.db.PlayerDB;

public class TagListener implements Listener{
	
	@EventHandler
	public void tag(ChatMessageEvent e)  {
		Player p = e.getSender();
		if(e.getTags().contains("elos")) {
			e.setTagValue("elos", PlayerDB.users.get(p.getName()).getElo().getTag());
		}
	}

}
