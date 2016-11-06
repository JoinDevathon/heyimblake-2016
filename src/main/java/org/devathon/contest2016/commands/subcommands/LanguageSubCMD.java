package org.devathon.contest2016.commands.subcommands;

import org.bukkit.entity.Player;
import org.devathon.contest2016.commands.AnnotatedRobotSubCommand;
import org.devathon.contest2016.commands.RobotSubCommandExecutor;
import org.devathon.contest2016.commands.RobotSubCommandHandler;
import org.devathon.contest2016.localization.LanguageMenu;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
@RobotSubCommandExecutor(subCommand = "language",
        syntax = "<Language>",
        description = "Set your language.",
        requiresArguments = false,
        mustBeRobot = false,
        opOnly = false)
public class LanguageSubCMD extends AnnotatedRobotSubCommand {

    public LanguageSubCMD(RobotSubCommandHandler robotSubCommandHandler) {
        super(robotSubCommandHandler);
    }

    @Override
    public void runPlayer() {
        Player player = getHandler().getPlayer();
        LanguageMenu.showToPlayer(player);

    }
}
