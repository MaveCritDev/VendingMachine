package de.mavecrit.vendingmachine.util;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;



import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

public class ItemFactory
{
  public static ItemStack create(Material material, byte data, String displayName, String[] lore)
  {
    ItemStack itemStack = new MaterialData(material, data).toItemStack(1);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName(displayName);
    if (lore != null) {
      List finalLore = itemMeta.hasLore() ? itemMeta.getLore() : new ArrayList();
      for (String s : lore)
        if (s != null)
          finalLore.add(s.replace("&", "§"));
      itemMeta.setLore(finalLore);
    }
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }



  private static MaterialData getMaterialData(String name) {
    return new MaterialData(Integer.parseInt(name.split(":")[0]), 
      (byte)(name
      .split(":").length > 
      1 ? (byte)Integer.parseInt(name.split(":")[1]) : 0));
  }

  public static ItemStack createSkull(String url, String name) {
    ItemStack head = create(Material.SKULL_ITEM, (byte)3, name, new String[0]);

    if (url.isEmpty()) return head;

    SkullMeta headMeta = (SkullMeta)head.getItemMeta();
    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
    profile.getProperties().put("textures", new Property("textures", url));
    try
    {
      Field profileField = headMeta.getClass().getDeclaredField("profile");
      profileField.setAccessible(true);
      profileField.set(headMeta, profile);
    } catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException e1) {
      e1.printStackTrace();
    }
    head.setItemMeta(headMeta);
    return head;
  }

  public static ItemStack createColouredLeather(Material armourPart, int red, int green, int blue) {
    ItemStack itemStack = new ItemStack(armourPart);
    LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta)itemStack.getItemMeta();
    leatherArmorMeta.setColor(Color.fromRGB(red, green, blue));
    itemStack.setItemMeta(leatherArmorMeta);
    return itemStack;
  }


}