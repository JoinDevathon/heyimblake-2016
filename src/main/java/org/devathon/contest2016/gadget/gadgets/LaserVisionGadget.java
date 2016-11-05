package org.devathon.contest2016.gadget.gadgets;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.gadget.Gadget;
import org.devathon.contest2016.localization.Language;
import org.devathon.contest2016.threads.LaserEyesParticleSpawnThread;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class LaserVisionGadget extends Gadget {



    @Override
    public String name(Language language) {
        return language.getTranslation("gadget.laservision.name");
    }

    @Override
    public ItemStack itemStack() {
        return new ItemStack(Material.INK_SACK, 1, (short) 1);
    }

    @Override
    public double usageDelay() {
        return 5;
    }

    @Override
    public void run(Player player) {
        ArmorStand armorStand = (ArmorStand) player.getLocation().getWorld().spawnEntity(player.getEyeLocation(), EntityType.ARMOR_STAND);
        armorStand.setGliding(true);
        armorStand.setVisible(false);
        armorStand.setSmall(true);
        armorStand.setCustomNameVisible(false);
        armorStand.setVelocity(player.getEyeLocation().getDirection().multiply(1.3));
        LaserEyesParticleSpawnThread.armorStands.add(armorStand);
        player.setGameMode(GameMode.SPECTATOR);
        new BukkitRunnable() {
            @Override
            public void run() {
                LaserEyesParticleSpawnThread.armorStands.remove(armorStand);
                armorStand.remove();
                player.setGameMode(GameMode.CREATIVE);
            }
        }.runTaskLater(DevathonPlugin.getInstance(), 3*20L);
    }
}
