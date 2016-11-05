package org.devathon.contest2016.localization;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class LanguageManager {
    private static Map<String, Language> uuidLanguageMap = new HashMap<>();

    public static Language getLanguage(String uuid) {
        return uuidLanguageMap.getOrDefault(uuid, Language.ENGLISH);
    }

    public static Language getLanguage(Player player) {
        return getLanguage(player.getUniqueId().toString());
    }

    public static void setLanguage(String uuid, Language language) {
        if (uuidLanguageMap.containsKey(uuid))
            uuidLanguageMap.remove(uuid);
        uuidLanguageMap.put(uuid, language);
    }

    public static void setLanguage(Player player, Language language) {
        setLanguage(player.getUniqueId().toString(), language);
    }
}
