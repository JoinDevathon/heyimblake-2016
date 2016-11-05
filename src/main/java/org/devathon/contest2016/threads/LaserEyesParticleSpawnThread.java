package org.devathon.contest2016.threads;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class LaserEyesParticleSpawnThread extends BukkitRunnable {

    public static Set<ArmorStand> armorStands = new HashSet<>();

    @Override
    public void run() {
        for (ArmorStand armorStand : armorStands) {
            Location location = armorStand.getLocation();
            location.getWorld().spawnParticle(Particle.REDSTONE, location, 0);
            location.getWorld().spawnParticle(Particle.CLOUD, location.subtract(0,0.2,0), 0);
        }
    }
}
