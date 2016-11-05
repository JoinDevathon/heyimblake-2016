package org.devathon.contest2016.robotutils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.devathon.contest2016.events.NewRobotEvent;
import org.devathon.contest2016.events.RemoveRobotEvent;

import java.util.UUID;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class Robot {
    private String robotName;
    private Player player;

    protected Robot(Player player) {
        this.player = player;
        this.robotName = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
        RobotManager.getInstance().addPlayer(this.player, this);
        Bukkit.getPluginManager().callEvent(new NewRobotEvent(this));
    }

    public String getRobotName() {
        return this.robotName;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void remove() {
        Bukkit.getPluginManager().callEvent(new RemoveRobotEvent(this));
        RobotManager.getInstance().removePlayer(this.player);
        this.player = null;
    }
}
