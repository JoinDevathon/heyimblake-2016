package org.devathon.contest2016.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.devathon.contest2016.commands.subcommands.LanguageSubCMD;
import org.devathon.contest2016.commands.subcommands.ToggleSubCMD;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.localization.LanguageManager;
import org.devathon.contest2016.robotutils.Robot;
import org.devathon.contest2016.robotutils.RobotCreator;
import org.devathon.contest2016.robotutils.RobotManager;
import org.devathon.contest2016.utils.Constants;
import org.devathon.contest2016.utils.PlayerUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class RobotBaseCommand implements CommandExecutor {

    private static Map<String, Class<? extends AnnotatedRobotSubCommand>> subCommandClassMap = new HashMap<>();

    public RobotBaseCommand() {
        initialize();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player in order to do this.");
            return true;
        }
        Player player = ((Player) commandSender);
        Language language = LanguageManager.getLanguage(player);
        if (strings.length == 0) {
            if (!RobotManager.getInstance().isRobot(player)) {
                Robot targetRobot = new RobotCreator().setPlayer(player).create();
                return true;
            }
            Robot targetRobot = RobotManager.getInstance().getRobotOf(player);
            targetRobot.remove();
            return true;
        }
        String subCMDName = strings[0];
        if (!subCommandClassMap.containsKey(subCMDName)) {
            showHelpMessage(player, command);
            return true;
        }

        Class<? extends AnnotatedRobotSubCommand> clazz = subCommandClassMap.get(subCMDName);
        RobotSubCommandHandler handler = new RobotSubCommandHandler(player, Arrays.copyOfRange(strings, 1, strings.length));

        if (getAnnotation(clazz).opOnly() && !player.isOp()) {
            PlayerUtils.sendErrorMessage(player, language.getTranslation("command.general.operror"));
            PlayerUtils.playErrorSound(player);
            return true;
        }
        if (getAnnotation(clazz).requiresArguments() && handler.getArgs().length == 0) {
            player.sendMessage(Constants.TAG + ChatColor.WHITE + language.getTranslation("command.general.usage") + "/" + command.getName() + " " + getAnnotation(clazz).subCommand() + " " + getAnnotation(clazz).syntax());
            PlayerUtils.playErrorSound(player);
            return true;
        }
        if (getAnnotation(clazz).mustBeRobot() && !RobotManager.getInstance().isRobot(player)) {
            PlayerUtils.sendErrorMessage(player, language.getTranslation("command.general.notrobot"));
            PlayerUtils.playErrorSound(player);
            return true;
        }
        execute(clazz, handler);
        return true;
    }

    private void showHelpMessage(Player player, Command command) {
        Language language = LanguageManager.getLanguage(player);
        player.sendMessage(" ");
        player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + language.getTranslation("command.general.help.title"));
        String bullet = ChatColor.DARK_AQUA + "" + '\u25CF' + " " + ChatColor.YELLOW;
        for (Class<? extends AnnotatedRobotSubCommand> clazz : subCommandClassMap.values()) {
            player.sendMessage(bullet + "/" + command.getName() + " " + getAnnotation(clazz).subCommand() + " " + getAnnotation(clazz).syntax() + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + getAnnotation(clazz).description());
        }
        player.sendMessage(" ");

    }

    public void initialize() {
        registerSubCommand(ToggleSubCMD.class);
        registerSubCommand(LanguageSubCMD.class);
    }

    private void registerSubCommand(Class<? extends AnnotatedRobotSubCommand> clazz) {
        String subCMD = getAnnotation(clazz).subCommand();
        if (!subCommandClassMap.containsKey(subCMD))
            subCommandClassMap.put(subCMD, clazz);
    }

    private RobotSubCommandExecutor getAnnotation(Class<? extends AnnotatedRobotSubCommand> clazz) {
        if (clazz.isAnnotationPresent(RobotSubCommandExecutor.class)) {
            return clazz.getAnnotation(RobotSubCommandExecutor.class);
        }
        return null;
    }

    private void execute(Class<? extends AnnotatedRobotSubCommand> clazz, RobotSubCommandHandler handler) {
        try {
            Constructor constructor = clazz.getConstructor(handler.getClass());
            AnnotatedRobotSubCommand subcommand = ((AnnotatedRobotSubCommand) constructor.newInstance(handler));
            subcommand.runPlayer();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
