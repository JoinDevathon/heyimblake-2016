package org.devathon.contest2016.threads;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class LaserEyesParticleSpawnThread extends BukkitRunnable {

    double i = 0;

    private Location location, loc2;

    public LaserEyesParticleSpawnThread(Location location) {
        this.location = location.clone();
        this.loc2 = location.clone();
    }

    @Override
    public void run() {
        if (i >= 5) {
            this.cancel();
            return;
        }
        Vector direction = location.getDirection();
        loc2.add(direction.normalize().multiply(1.6));
        location.getWorld().spawnParticle(Particle.REDSTONE, loc2, 0);
        location.getWorld().spawnParticle(Particle.CLOUD, loc2.subtract(0, -0.3, 0), 0);
        i += 0.25;
    }
}
