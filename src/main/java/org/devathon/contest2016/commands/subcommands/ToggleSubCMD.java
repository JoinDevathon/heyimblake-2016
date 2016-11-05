package org.devathon.contest2016.commands.subcommands;

import org.bukkit.entity.Player;
import org.devathon.contest2016.commands.AnnotatedRobotSubCommand;
import org.devathon.contest2016.commands.RobotSubCommandExecutor;
import org.devathon.contest2016.commands.RobotSubCommandHandler;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
@RobotSubCommandExecutor(subCommand = "toggle",
syntax = "<Player>",
description = "Forces the Robot status of a player to be toggled.",
requiresArguments = true,
mustBeRobot = false,
opOnly = true)
public class ToggleSubCMD extends AnnotatedRobotSubCommand {

    public ToggleSubCMD(RobotSubCommandHandler robotSubCommandHandler) {
        super(robotSubCommandHandler);
    }

    @Override
    public void runPlayer() {
        Player player = ((Player) getHandler().getCommandSender());
    }
}
