package org.devathon.contest2016;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.commands.RobotBaseCommand;
import org.devathon.contest2016.listeners.PlayerJoinListener;
import org.devathon.contest2016.listeners.PlayerQuitListener;
import org.devathon.contest2016.localization.Language;

import java.util.Arrays;

public class DevathonPlugin extends JavaPlugin {

    private static DevathonPlugin instance;

    public static DevathonPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        initializeLanguages();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        // put your disable code here
    }

    private void registerCommands() {
        getCommand("robot").setExecutor(new RobotBaseCommand());
    }

    private void initializeLanguages() {
        Arrays.stream(Language.values()).forEach(Language::initialize);
    }

    private void registerListeners() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
    }

}

