package de.mavecrit.vendingmachine;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import de.mavecrit.vendingmachine.command.GetPaper;
import de.mavecrit.vendingmachine.events.InteractWithDrinkables;
import de.mavecrit.vendingmachine.events.Interacter;
import de.mavecrit.vendingmachine.events.Joiner;
import de.mavecrit.vendingmachine.gui.VendingGUI;
import de.mavecrit.vendingmachine.movement.Snippet;

public class Main extends JavaPlugin implements Listener{
	
	private static Main plugin;
	
	public void loadConfiguration() {
		plugin.getConfig().addDefault("Enable.Coke", true);
		plugin.getConfig().addDefault("Enable.RedBull", true);
		plugin.getConfig().addDefault("Enable.Beer", true);
		plugin.getConfig().addDefault("Enable.Shisha", true);
		plugin.getConfig().addDefault("Enable.ForceResourcepack", true);
		
		plugin.getConfig().addDefault("ForceResourcepack.Link", "http://flipco.eu/VendingMachine.zip");
		
		plugin.getConfig().addDefault("General.Vendingmachine.Block", "JUKEBOX");	
		plugin.getConfig().addDefault("General.BlockUnder.Active", true);
		plugin.getConfig().addDefault("General.BlockUnder.Material", "IRON_BLOCK");
		
		plugin.getConfig().addDefault("Dollar.displayname", "&21 Dollar");
		plugin.getConfig().addDefault("Dollar.lore", "Use this dollar at the vending machine");
		
		plugin.getConfig().addDefault("GUI.name", "&9VendingMachine");
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
	}
	
	public void onEnable(){
		plugin = this;
		
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new VendingGUI(), this);
		Bukkit.getPluginManager().registerEvents(new Interacter(), this);
		Bukkit.getPluginManager().registerEvents(new Snippet(), this);
		Bukkit.getPluginManager().registerEvents(new InteractWithDrinkables(), this);
		Bukkit.getPluginManager().registerEvents(new Joiner(), this);
	    Bukkit.getConsoleSender().sendMessage("§aVendingmachine is ENABLED!");
	    Bukkit.getConsoleSender().sendMessage("§dWant a custom plugin? Visit §cflipco.eu");
	    loadConfiguration();
	    getCommand("getdollar").setExecutor(new GetPaper());
	}
	
	public static Main getPlugin(){
		return plugin;
	}

}
