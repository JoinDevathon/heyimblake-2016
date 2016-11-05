package org.devathon.contest2016.robotutils;

import org.bukkit.entity.Player;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class RobotCreator {
    private Player player;

    public RobotCreator() {

    }

    public RobotCreator setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public Robot create() {
        return new Robot(this.player);
    }
}
