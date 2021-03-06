package org.devathon.contest2016.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffectType;
import org.devathon.contest2016.events.RemoveRobotEvent;
import org.devathon.contest2016.gadget.GadgetManager;
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
public class RemoveRobotListener implements Listener {
    @EventHandler
    public void onRemoveRobot(RemoveRobotEvent event) {
        Player player = event.getPlayer();
        Language language = LanguageManager.getLanguage(player);
        PlayerUtils.sendSuccessMessage(player, language.getTranslation("listener.removerobot.message"));
        PlayerInventory inventory = player.getInventory();
        inventory.setHelmet(null);
        inventory.setChestplate(null);
        inventory.setLeggings(null);
        inventory.setBoots(null);
        if (inventory.contains(CommonItemStacks.becomeHuman(language))) {
            inventory.remove(CommonItemStacks.becomeHuman(language));
            inventory.addItem(CommonItemStacks.becomeRobot(language));
        }

        player.setFlying(false);
        player.setAllowFlight(false);
        player.removePotionEffect(PotionEffectType.SLOW);

        language.getRobotActiveBar().removePlayer(player);
        language.getRobotNotActiveBar().addPlayer(player);

        GadgetManager.getInstance().getGadgets().forEach(gadget -> inventory.remove(gadget.getItem(language)));
    }
}
