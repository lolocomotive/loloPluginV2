//package de.loicezt.lolopluginv2.events;
//
//import de.loicezt.lolopluginv2.main.PluginMain;
//import org.bukkit.ChatColor;
//import org.bukkit.Material;
//import org.bukkit.Particle;
//import org.bukkit.World;
//import org.bukkit.block.Block;
//import org.bukkit.block.data.Waterlogged;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.entity.EntityToggleGlideEvent;
//import org.bukkit.event.player.PlayerMoveEvent;
//import org.bukkit.util.Vector;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class WsEvents implements Listener {
//    public static float speed = 10f;
//    public static List<Player> watersliding = new ArrayList<Player>();
//    public static boolean debug = false;
//    public static int particleAmountMultiplier = 10;
//
//    public static void addPlayer(Player player) {
//        Vector dir = player.getLocation().getDirection();
//        Vector push = new Vector();
//        if (!watersliding.contains(player)) {
//            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYou are now watersliding!"));
//            watersliding.add(player);
//        } else {
//            push.setX(dir.getX() * speed * .009);
//            push.setY(0);
//            push.setZ(dir.getZ() * speed * .009);
//        }
//        player.setVelocity(player.getVelocity().add(push));
//    }
//
//    public static List<Player> getPlayers() {
//        return watersliding;
//    }
//
//    public static void removePlayer(Player player) {
//
//        if (watersliding.contains(player)) {
//            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You are not watersliding anymore!"));
//            player.setVelocity(new Vector(0, 0, 0));
//            watersliding.remove(player);
//            player.setGliding(false);
//        }
//    }
//
//    @EventHandler
//    public void onPlayerGlide(EntityToggleGlideEvent e) {
//        if (e.getEntity() instanceof Player) {
//            Player player = (Player) e.getEntity();
//            if (watersliding.contains(player)) {
//                e.setCancelled(true);
//            }
//        }
//        if (PluginMain.gliding) e.setCancelled(true);
//    }
//
//    @EventHandler
//    public void onPlayerMove(PlayerMoveEvent evt) {
//        Player player = evt.getPlayer();
//        Vector dir = player.getLocation().getDirection();
//
//        if (debug) {
//            player.sendMessage("=====================================================");
//            player.sendMessage("Direction X:" + String.valueOf(dir.getX()));
//            player.sendMessage("Direction Y:" + String.valueOf(dir.getY()));
//            player.sendMessage("Direction Z:" + String.valueOf(dir.getZ()));
//            player.sendMessage("=====================================================");
//            player.sendMessage("");
//            player.sendMessage("BlockData:" + player.getWorld().getBlockAt(player.getLocation()).getBlockData().getAsString());
//            player.sendMessage("");
//        }
//
//        Block block = player.getWorld().getBlockAt(player.getLocation());
//        if (block.getBlockData() instanceof Waterlogged) {
//            Waterlogged w = (Waterlogged) block.getBlockData();
//            if (debug) player.sendMessage("Waterlogged:" + String.valueOf(w.isWaterlogged()));
//            if (block.getType() == Material.QUARTZ_SLAB && w.isWaterlogged() /*&& (player.isSprinting() || player.isSwimming() || player.isGliding())*/) {
//                addPlayer(player);
//                player.setGliding(true);
//                World world = player.getWorld();
//
//                world.spawnParticle(Particle.FALLING_WATER, player.getLocation(), (int) 10 * particleAmountMultiplier, .5, .5, .5);
//                world.spawnParticle(Particle.SNOWBALL, player.getLocation(), (int) 0.5 * particleAmountMultiplier, 0, 0, 0);
//                world.spawnParticle(Particle.WATER_BUBBLE, player.getLocation(), (int) 10 * particleAmountMultiplier, .2, .1, .2, .5);
//                return;
//            }
//        } else {
//            if (debug) player.sendMessage("");
//        }
//        removePlayer(player);
//
//    }
//
//}
