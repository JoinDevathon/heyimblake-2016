package org.devathon.contest2016.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.devathon.contest2016.robotutils.RobotManager;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class PlayerInventoryClickListener implements Listener {
    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player))
            return;
        Player player = ((Player) event.getWhoClicked());
        if (!RobotManager.getInstance().isRobot(player))
            return;
        if (event.getClickedInventory() == null)
            return;
        if (event.getClickedInventory() != player.getInventory())
            return;
        if (event.getSlotType() == null)
            return;
        if (event.getSlotType() != InventoryType.SlotType.ARMOR)
            return;
        event.setResult(Event.Result.DENY);
        event.setCancelled(true);
    }
}
