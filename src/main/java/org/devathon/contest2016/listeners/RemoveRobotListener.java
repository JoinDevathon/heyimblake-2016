package org.devathon.contest2016.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;
import org.devathon.contest2016.events.RemoveRobotEvent;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.utils.CommonItemStacks;
import org.devathon.contest2016.utils.PlayerUtils;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class RemoveRobotListener implements Listener {
    @EventHandler
    public void onRemoveRobot(RemoveRobotEvent event) {
        Player player = event.getPlayer();
        PlayerUtils.sendSuccessMessage(player, LanguageManager.getLanguage(player).getTranslation("listener.removerobot.message"));
        PlayerInventory inventory = player.getInventory();
        inventory.setHelmet(null);
        inventory.setChestplate(null);
        inventory.setLeggings(null);
        inventory.setBoots(null);
    }
}
