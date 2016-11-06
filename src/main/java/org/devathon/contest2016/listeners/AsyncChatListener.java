package org.devathon.contest2016.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.devathon.contest2016.robotutils.RobotManager;

import java.io.UnsupportedEncodingException;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class AsyncChatListener implements Listener {
    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!RobotManager.getInstance().isRobot(player))
            return;
        byte[] messagebyte;
        try {
            messagebyte = event.getMessage().getBytes("UTF-8");
            String finalmsg = "";
            for (byte b : messagebyte) {
                if (Integer.toBinaryString(b).equals("100000")) {
                    finalmsg += " ";
                } else
                    finalmsg += Integer.toBinaryString(b);
            }
            event.setMessage(finalmsg);
        } catch (UnsupportedEncodingException e) {
            event.setMessage("01000110011000010110100101101100011101010111001001100101");
        }
    }
}
