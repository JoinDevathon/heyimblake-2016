package org.devathon.contest2016.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Language language = LanguageManager.getLanguage(player);
        if (!RobotManager.getInstance().isRobot(player))
            return;
        if (event.getTo() == null)
            return;
        Location location = event.getTo();
        if (location.getBlock() == null)
            return;
        if (location.getBlock().getType() == Material.WATER || location.getBlock().getType() == Material.STATIONARY_WATER) {
            if (player.getInventory().getBoots().getType() != null && player.getInventory().getBoots().getType() != Material.LEATHER_BOOTS) {
                player.getInventory().setBoots(CommonItemStacks.rustyBoots(language));
                PlayerUtils.sendErrorMessage(player, language.getTranslation("listener.playermove.rustybootsalert"));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1), true);
            }
        }
    }
}
