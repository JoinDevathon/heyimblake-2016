package org.devathon.contest2016.localization;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.devathon.contest2016.utils.CommonItemStacks;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class LanguageMenu {
    public static void showToPlayer(Player player) {
        player.closeInventory();
        int invsize = 27 + ((Language.values().length / 4) * 9) >= 54 ? 54 : 27 + ((Language.values().length / 4) * 9);
        Inventory inventory = Bukkit.createInventory(player, invsize, LanguageManager.getLanguage(player).getTranslation("localization.menu.title"));
        int i = 10;
        for (Language language : Language.values()) {
            inventory.setItem(i, language.getGUIItem(player.getUniqueId().toString()));
            if ((i + 2) % 9 == 0) {
                i += 3;
            } else {
                i += 2;
            }
        }
        inventory.setItem(inventory.getSize() - 1, CommonItemStacks.cancelItem(LanguageManager.getLanguage(player)));
        player.openInventory(inventory);
    }
}
