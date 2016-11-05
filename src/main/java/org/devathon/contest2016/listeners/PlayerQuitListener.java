package org.devathon.contest2016.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.robotutils.RobotManager;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (RobotManager.getInstance().isRobot(player))
            RobotManager.getInstance().getRobotOf(player).remove();
        LanguageManager.getLanguage(player).getRobotActiveBar().removePlayer(player);
    }
}
