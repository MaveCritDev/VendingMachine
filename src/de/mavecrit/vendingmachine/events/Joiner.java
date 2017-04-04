package de.mavecrit.vendingmachine.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import de.mavecrit.vendingmachine.Main;
import de.mavecrit.vendingmachine.gui.VendingGUI;
import de.mavecrit.vendingmachine.items.Dollar;

public class Joiner implements Listener {
	public List<Player> cooldown = new ArrayList<Player>();

	@EventHandler
	public void Join(PlayerJoinEvent e){
		Player p = e.getPlayer();
		 

		Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if(Main.getPlugin().getConfig().getBoolean("Enable.ForceResourcepack")){
					p.setResourcePack(Main.getPlugin().getConfig().getString("ForceResourcepack.Link"));
				}
			}
		},5L);
		
	}
}
