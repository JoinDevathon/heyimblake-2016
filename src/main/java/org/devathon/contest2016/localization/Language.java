package org.devathon.contest2016.localization;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.devathon.contest2016.DevathonPlugin;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public enum Language {
    ENGLISH("en", "English", new HashMap<>(), Bukkit.createBossBar("en", BarColor.RED, BarStyle.SOLID), Bukkit.createBossBar("en", BarColor.BLUE, BarStyle.SOLID), false);

    private String abbreviation, fullName;
    private Map<String, String> stringsMap;
    private BossBar robotNotActiveBar, robotActiveBar;
    private boolean initialized;

    Language(String abbreviation, String fullName, Map<String, String> stringsMap, BossBar robotNotActiveBar, BossBar robotActiveBar, boolean initialized) {
        this.abbreviation = abbreviation;
        this.fullName = fullName;
        this.stringsMap = stringsMap;
        this.robotNotActiveBar = robotNotActiveBar;
        this.robotActiveBar = robotActiveBar;
        this.initialized = initialized;
    }

    @Override
    public String toString() {
        return this.abbreviation;
    }

    public String getFileName() {
        return "messages_" + this.toString() + ".json";
    }

    public String getTranslation(String key) {
       return this == ENGLISH ? ChatColor.translateAlternateColorCodes('&', this.stringsMap.getOrDefault(key, "Invalid Key: " + key)) : ChatColor.translateAlternateColorCodes('&', this.stringsMap.getOrDefault(key, ENGLISH.getString(key)))
    }

    public String getFormattedTranslation(String key, Object... params) {
        return String.format(this.getTranslation(key), params);
    }

    public void initialize() {
        if (this.initialized)
            return;
        InputStream is = DevathonPlugin.getInstance().getResource(this.getFileName());
        if (is == null)
            is = DevathonPlugin.getInstance().getResource(ENGLISH.getFileName());
        JsonObject json = new JsonParser().parse(new InputStreamReader(is)).getAsJsonObject();
        json.entrySet().forEach(stringJsonElementEntry -> this.stringsMap.put(stringJsonElementEntry.getKey(), stringJsonElementEntry.getValue().getAsString()));
        this.initialized = true;
    }


}
