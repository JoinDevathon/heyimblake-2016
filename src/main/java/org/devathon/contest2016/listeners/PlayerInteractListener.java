package org.devathon.contest2016.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.devathon.contest2016.gadget.GadgetManager;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.robotutils.RobotCreator;
import org.devathon.contest2016.robotutils.RobotManager;
import org.devathon.contest2016.utils.CommonItemStacks;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!RobotManager.getInstance().isRobot(player))
            return;
        if (event.getItem() == null)
            return;
        if (!event.getItem().hasItemMeta())
            return;
        if (event.getAction() == null)
            return;
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR)
            return;
        ItemStack clickedItem = event.getItem();
        Language language = LanguageManager.getLanguage(player);
        GadgetManager.getInstance().getlanguageGadgetItemsMap().get(language).keySet().stream().filter(itemStack -> itemStack.getItemMeta().getDisplayName().equals(clickedItem.getItemMeta().getDisplayName())).forEachOrdered(itemStack -> {
            GadgetManager.getInstance().performGadget(player, GadgetManager.getInstance().getlanguageGadgetItemsMap().get(language).get(itemStack));
        });
        if (clickedItem.getItemMeta().getDisplayName().equals(CommonItemStacks.becomeHuman(language).getItemMeta().getDisplayName())) {
            PlayerInventory inventory = player.getInventory();
            int i = inventory.getHeldItemSlot();
            inventory.remove(CommonItemStacks.becomeHuman(language));
            inventory.setItem(i, CommonItemStacks.becomeRobot(language));
            if (RobotManager.getInstance().isRobot(player))
                RobotManager.getInstance().getRobotOf(player).remove();
        } else if (clickedItem.getItemMeta().getDisplayName().equals(CommonItemStacks.becomeRobot(language).getItemMeta().getDisplayName())) {
            PlayerInventory inventory = player.getInventory();
            int i = inventory.getHeldItemSlot();
            inventory.remove(CommonItemStacks.becomeRobot(language));
            inventory.setItem(i, CommonItemStacks.becomeHuman(language));
            if (!RobotManager.getInstance().isRobot(player))
                new RobotCreator().setPlayer(player).create();

        }
    }
}
