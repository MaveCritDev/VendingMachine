package de.mavecrit.vendingmachine.items;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.mavecrit.vendingmachine.Main;


public class Dollar {
	
	
	public static ItemStack OneDollar() {

		ItemStack itemStack = new ItemStack(Material.PAPER);
		ItemMeta itemMeta = itemStack.getItemMeta();

		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
				Main.getPlugin().getConfig().getString("Dollar.displayname")));
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(ChatColor.translateAlternateColorCodes('&',
				Main.getPlugin().getConfig().getString("Dollar.lore")));
		itemMeta.setLore(lore1);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	public static ItemStack MultiDollar(int amount) {

		ItemStack itemStack = new ItemStack(Material.PAPER, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();

		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
				Main.getPlugin().getConfig().getString("Dollar.displayname")));
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(ChatColor.translateAlternateColorCodes('&',
				Main.getPlugin().getConfig().getString("Dollar.lore")));
		itemMeta.setLore(lore1);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
}
