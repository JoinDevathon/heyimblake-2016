package org.devathon.contest2016.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class RobotSubCommandHandler {

    private CommandSender commandSender;
    private String[] args;

    public RobotSubCommandHandler(CommandSender commandSender, String[] args) {
        this.commandSender = commandSender;
        this.args = args;
    }

    public CommandSender getCommandSender() {
        return this.commandSender;
    }

    public String[] getArgs() {
        return this.args;
    }

    public boolean isPlayer() {
        return this.commandSender instanceof Player;
    }
}
