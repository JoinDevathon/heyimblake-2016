package org.devathon.contest2016.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.devathon.contest2016.commands.AnnotatedRobotSubCommand;
import org.devathon.contest2016.commands.RobotSubCommandExecutor;
import org.devathon.contest2016.commands.RobotSubCommandHandler;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.robotutils.RobotManager;
import org.devathon.contest2016.utils.PlayerUtils;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
@RobotSubCommandExecutor(subCommand = "language",
syntax = "<Language>",
description = "Set your language.",
requiresArguments = true,
mustBeRobot = false,
opOnly = false)
public class LanguageSubCMD extends AnnotatedRobotSubCommand {

    public LanguageSubCMD(RobotSubCommandHandler robotSubCommandHandler) {
        super(robotSubCommandHandler);
    }

    @Override
    public void runPlayer() {
        Player player = getHandler().getPlayer();
        String arg = getHandler().getArgs()[0];
        String allLanguages = "";
        for (Language lang : Language.values()) {
            if (arg.equalsIgnoreCase(lang.getAbbreviation())) {
                LanguageManager.setLanguage(player, lang);
                PlayerUtils.sendSuccessMessage(player, lang.getFormattedTranslation("localization.message.setlang.success", ChatColor.YELLOW + lang.getFullName()));
                if (RobotManager.getInstance().isRobot(player)) {
                    lang.getRobotActiveBar().addPlayer(player);
                    lang.getRobotNotActiveBar().removePlayer(player);
                    return;
                }
                lang.getRobotActiveBar().removePlayer(player);
                lang.getRobotNotActiveBar().addPlayer(player);
                return;
            }
            allLanguages += lang.getAbbreviation() + ", ";
        }
        PlayerUtils.sendErrorMessage(player, LanguageManager.getLanguage(player).getTranslation("command.language.invalidlang"));
        PlayerUtils.sendErrorMessage(player, allLanguages);

    }
}
