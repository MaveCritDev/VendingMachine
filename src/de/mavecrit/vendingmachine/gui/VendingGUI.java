package de.mavecrit.vendingmachine.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;

import de.mavecrit.vendingmachine.Main;
import de.mavecrit.vendingmachine.items.Dollar;
import de.mavecrit.vendingmachine.util.ParticleEffect;


public class VendingGUI implements Listener {
	
	public static List<Player> inPlace = new ArrayList<Player>();
	public static String Input_Solut = null;
	public static String type = null;
	public static String UType = null;
	public static String holoText = null;
	
	public static void openGui(Player player) {
		Inventory games = Bukkit.getServer().createInventory(player, 27, ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("GUI.name")));
		for (int i = 0; i < 27; i++) {
			games.setItem(i, spacer3(player));
		}
		
		games.setItem(10, coke());
		
		games.setItem(12, RedBull());
		
		games.setItem(14, Beer());
		
		games.setItem(16, shisha());
		player.openInventory(games);
	}

	private static ItemStack spacer3(Player player) {

		ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
		ItemMeta itemMeta = itemStack.getItemMeta();

		itemMeta.setDisplayName("§d");

		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(0, "§c");

		itemMeta.setLore(lore1);

		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	private static ItemStack coke() {

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
	
	private static ItemStack shisha() {

		ItemStack itemStack = new ItemStack(Material.POTION, 1, (short) 8197);
		ItemMeta itemMeta = itemStack.getItemMeta();
		PotionMeta pm = (PotionMeta) itemMeta;
		pm.setDisplayName("§7Shisha to go");
		pm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		pm.setColor(Color.BLACK);
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(0, "§eRightclick to drink");
		lore1.add(1, "§6Leftclick for informations");
		pm.setLore(lore1);

		itemStack.setItemMeta(pm);
		return itemStack;
	}
	
	private static ItemStack RedBull() {

		ItemStack itemStack = new ItemStack(Material.POTION, 1, (short) 8197);
		ItemMeta itemMeta = itemStack.getItemMeta();
		PotionMeta pm = (PotionMeta) itemMeta;
		pm.setDisplayName("§9RedBull");
		pm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		pm.setColor(Color.BLUE);
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(0, "§eRightclick to drink");
		lore1.add(1, "§6Leftclick for informations");
		pm.setLore(lore1);

		itemStack.setItemMeta(pm);
		return itemStack;
	}
	
	
	private static ItemStack Beer() {

		ItemStack itemStack = new ItemStack(Material.POTION, 1, (short) 8197);
		ItemMeta itemMeta = itemStack.getItemMeta();
		PotionMeta pm = (PotionMeta) itemMeta;
		pm.setDisplayName("§6Beer");
		pm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		pm.setColor(Color.YELLOW);
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(0, "§eRightclick to drink");
		lore1.add(1, "§6Leftclick for informations");
		pm.setLore(lore1);

		itemStack.setItemMeta(pm);
		return itemStack;
	}


	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Inventory gui = event.getInventory();
		final Player p = (Player) event.getWhoClicked();
		if (gui.getName().equals(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("GUI.name")))) {

			if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cCoke")){
				
				if(Main.getPlugin().getConfig().getBoolean("Enable.Coke")){
					if(event.getClick().equals(ClickType.RIGHT)){
						p.getInventory().addItem(coke());
						ParticleEffect.SMOKE_LARGE.display(0.5F, 0.5F, 0.5F, 0.2f, 30,
								p.getLocation(), 5); 
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_BOTTLE_THROW, 1f, 1f);
					} else {
						p.sendMessage("§7Information: §cCoke will give you a little coffein boost for 30 seconds.");
						p.getInventory().addItem(Dollar.OneDollar());
					}
				} else {
					p.sendMessage("§cI dont think that the owner of this server wants you to do this.");
				}
			}
		
			if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Beer")){		
				if(Main.getPlugin().getConfig().getBoolean("Enable.Beer")){
					if(event.getClick().equals(ClickType.RIGHT)){
						p.getInventory().addItem(Beer());
						ParticleEffect.SMOKE_LARGE.display(0.5F, 0.5F, 0.5F, 0.2f, 30,
								p.getLocation(), 5); 
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_BOTTLE_THROW, 1f, 1f);
					} else {
						p.sendMessage("§7Information: §6Beer will make you drunk for 30 seconds.");
						p.getInventory().addItem(Dollar.OneDollar());
					}
				} else {
					p.sendMessage("§cI dont think that the owner of this server wants you to do this.");
				}
			}
			if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§9RedBull")){
				
				if(Main.getPlugin().getConfig().getBoolean("Enable.RedBull")){
					if(event.getClick().equals(ClickType.RIGHT)){
						p.getInventory().addItem(RedBull());
						ParticleEffect.SMOKE_LARGE.display(0.5F, 0.5F, 0.5F, 0.2f, 30,
								p.getLocation(), 5); 
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_BOTTLE_THROW, 1f, 1f);
					} else {
						p.sendMessage("§7Information: §9RedBull gives you flight strength for 20 seconds");
						p.getInventory().addItem(Dollar.OneDollar());
					}
				} else {
					p.sendMessage("§cI dont think that the owner of this server wants you to do this.");
				}
			}
			if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§7Shisha to go")){
				
				if(Main.getPlugin().getConfig().getBoolean("Enable.Shisha")){
					if(event.getClick().equals(ClickType.RIGHT)){
						p.getInventory().addItem(shisha());
						ParticleEffect.SMOKE_LARGE.display(0.5F, 0.5F, 0.5F, 0.2f, 30,
								p.getLocation(), 5); 
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_BOTTLE_THROW, 1f, 1f);
					} else {
						p.sendMessage("§7Information: §7Time to smoke so much shisha that you probably will turn blind.");
						p.getInventory().addItem(Dollar.OneDollar());
					}
				} else {
					p.sendMessage("§cI dont think that the owner of this server wants you to do this.");
				}
			}
			event.setCancelled(true);
			p.closeInventory();
			
		}
	}
	
}
