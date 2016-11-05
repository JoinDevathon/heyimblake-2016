package org.devathon.contest2016;

import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.commands.RobotBaseCommand;

public class DevathonPlugin extends JavaPlugin {

    private static DevathonPlugin instance;

    @Override
    public void onEnable() {
       instance = this;
    }

    @Override
    public void onDisable() {
        // put your disable code here
    }

    private void registerCommands() {
        getCommand("robot").setExecutor(new RobotBaseCommand());
    }

    public static DevathonPlugin getInstance() {
        return instance;
    }
}

