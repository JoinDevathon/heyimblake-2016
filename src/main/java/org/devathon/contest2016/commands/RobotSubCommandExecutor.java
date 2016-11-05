package org.devathon.contest2016.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RobotSubCommandExecutor {

    String subCommand();

    String syntax();

    String description();

    boolean requiresArguments();

    boolean mustBeRobot();

    boolean opOnly() default false;
}
