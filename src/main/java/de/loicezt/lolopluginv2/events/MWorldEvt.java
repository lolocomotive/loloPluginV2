package de.loicezt.lolopluginv2.events;

import de.loicezt.lolopluginv2.main.PluginMain;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class MWorldEvt implements Listener {
    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        BukkitScheduler s = Bukkit.getScheduler();
        switch (p.getWorld().getName()) {
            case "world_nether":
                if (e.getFrom().getName().equals("survie")) {
                    s.runTaskLater(PluginMain.getInstance(), new Runnable() {
                        public void run() {
                            p.setGameMode(GameMode.SURVIVAL);
                        }
                    }, 1);
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent set survival");
                } else {
                    p.sendMessage("§4For security reasons, you can only go to the nether from the survival world");
                    p.performCommand("lobby");
                }
                break;

            case "world_the_end":
                if (e.getFrom().getName().equals("survie")) {
                    s.runTaskLater(PluginMain.getInstance(), new Runnable() {
                        public void run() {
                            p.setGameMode(GameMode.SURVIVAL);
                        }
                    }, 1);
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent set survival");
                } else {
                    p.sendMessage("§4For security reasons, you can only go to the end from the survival world");
                    p.performCommand("lobby");
                }
                break;
            case "world":
                s.runTaskLater(PluginMain.getInstance(), new Runnable() {
                    public void run() {
                        p.setGameMode(GameMode.ADVENTURE);
                    }
                }, 1);
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent set lobby");
                break;
            case "survie":
                s.runTaskLater(PluginMain.getInstance(), new Runnable() {
                    public void run() {
                        p.setGameMode(GameMode.SURVIVAL);
                    }
                }, 1);
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent set survival");
                break;

            case "FBC":
                s.runTaskLater(PluginMain.getInstance(), new Runnable() {
                    public void run() {
                        p.setGameMode(GameMode.CREATIVE);
                    }
                }, 1);
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent set default");
                break;

        }
        if (p.getWorld().getName().startsWith("World_")) {
            s.runTaskLater(PluginMain.getInstance(), new Runnable() {
                public void run() {
                    p.setGameMode(GameMode.CREATIVE);
                }
            }, 1);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent set default");
        }

    }
}
