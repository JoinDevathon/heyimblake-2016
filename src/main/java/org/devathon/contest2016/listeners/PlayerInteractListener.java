package org.devathon.contest2016.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.devathon.contest2016.gadget.GadgetManager;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.robotutils.RobotManager;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!RobotManager.getInstance().isRobot(player))
            return;
        if (event.getItem() == null)
            return;
        if (event.getAction() == null)
            return;
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR)
            return;
        ItemStack clickedItem = event.getItem();
        Language language = LanguageManager.getLanguage(player);
        GadgetManager.getInstance().getlanguageGadgetItemsMap().get(language).keySet().stream().filter(itemStack -> itemStack == clickedItem).forEachOrdered(itemStack -> {
            GadgetManager.getInstance().performGadget(player, GadgetManager.getInstance().getlanguageGadgetItemsMap().get(language).get(itemStack));
        });
    }
}