package org.devathon.contest2016.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.localization.LanguageMenu;
import org.devathon.contest2016.robotutils.RobotManager;
import org.devathon.contest2016.utils.CommonItemStacks;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                LanguageMenu.showToPlayer(player);
            }
        }.runTaskLater(DevathonPlugin.getInstance(), 20L);

        PlayerInventory inventory = player.getInventory();
        Language language = LanguageManager.getLanguage(player);
        if (inventory.getHelmet() == CommonItemStacks.helmet(language))
            inventory.setHelmet(null);
        if (inventory.getChestplate() == CommonItemStacks.chestplate(language))
            inventory.setChestplate(null);
        if (inventory.getLeggings() == CommonItemStacks.leggings(language))
            inventory.setLeggings(null);
        if (inventory.getBoots() == CommonItemStacks.boots(language))
            inventory.setBoots(null);

        inventory.remove(CommonItemStacks.becomeRobot(language));
        inventory.remove(CommonItemStacks.becomeHuman(language));
        inventory.setItemInMainHand(CommonItemStacks.becomeRobot(language));
        inventory.addItem(CommonItemStacks.changeLanguage(language));


        player.setFlying(false);
        player.setAllowFlight(false);
        player.removePotionEffect(PotionEffectType.SLOW);

        language.getRobotActiveBar().removePlayer(player);
        language.getRobotNotActiveBar().addPlayer(player);
    }
}
