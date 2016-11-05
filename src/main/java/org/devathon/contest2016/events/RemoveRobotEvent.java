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
public class RemoveRobotEvent extends Event {
    private Robot robot;
    private Player player;
    private static final HandlerList handlers = new HandlerList();

    public RemoveRobotEvent(Robot robot) {
        this.robot = robot;
        this.player = robot.getPlayer();
    }

    public Robot getRobot() {
        return robot;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
