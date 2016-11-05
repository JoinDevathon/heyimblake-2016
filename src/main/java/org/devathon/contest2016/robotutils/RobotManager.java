package org.devathon.contest2016.robotutils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class RobotManager {
    private static RobotManager instance = new RobotManager();
    public static RobotManager getInstance() {
        return instance;
    }

    public Map<Player, Robot> playerRobotMap = new HashMap<>();

    public boolean isRobot(Player player) {
        return playerRobotMap.containsKey(player);
    }

    public void addPlayer(Player player, Robot robot) {
        playerRobotMap.put(player, robot);
    }

    public void removePlayer(Player player) {
        playerRobotMap.remove(player);
    }

    public Robot getRobotOf(Player player) {
        return playerRobotMap.getOrDefault(player, null);
    }
}
