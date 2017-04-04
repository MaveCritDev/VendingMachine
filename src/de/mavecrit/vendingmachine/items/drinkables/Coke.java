package de.mavecrit.vendingmachine.items.drinkables;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public class Coke {
	
	
	public static ItemStack cokedrink() {

		ItemStack itemStack = new ItemStack(Material.POTION, 1, (short) 8197);
		ItemMeta itemMeta = itemStack.getItemMeta();
		PotionMeta pm = (PotionMeta) itemMeta;
		pm.setDisplayName("§cCoke");
		pm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		pm.setColor(Color.RED);
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(0, "§eRightclick to drink");
		lore1.add(1, "§6Leftclick for informations");
		pm.setLore(lore1);

		itemStack.setItemMeta(pm);
		return itemStack;
	}
	
	public static ItemStack cokedrinkM(int amount) {

		ItemStack itemStack = new ItemStack(Material.POTION, amount, (short) 8197);
		ItemMeta itemMeta = itemStack.getItemMeta();
		PotionMeta pm = (PotionMeta) itemMeta;
		pm.setDisplayName("§cCoke");
		pm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		pm.setColor(Color.RED);
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(0, "§eRightclick to drink");
		lore1.add(1, "§6Leftclick for informations");
		pm.setLore(lore1);

		itemStack.setItemMeta(pm);
		return itemStack;
	}
}
