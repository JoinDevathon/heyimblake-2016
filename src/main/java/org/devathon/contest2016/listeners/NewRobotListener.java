package org.devathon.contest2016.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;
import org.devathon.contest2016.events.NewRobotEvent;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.utils.CommonItemStacks;
import org.devathon.contest2016.utils.PlayerUtils;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class NewRobotListener implements Listener {
    @EventHandler
    public void onNewRobotCreation(NewRobotEvent event) {
        Player player = event.getPlayer();
        Language language = LanguageManager.getLanguage(player);
        PlayerUtils.sendSuccessMessage(player, LanguageManager.getLanguage(player).getFormattedTranslation("listener.newrobot.message", event.getRobot().getRobotName()));
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 10, 1);
        PlayerInventory inventory = player.getInventory();
        inventory.setHelmet(CommonItemStacks.helmet(language));
        inventory.setChestplate(CommonItemStacks.chestplate(language));
        inventory.setLeggings(CommonItemStacks.leggings(language));
        inventory.setBoots(CommonItemStacks.boots(language));
    }
}
