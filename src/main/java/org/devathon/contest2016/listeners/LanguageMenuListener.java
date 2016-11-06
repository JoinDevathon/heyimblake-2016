package org.devathon.contest2016.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.devathon.contest2016.gadget.GadgetManager;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.robotutils.RobotManager;
import org.devathon.contest2016.utils.CommonItemStacks;
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
                    player.closeInventory();
                    LanguageManager.getLanguage(player).getRobotNotActiveBar().removePlayer(player);
                    LanguageManager.getLanguage(player).getRobotActiveBar().removePlayer(player);
                    LanguageManager.setLanguage(player, language);
                    PlayerUtils.playClickSound(player);
                    PlayerUtils.sendSuccessMessage(player, language.getFormattedTranslation("localization.message.setlang.success", ChatColor.YELLOW + language.getFullName()));
                    player.getInventory().clear();
                    if (RobotManager.getInstance().isRobot(player)) {
                        language.getRobotActiveBar().addPlayer(player);
                        language.getRobotNotActiveBar().removePlayer(player);
                        player.getInventory().addItem(CommonItemStacks.becomeHuman(language));
                        GadgetManager.getInstance().getGadgets().forEach(gadget -> player.getInventory().addItem(gadget.getItem(language)));
                        player.getInventory().setHelmet(CommonItemStacks.helmet(language));
                        player.getInventory().setChestplate(CommonItemStacks.chestplate(language));
                        player.getInventory().setLeggings(CommonItemStacks.leggings(language));
                        player.getInventory().setBoots(CommonItemStacks.boots(language));
                        return;
                    }
                    language.getRobotActiveBar().removePlayer(player);
                    language.getRobotNotActiveBar().addPlayer(player);
                    player.getInventory().addItem(CommonItemStacks.becomeRobot(language));

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
