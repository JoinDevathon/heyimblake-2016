package org.devathon.contest2016.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.devathon.contest2016.commands.AnnotatedRobotSubCommand;
import org.devathon.contest2016.commands.RobotSubCommandExecutor;
import org.devathon.contest2016.commands.RobotSubCommandHandler;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.robotutils.Robot;
import org.devathon.contest2016.robotutils.RobotCreator;
import org.devathon.contest2016.robotutils.RobotManager;
import org.devathon.contest2016.utils.PlayerUtils;

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
        Player player = getHandler().getPlayer();
        Player target = Bukkit.getPlayer(getHandler().getArgs()[0]);
        Language language = LanguageManager.getLanguage(player);
        if (target == null) {
            PlayerUtils.sendErrorMessage(player, language.getTranslation("general.command.playernotfound"));
            return;
        }
        if (!RobotManager.getInstance().isRobot(target)) {
            Robot targetRobot = new RobotCreator().setPlayer(target).create();
            PlayerUtils.sendSuccessMessage(player, language.getFormattedTranslation("command.toggle.success.roboton", targetRobot.getPlayer().getName()));
            return;
        }
        Robot targetRobot = RobotManager.getInstance().getRobotOf(target);
        targetRobot.remove();
        PlayerUtils.sendSuccessMessage(player, language.getFormattedTranslation("command.toggle.success.robotoff", target.getName()));
    }
}
