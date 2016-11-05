package org.devathon.contest2016.gadget;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.devathon.contest2016.gadget.gadgets.SaySomethingGadget;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.utils.PlayerUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class GadgetManager {
    private static GadgetManager instance = new GadgetManager();
    private Set<Gadget> gadgets = new HashSet<>();
    private Map<Language, Map<ItemStack, Gadget>> languageGadgetItemsMap = new HashMap<>();
    private Map<Gadget, Map<String, Long>> cooldownMap = new HashMap<>();

    private GadgetManager() {
        initializeGadgets();
        initializeLanguageItems();
    }

    public void performGadget(Player player, Gadget gadget) {
        if (cooldownMap.get(gadget) == null || cooldownMap.get(gadget).get(player.getUniqueId().toString()) == null) {
            gadget.run(player);
            cooldownMap.get(gadget).put(player.getUniqueId().toString(), System.currentTimeMillis());
            return;
        }
        if ((gadget.getDelay() + (cooldownMap.get(gadget).get(player.getUniqueId().toString())/1000)) <= (System.currentTimeMillis()/1000)) {
            gadget.run(player);
            cooldownMap.get(gadget).remove(player.getUniqueId().toString());
            cooldownMap.get(gadget).put(player.getUniqueId().toString(), System.currentTimeMillis());
            return;
        }
        PlayerUtils.sendErrorMessage(player, LanguageManager.getLanguage(player).getFormattedTranslation("gadget.message.oncooldown", (gadget.getDelay() + (cooldownMap.get(gadget).get(player.getUniqueId().toString())/1000)) - (System.currentTimeMillis()/1000)));
    }

    private void initializeLanguageItems() {
        for (Language language : Language.values()) {
            Map<ItemStack, Gadget> map = new HashMap<>();
            gadgets.forEach(gadget -> map.put(gadget.getItem(language), gadget));
            languageGadgetItemsMap.put(language, map);
        }
    }

    private void initializeGadgets() {
        registerGadget(new SaySomethingGadget());
    }

    private void registerGadget(Gadget gadget) {
        gadgets.add(gadget);
        cooldownMap.put(gadget, new HashMap<>());
    }

    public Set<Gadget> getGadgets() {
        return gadgets;
    }

    public Map<Language, Map<ItemStack, Gadget>> getlanguageGadgetItemsMap() {
        return languageGadgetItemsMap;
    }

    public static GadgetManager getInstance() {
        return instance;
    }
}
