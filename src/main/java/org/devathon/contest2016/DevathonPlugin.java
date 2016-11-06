package org.devathon.contest2016;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.commands.RobotBaseCommand;
import org.devathon.contest2016.listeners.*;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.threads.LaserEyesParticleSpawnThread;
import org.devathon.contest2016.threads.RobotFlyingParticleThread;

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
        initializeThreads();
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
        Arrays.stream(Language.values()).forEach(language -> language.getRobotActiveBar().setTitle(ChatColor.AQUA + "" + ChatColor.BOLD + language.getTranslation("localization.bossbar.roboton")));
        Arrays.stream(Language.values()).forEach(language -> language.getRobotNotActiveBar().setTitle(ChatColor.GRAY + "" + ChatColor.BOLD + language.getTranslation("localization.bossbar.robotoff")));
    }

    private void registerListeners() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
        pluginManager.registerEvents(new LanguageMenuListener(), this);
        pluginManager.registerEvents(new RemoveRobotListener(), this);
        pluginManager.registerEvents(new NewRobotListener(), this);
        pluginManager.registerEvents(new PlayerInventoryClickListener(), this);
        pluginManager.registerEvents(new PlayerInteractListener(), this);
        pluginManager.registerEvents(new PlayerMoveListener(), this);
    }

    private void initializeThreads() {
        new RobotFlyingParticleThread().runTaskTimer(this, 0L, 3L);
    }

}

