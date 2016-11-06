package org.devathon.contest2016.threads;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.devathon.contest2016.robotutils.RobotManager;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class RobotFlyingParticleThread extends BukkitRunnable {

    private static int i = 15;

    @Override
    public void run() {
        i += 15;
        if (i >= 360) {
            i = 15;
        }
        for (Player player : RobotManager.getInstance().playerRobotMap.keySet()) {
            Location location = player.getLocation().add(0, -0.3, 0);
            Location circle = location.clone().add(0, -.2, 0);
            circle.setX(circle.getX() - Math.sin(i));
            circle.setZ(circle.getZ() - Math.cos(i));
            World world = location.getWorld();
            world.spawnParticle(Particle.SMOKE_LARGE, circle, 0);
            world.spawnParticle(Particle.FLAME, location, 0);
        }
    }
}
