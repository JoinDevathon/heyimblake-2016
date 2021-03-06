package org.devathon.contest2016.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.devathon.contest2016.robotutils.Robot;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class NewRobotEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Robot robot;

    public NewRobotEvent(Robot robot) {
        this.player = robot.getPlayer();
        this.robot = robot;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public Robot getRobot() {
        return robot;
    }
}
