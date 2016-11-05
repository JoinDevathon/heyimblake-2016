package org.devathon.contest2016.commands;

import org.bukkit.entity.Player;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class RobotSubCommandHandler {

    private Player commandSender;
    private String[] args;

    public RobotSubCommandHandler(Player commandSender, String[] args) {
        this.commandSender = commandSender;
        this.args = args;
    }

    public Player getPlayer() {
        return this.commandSender;
    }

    public String[] getArgs() {
        return this.args;
    }

}
