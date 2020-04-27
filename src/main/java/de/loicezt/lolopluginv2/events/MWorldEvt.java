package de.loicezt.lolopluginv2.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class MWorldEvt implements Listener {
    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        if (p.getWorld().getName().equals("world_nether")) {
            if (e.getFrom().getName().equals("survie")) {

            } else {
                p.sendMessage("§4For security reasons, you can only go to the nether from the survival world");
                p.performCommand("lobby");
            }
        }
        if (p.getWorld().getName().equals("world_the_end")) {
            if (e.getFrom().getName().equals("survie")) {

            } else {
                p.sendMessage("§4For security reasons, you can only go to the end from the survival world");
                p.performCommand("lobby");
            }
        }
        if (p.getWorld().getName().equals("world")) {
            p.setGameMode(GameMode.ADVENTURE);
        }
        if (p.getWorld().getName().equals("survie")) {
            p.setGameMode(GameMode.SURVIVAL);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent set survival");
        }
        if (p.getWorld().getName().equals("world_nether")) {
            p.setGameMode(GameMode.SURVIVAL);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent set survival");
        }
        if (p.getWorld().getName().equals("world_the_end")) {
            p.setGameMode(GameMode.SURVIVAL);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent set survival");
        }
        if (p.getWorld().getName().equals("World_" + p.getName().replace(" ", "_"))) {
            p.setGameMode(GameMode.CREATIVE);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent set survival");
        }
    }
}
