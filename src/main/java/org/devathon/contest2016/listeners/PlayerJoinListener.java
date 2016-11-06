package org.devathon.contest2016.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffectType;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.utils.CommonItemStacks;
import org.devathon.contest2016.utils.Constants;
import org.devathon.contest2016.utils.PlayerUtils;

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
        PlayerInventory inventory = player.getInventory();
        Language language = LanguageManager.getLanguage(player);
        inventory.clear();

        inventory.addItem(CommonItemStacks.becomeRobot(language));
        inventory.addItem(CommonItemStacks.changeLanguage(language));

        if (player.getGameMode() != GameMode.CREATIVE || player.getGameMode() != GameMode.SPECTATOR) {
            player.setFlying(false);
            player.setAllowFlight(false);
        }
        player.removePotionEffect(PotionEffectType.SLOW);

        language.getRobotActiveBar().removePlayer(player);
        language.getRobotNotActiveBar().addPlayer(player);

        PlayerUtils.sendMessage(player, language.getFormattedTranslation("listener.playerjoin.message", Constants.ACCENT_COLOR + "/robot info" + Constants.BASE_COLOR));
    }
}
