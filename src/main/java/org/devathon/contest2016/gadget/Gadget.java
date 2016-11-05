package org.devathon.contest2016.gadget;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.devathon.contest2016.localization.Language;


/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public abstract class Gadget {
    public abstract String name(Language language);
    public abstract ItemStack itemStack();
    public abstract double usageDelay();
    public abstract void run(Player player);

    public ItemStack getItem(Language language) {
        ItemStack itemStack = new ItemStack(itemStack().getType(), itemStack().getAmount(), itemStack().getDurability());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + name(language) + " " + ChatColor.GRAY + language.getTranslation("itemstack.general.rightclick"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public double getDelay() {
        return usageDelay();
    }
}
