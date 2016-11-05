package org.devathon.contest2016.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.devathon.contest2016.localization.Language;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class CommonItemStacks {
    public static ItemStack cancelItem(Language language) {
        ItemStack itemStack = new ItemStack(Material.BARRIER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + language.getTranslation("itemstack.cancel.name"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack helmet(Language language) {
        ItemStack itemStack = new ItemStack(Material.IRON_HELMET, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + language.getTranslation("itemstack.robotarmor.helmet.name"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack chestplate(Language language) {
        ItemStack itemStack = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + language.getTranslation("itemstack.robotarmor.chestplate.name"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack leggings(Language language) {
        ItemStack itemStack = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + language.getTranslation("itemstack.robotarmor.leggings.name"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack boots(Language language) {
        ItemStack itemStack = new ItemStack(Material.IRON_BOOTS, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + language.getTranslation("itemstack.robotarmor.boots.name"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
