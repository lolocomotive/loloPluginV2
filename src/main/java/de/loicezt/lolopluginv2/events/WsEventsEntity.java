package de.loicezt.lolopluginv2.events;

import de.loicezt.lolopluginv2.main.PluginMain;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

//Hello , Here I am!

public class WsEventsEntity implements Runnable, Listener {
    public static float speed = 10f;
    public static List<Entity> watersliding = new ArrayList<Entity>();
    public static boolean debug = false;
    public static int particleAmountMultiplier = 10;

    public static void addEntity(Entity entity) {
        Vector dir = entity.getLocation().getDirection();
        Vector push = new Vector();
        if (!watersliding.contains(entity)) {
        entity.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYou are now watersliding!"));
        watersliding.add(entity);
        } else {
        push.setX(dir.getX() * speed * .009);
        push.setY(0);
        push.setZ(dir.getZ() * speed * .009);
        }
        entity.setVelocity(entity.getVelocity().add(push));
    }

    public static List<Entity> getPlayers() {
        return watersliding;
    }

    public static void removeEntity(Entity entity) {

        if (watersliding.contains(entity)) {
            entity.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You are not watersliding anymore!"));
            entity.setVelocity(new Vector(0, 0, 0));
            watersliding.remove(entity);
            if(entity instanceof Player)((Player) entity).setGliding(false);
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
        if (PluginMain.gliding) e.setCancelled(true);
    }

    @EventHandler
    public void run() {
        World world = Bukkit.getWorld("world");
        if (debug) Bukkit.broadcastMessage("=====================================================");
        @SuppressWarnings("Null")
        List<Entity> el = world.getEntities();
        for (int i = 0; i < el.size(); i++) {
            if (debug) Bukkit.broadcastMessage("Entity " + i + " :" + el.get(i).getName());
            Block block = el.get(i).getWorld().getBlockAt(el.get(i).getLocation());

            //Bukkit.broadcastMessage(block.toString());

            if (block.getBlockData() instanceof Waterlogged) {
                if(debug)Bukkit.broadcastMessage("Entity-Wloggable:" + el.get(i).getName());
                Waterlogged w = (Waterlogged) block.getBlockData();
                if (block.getType() == Material.QUARTZ_SLAB && w.isWaterlogged() /*&& (entity.isSprinting() || entity.isSwimming() || entity.isGliding())*/) {
                    addEntity(el.get(i));
                    if(debug)Bukkit.broadcastMessage("Entity-Wlogged:" + el.get(i).getName());
                    if (el.get(i) instanceof Player) ((Player)el.get(i)).setGliding(true);



                    world.spawnParticle(Particle.FALLING_WATER, el.get(i).getLocation(), 10 * particleAmountMultiplier, .5, .5, .5);
                    world.spawnParticle(Particle.SNOWBALL, el.get(i).getLocation(), (int) 0.5 * particleAmountMultiplier, 0, 0, 0);
                    world.spawnParticle(Particle.WATER_BUBBLE, el.get(i).getLocation(), 10 * particleAmountMultiplier, .2, .1, .2, .5);
                } else {
                    removeEntity(el.get(i));

                }
            } else {
                if (debug) el.get(i).sendMessage("");
                removeEntity(el.get(i));
            }


        }

    }
}
