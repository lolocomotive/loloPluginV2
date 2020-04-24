package de.loicezt.lolopluginv2.events;

import de.loicezt.lolopluginv2.main.PluginMain;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class WsEventsEntity implements Runnable, Listener {
    public static float speed = 10f;
    public static List<Entity> watersliding = new ArrayList<Entity>();
    World world = Bukkit.getWorld("world");

    public static void addEntity(Entity entity) {
        Vector dir = entity.getLocation().getDirection();
        Vector push = new Vector();
        if (!watersliding.contains(entity)) {
            if (PluginMain.isDebug())
                entity.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYou are now watersliding!"));
            watersliding.add(entity);
        } else {
            push.setX(dir.getX() * speed * .009);
            push.setY(0);
            push.setZ(dir.getZ() * speed * .009);
        }
        if (entity instanceof Vehicle) {
            if (entity.getPassengers().size() != 0) {
                push.setX(dir.getX() * speed * .1);
                push.setY(0);
                push.setZ(dir.getZ() * speed * .1);
            }
        }
        entity.setVelocity(entity.getVelocity().add(push));
    }

    public static List<Entity> getPlayers() {
        return watersliding;
    }

    public static void removeEntity(Entity entity) {

        if (watersliding.contains(entity)) {
            if (PluginMain.isDebug())
                entity.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You are not watersliding anymore!"));
            entity.setVelocity(new Vector(0, 0, 0));
            watersliding.remove(entity);
            if (entity instanceof Player) ((Player) entity).setGliding(false);
        }
    }

    @EventHandler
    public void onPlayerGlide(EntityToggleGlideEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (watersliding.contains(player)) {
                e.setCancelled(true);
            }
        }
        if (PluginMain.isGliding()) e.setCancelled(true);
    }

    public void run() {
        List<Entity> el = world.getEntities();
        for (Entity entity : el) {
            Block block = entity.getWorld().getBlockAt(entity.getLocation());
            if (block.getType() == Material.QUARTZ_SLAB) {
                if (block.getBlockData() instanceof Waterlogged) {
                    Waterlogged w = (Waterlogged) block.getBlockData();
                    if (w.isWaterlogged()) {
                        addEntity(entity);
                        if (entity instanceof Player) {
                            Player p = (Player) entity;
                            if (p.getVehicle() == null) {
                                p.setGliding(true);
                            } else {
                                removeEntity(entity);
                            }
                        }
                        world.spawnParticle(Particle.FALLING_WATER, entity.getLocation(), Math.round(10f * PluginMain.getWsPartMult()), .5, .5, .5);
                        world.spawnParticle(Particle.SNOWBALL, entity.getLocation(), Math.round(0.5f * PluginMain.getWsPartMult()), 0.0, 0.0, 0.0);
                        world.spawnParticle(Particle.WATER_BUBBLE, entity.getLocation(), Math.round(10f * PluginMain.getWsPartMult()), .2, .1, .2, .5);
                    }
                } else {
                    removeEntity(entity);

                }
            } else {
                removeEntity(entity);
            }
        }
    }
}
