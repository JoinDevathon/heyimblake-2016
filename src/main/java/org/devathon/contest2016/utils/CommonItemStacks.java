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
        itemMeta.setDisplayName(ChatColor.RED + language.getTranslation("general.item.name.cancel"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
