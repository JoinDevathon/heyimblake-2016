package org.devathon.contest2016;

import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.commands.RobotBaseCommand;
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
}

