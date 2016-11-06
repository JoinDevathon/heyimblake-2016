package org.devathon.contest2016.gadget.gadgets;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.gadget.Gadget;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.utils.Hologram;

import java.util.*;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class SaySomethingGadget extends Gadget {

    private static Map<Language, Set<String>> languageSetMap = new HashMap<>();
    private static boolean initialized = false;

    public SaySomethingGadget() {
        if (initialized)
            return;
        for (Language language : Language.values()) {
            Set<String> set = new HashSet<>();
            set.add(language.getTranslation("gadget.saysomething.msg1"));
            set.add(language.getTranslation("gadget.saysomething.msg2"));
            set.add(language.getTranslation("gadget.saysomething.msg3"));
            set.add(language.getTranslation("gadget.saysomething.msg4"));
            languageSetMap.put(language, set);
        }
        initialized = true;
    }

    @Override
    public String name(Language language) {
        return language.getTranslation("gadget.saysomething.name");
    }

    @Override
    public ItemStack itemStack() {
        return new ItemStack(Material.PAPER, 1);
    }

    @Override
    public double usageDelay() {
        return 3.5;
    }

    @Override
    public void run(Player player) {
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 10, 1);
        Language language = LanguageManager.getLanguage(player);
        Hologram hologram = new Hologram(ChatColor.GOLD + String.valueOf(languageSetMap.get(language).toArray()[new Random().nextInt(languageSetMap.get(language).size())]));
        Location location = player.getLocation();
        location.add(new Random().nextInt(2), new Random().nextInt(2), new Random().nextInt(2));
        hologram.spawn(location);
        new BukkitRunnable() {
            @Override
            public void run() {
                hologram.despawn();
            }
        }.runTaskLater(DevathonPlugin.getInstance(), 3 * 20L);
    }
}
