package de.mavecrit.vendingmachine.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import de.mavecrit.vendingmachine.Main;
import de.mavecrit.vendingmachine.items.Dollar;
import de.mavecrit.vendingmachine.items.drinkables.Beer;
import de.mavecrit.vendingmachine.items.drinkables.Coke;
import de.mavecrit.vendingmachine.items.drinkables.RedBull;
import de.mavecrit.vendingmachine.items.drinkables.shisha;
import de.mavecrit.vendingmachine.movement.Snippet;
import de.mavecrit.vendingmachine.util.ItemFactory;
import de.mavecrit.vendingmachine.util.ParticleEffect;

public class InteractWithDrinkables implements Listener {

	public List<Player> cooldown = new ArrayList<Player>();
	ArrayList<Item> items = new ArrayList<>();
	static Random r = new Random();
	public static List<Player> Smoking = new ArrayList<Player>();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(p.getItemInHand().getType().equals(Material.POTION))
		if(p.getItemInHand().getItemMeta().getDisplayName().equals("§6Beer")){
			p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1F, 1F);
			e.setCancelled(true);
			if(!Snippet.Drunk.contains(p)){
				for (ItemStack is : p.getInventory().all(Material.POTION).values()) {
					if(is.getItemMeta().getDisplayName().equals("§6Beer")){
					p.getInventory().remove(is);
					p.getInventory().addItem(Beer.BeerDrinkM((is.getAmount() - 1)));
					}
				}
				Snippet.Drunk.add(p);
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 30 * 20, 3));
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {				
					 
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {	
							      for(PotionEffect potion : p.getActivePotionEffects()){
							    	  p.removePotionEffect(potion.getType());
							      }
							      Snippet.Drunk.remove(p);
							  }
							}, 30 * 20);
					}
				}, 20 * 2);
			   } else {
				   p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1F, 1F);
				   
					Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
						@Override
						public void run() {
							for (int i = 0; i < 30; i++) {
								final Item ITEM = p.getWorld().dropItem(
										p.getLocation(),ItemFactory.create(Material.STAINED_CLAY,(byte) 13, UUID.randomUUID().toString(), null));
								ITEM.setPickupDelay(30000);
								ITEM.setVelocity(new Vector(r.nextDouble() - 0.5,r.nextDouble() / 2.0,r.nextDouble() - 0.5));
								items.add(ITEM);
							}
							Bukkit.getScheduler().runTaskLater(Main.getPlugin(),new Runnable() {
										@Override public void run() {
											for (Item i : items)
												i.remove();
										}
									}, 50);
							}
						}, 9);
					}
			}
		if(p.getItemInHand().getType().equals(Material.POTION))
		if(p.getItemInHand().getItemMeta().getDisplayName().equals("§cCoke")){
			p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1F, 1F);
			e.setCancelled(true);
			if(!cooldown.contains(p)){
				cooldown.add(p);
				for (ItemStack is : p.getInventory().all(Material.POTION).values()) {
					if(is.getItemMeta().getDisplayName().equals("§cCoke")){
					p.getInventory().remove(is);
					p.getInventory().addItem(Coke.cokedrinkM((is.getAmount()-1)));
					}
				}
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(),
						new Runnable() {
							@Override public void run() {
								cooldown.remove(p);
								p.setWalkSpeed(0.2f);
							}
						}, 30 * 20L);
				
				p.setWalkSpeed(0.5f);
			} else {
				   p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1F, 1F);
			}
		}
		if(p.getItemInHand().getType().equals(Material.POTION))
		if(p.getItemInHand().getItemMeta().getDisplayName().equals("§9RedBull")){
			 p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1F, 1F);
			e.setCancelled(true);
			if(!cooldown.contains(p)){
				cooldown.add(p);
				for (ItemStack is : p.getInventory().all(Material.POTION).values()) {
					if(is.getItemMeta().getDisplayName().equals("§9RedBull")){
					p.getInventory().remove(is);
					p.getInventory().addItem(RedBull.RedBullDrinkM((is.getAmount() - 1)));
					}
				}
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(),
						new Runnable() {
							@Override public void run() {
								cooldown.remove(p);
								p.setAllowFlight(false);
							}
						}, 20 * 20L);
				
				 p.setVelocity(new Vector(0,1.5,0));
				 
			       Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			    		public void run() {
			    	   p.setAllowFlight(true);
			    	   p.setFlying(true);
			         }
			       }, 20L * 1);
			} else {
				   p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1F, 1F);
			}
		}
		if(p.getItemInHand().getType().equals(Material.POTION))
		if(p.getItemInHand().getItemMeta().getDisplayName().equals("§7Shisha to go")){
			e.setCancelled(true);
			if (!Smoking.contains(p)) {
				Smoking.add(p);
				for (ItemStack is : p.getInventory().all(Material.POTION).values()) {
					if(is.getItemMeta().getDisplayName().equals("§7Shisha to go")){
					p.getInventory().remove(is);
					p.getInventory().addItem(shisha.shishaToGoM((is.getAmount() - 1)));
					}
				}
				ParticleEffect.SMOKE_LARGE.display(0.5F, 0.5F, 0.5F, 0.2f, 30,
						p.getLocation().add(0,1,0), 5); 
				ParticleEffect.FLAME.display(0.5F, 0.5F, 0.5F, 0.2f, 30,
						p.getLocation().add(0,1,0), 5); 
				p.playEffect(p.getLocation().add(0,1,0), Effect.FLAME, 20);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1F, 1F);
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * 50, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 50, 3));
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 50, 1));
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {
						for (PotionEffect potion : p.getActivePotionEffects()) {
							p.removePotionEffect(potion.getType());
						}
						Smoking.remove(p);

					}
				}, 20 * 30);

			} else {
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1F, 1F);

				Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
					@Override
					public void run() {

						for (int i = 0; i < 30; i++) {
							final Item ITEM = p.getWorld().dropItem(p.getLocation(), ItemFactory.create(Material.COAL_BLOCK, (byte) 0, UUID.randomUUID().toString(), null));
							ITEM.setPickupDelay(30000);
							ITEM.setVelocity(new Vector(r.nextDouble() - 0.5, r.nextDouble() / 2.0, r.nextDouble() - 0.5));
							items.add(ITEM);
						}
						Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
							@Override
							public void run() {
								for (Item i : items)
									i.remove();
							}
						}, 50);
					}
				}, 9);
			}
		}
	}

}
