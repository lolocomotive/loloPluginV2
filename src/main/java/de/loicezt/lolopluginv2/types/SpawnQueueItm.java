package de.loicezt.lolopluginv2.types;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SpawnQueueItm {
    private Player player;
    private int step;
    private int remaining;
    private EntityType type;
    private World world;
    private Location loc;

    public SpawnQueueItm(Player player, int step, int remaining, EntityType type) {
        this.player = player;
        this.step = step;
        this.remaining = remaining;
        this.type = type;
        world = player.getWorld();
        loc = player.getLocation();
    }

    public boolean tick() {
        for (int i = 0; i < step; i++) {
            if (i == remaining) return true;
            world.spawnEntity(loc, type);
        }
        remaining -= step;
        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }
}
