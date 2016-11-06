package org.devathon.contest2016.gadget.gadgets;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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
        new LaserEyesParticleSpawnThread(player.getEyeLocation()).runTaskTimer(DevathonPlugin.getInstance(), 0, 5L);
        player.playSound(player.getLocation(), Sound.ITEM_BOTTLE_FILL_DRAGONBREATH, 10, 1);
    }
}
