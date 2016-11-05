package org.devathon.contest2016.commands;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public abstract class AnnotatedRobotSubCommand {
    private RobotSubCommandHandler robotSubCommandHandler;

    public AnnotatedRobotSubCommand(RobotSubCommandHandler robotSubCommandHandler) {
        this.robotSubCommandHandler = robotSubCommandHandler;
    }

    public abstract void runPlayer();

    public RobotSubCommandHandler getHandler() {
        return this.robotSubCommandHandler;
    }
}
