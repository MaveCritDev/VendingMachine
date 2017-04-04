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
import org.bukkit.inventory.ItemStack;

import de.mavecrit.vendingmachine.Main;
import de.mavecrit.vendingmachine.gui.VendingGUI;
import de.mavecrit.vendingmachine.items.Dollar;

public class Interacter implements Listener {
	public List<Player> cooldown = new ArrayList<Player>();

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Material wanted = Material.valueOf(Main.getPlugin().getConfig().getString("General.Vendingmachine.Block"));

		if(e.getClickedBlock() !=null)
		if (e.getClickedBlock().getType().equals(wanted)) {
			if (!cooldown.contains(e.getPlayer())) {
				cooldown.add(e.getPlayer());
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
					@Override
					public void run() {
						cooldown.remove(e.getPlayer());
					}
				}, 2L);
				if (p.getItemInHand().getType().equals(Material.PAPER)) {
					if (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Dollar.displayname")))) {

						for (ItemStack is : p.getInventory().all(Material.PAPER).values()) {
							if (Main.getPlugin().getConfig().getBoolean("General.BlockUnder.Active")) {
								Material wanted2 = Material.valueOf(Main.getPlugin().getConfig().getString("General.BlockUnder.Material"));
								if (e.getClickedBlock().getLocation().add(0, -1, 0).getBlock().getType().equals(wanted2)) {
									p.getInventory().remove(is);
									p.getInventory().addItem(Dollar.MultiDollar((is.getAmount() - 1)));
									VendingGUI.openGui(p);
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
								}
							} else {
								p.getInventory().remove(is);
								p.getInventory().addItem(Dollar.MultiDollar((is.getAmount() - 1)));
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
								VendingGUI.openGui(p);
							}
						}
					}
				}
			}
		}
	}
}
