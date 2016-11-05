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
    @Override
    public void run() {
        for (Player player : RobotManager.getInstance().playerRobotMap.keySet()) {
            if (!player.isFlying())
                return;
            Location location = player.getLocation().add(0, -0.5, 0);
            int x = 
            World world = location.getWorld();
            world.spawnParticle(Particle.CLOUD, location, 0);
        }
    }
}
