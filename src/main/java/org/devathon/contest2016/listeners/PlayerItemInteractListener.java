package org.devathon.contest2016.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.localization.LanguageMenu;
import org.devathon.contest2016.robotutils.RobotCreator;
import org.devathon.contest2016.robotutils.RobotManager;
import org.devathon.contest2016.utils.CommonItemStacks;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class PlayerItemInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteractItem(PlayerInteractEvent event) {
        if (event.getAction() == null)
            return;
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
            return;
        if (event.getItem() == null)
            return;
        if (!event.getItem().hasItemMeta())
            return;
        ItemStack clickedItem = event.getItem();
        Player player = event.getPlayer();
        Language language = LanguageManager.getLanguage(player);

        if (clickedItem.getItemMeta().getDisplayName().equals(CommonItemStacks.changeLanguage(language).getItemMeta().getDisplayName())) {
            LanguageMenu.showToPlayer(player);
            return;
        }

        if (clickedItem.getItemMeta().getDisplayName().equals(CommonItemStacks.becomeRobot(language).getItemMeta().getDisplayName())) {
            if (!RobotManager.getInstance().isRobot(player))
                new RobotCreator().setPlayer(player).create();
            return;
        }

    }
}
