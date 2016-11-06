package org.devathon.contest2016.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class PlayerUtils {
    public static void sendMessage(Player player, String message) {
        player.sendMessage(Constants.TAG + message);
    }

    public static void sendErrorMessage(Player player, String message) {
        player.sendMessage(Constants.TAG + Constants.ERROR_COLOR + message);
    }

    public static void sendSuccessMessage(Player player, String message) {
        player.sendMessage(Constants.TAG + Constants.SUCCESS_COLOR + message);
    }

    public static void playErrorSound(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 10, 1);
    }

    public static void playClickSound(Player player) {
        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 10, 3);
    }
}
