package org.devathon.contest2016.utils;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

/**
 * Created by heyimblake on 11/5/2016.
 *
 * @author heyimblake
 *         https://heyimblake.me
 */
public class Hologram {
    private String text;
    private ArmorStand armorStand;

    public Hologram(String text) {
        this.text = text;
    }

    public void spawn(Location location) {
        if (this.armorStand != null)
            despawn();
        this.armorStand = location.getWorld().spawn(location, ArmorStand.class);
        setAttributes();
    }

    public void setText(String text) {
        this.text = text;
        setAttributes();
    }

    public void despawn() {
        this.armorStand.remove();
    }

    private void setAttributes() {
        this.armorStand.setGravity(false);
        this.armorStand.setVisible(false);
        this.armorStand.setCustomName(this.text);
        this.armorStand.setCustomNameVisible(true);
        this.armorStand.setInvulnerable(true);
    }
}
