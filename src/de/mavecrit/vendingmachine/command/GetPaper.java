package de.mavecrit.vendingmachine.command;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mavecrit.vendingmachine.items.Dollar;


public class GetPaper implements CommandExecutor {
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
	
		if (cmd.getName().equalsIgnoreCase("getdollar")) {
			if(player.hasPermission("vendingmachine.dollar")){
				player.getInventory().addItem(Dollar.OneDollar());
				player.sendMessage("§a+1 Dollar");
			}
		}
		return true;
		
	}
}
