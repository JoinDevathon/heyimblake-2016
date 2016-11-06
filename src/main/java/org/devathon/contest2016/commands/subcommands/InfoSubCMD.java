package org.devathon.contest2016.commands.subcommands;

import org.bukkit.entity.Player;
import org.devathon.contest2016.commands.AnnotatedRobotSubCommand;
import org.devathon.contest2016.commands.RobotSubCommandExecutor;
import org.devathon.contest2016.commands.RobotSubCommandHandler;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.utils.Constants;
import org.devathon.contest2016.utils.PlayerUtils;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
@RobotSubCommandExecutor(subCommand = "info",
        syntax = "",
        description = "Sends a giant wall of text about this submission.",
        requiresArguments = false,
        mustBeRobot = false)
public class InfoSubCMD extends AnnotatedRobotSubCommand {

    public InfoSubCMD(RobotSubCommandHandler robotSubCommandHandler) {
        super(robotSubCommandHandler);
    }

    @Override
    public void runPlayer() {
        Player player = getHandler().getPlayer();
        Language language = LanguageManager.getLanguage(player);
        PlayerUtils.sendMessage(player, language.getTranslation("command.info.part1"));
        PlayerUtils.sendMessage(player, language.getTranslation("command.info.part2"));
        PlayerUtils.sendMessage(player, language.getTranslation("command.info.part3"));
        PlayerUtils.sendMessage(player, language.getTranslation("command.info.part4"));
        PlayerUtils.sendMessage(player, language.getTranslation("command.info.part5"));
        PlayerUtils.sendMessage(player, language.getTranslation("command.info.part6"));
        PlayerUtils.sendMessage(player, Constants.ACCENT_COLOR + language.getTranslation("command.info.part7"));
    }
}
