package de.mavecrit.vendingmachine.movement;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class Snippet implements Listener {

	private Vector push = new Vector(0, 0, 0);
	public static List<Player> Drunk = new ArrayList<Player>();
	
	@EventHandler
	public void move(PlayerMoveEvent event) {

		Player player = event.getPlayer();
		Entity entity = (Entity) player;
	
		if(Drunk.contains(player)){
		if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getZ() != event.getTo().getZ()) {
		
			
			if (entity.isOnGround()) {
			
					
				push.setX((Math.random() - 0.5) / 2.0);
				push.setZ((Math.random() - 0.5) / 2.0);
	
					player.setVelocity(push);
				} 
		}
	  }
	}
}

