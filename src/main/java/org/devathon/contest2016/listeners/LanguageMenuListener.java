package org.devathon.contest2016.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.utils.PlayerUtils;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class LanguageMenuListener implements Listener {
    @EventHandler
    public void onLanguageMenuItemClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player))
            return;
        Player player = ((Player) event.getWhoClicked());
        if (event.getClickedInventory() == null)
            return;
        if (!event.getClickedInventory().getName().equals(LanguageManager.getLanguage(player).getTranslation("localization.menu.title")))
            return;
        if (event.getCurrentItem() == null)
            return;
        ItemStack clickedItem = event.getCurrentItem();
        if (!clickedItem.hasItemMeta()) {
            event.setResult(Event.Result.DENY);
            return;
        }
        if (clickedItem.getType() == Material.BOOK_AND_QUILL) {
            for (Language language : Language.values()) {
                if (ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName()).equals(language.getFullName())) {
                    if (LanguageManager.getLanguage(player).getAbbreviation().equals(language.getAbbreviation())) {
                        PlayerUtils.playErrorSound(player);
                        PlayerUtils.sendErrorMessage(player, language.getTranslation("localization.message.setlang.fail"));
                        return;
                    }
                    player.closeInventory();
                    LanguageManager.setLanguage(player, language);
                    PlayerUtils.playClickSound(player);
                    PlayerUtils.sendSuccessMessage(player, language.getFormattedTranslation("localization.message.setlang.success", ChatColor.YELLOW + language.getFullName()));
                }
            }
            return;
        }
        if (clickedItem.getType() == Material.BARRIER) {
            PlayerUtils.playClickSound(player);
            player.closeInventory();
        }
    }
}
