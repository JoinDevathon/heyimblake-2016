package org.devathon.contest2016.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.devathon.contest2016.events.NewRobotEvent;
import org.devathon.contest2016.localization.LanguageManager;
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
        PlayerUtils.sentSuccessMessage(player, LanguageManager.getLanguage(player).getFormattedTranslation("listener.newrobot.message", event.getRobot().getRobotName()));
    }
}
