package me.com.splash.elos.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.com.splash.elos.EloManager;
import me.com.splash.elos.Elos;
import me.com.splash.elos.db.PlayerDB;
import me.com.splash.elos.utils.ItemBuilder;
import me.com.splash.elos.utils.SkullBuilder;

public class EloCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("elos")) {
			open(p);
		}
		return false;
	}
	public void open(Player p ) {
		//Elos elo = EloManager.getElo(p);
		Inventory inv = Bukkit.createInventory(null, 6*9, "Elos do servidor");
		
		inv.setItem(4, new SkullBuilder().setOwner(p.getName()).setDisplayName("§7Informações de: §f" + p.getName()).setLore("§7Elo atual: " + PlayerDB.users.get(p.getName()).getElo().getViewer(),"§7Suas kills: §f" + p.getStatistic(Statistic.PLAYER_KILLS),"").build());
		p.openInventory(inv);
	}

}
