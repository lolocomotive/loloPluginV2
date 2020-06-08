package de.loicezt.lolopluginv2.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class GamemodeEvents implements Runnable {
    public void run() {
        for (World w : Bukkit.getWorlds()) {
            for (Player p : w.getPlayers()) {
                if (p.getWorld().getName().equals("world")) {
                    if (!p.getGameMode().equals(GameMode.ADVENTURE)) {
                        p.setGameMode(GameMode.ADVENTURE);
                    }
                }
                if (p.getWorld().getName().equals("survie")) {
                    if (!p.getGameMode().equals(GameMode.SURVIVAL)) {
                        p.setGameMode(GameMode.SURVIVAL);
                    }
                }
                if (p.getWorld().getName().equals("world_nether")) {
                    if (!p.getGameMode().equals(GameMode.SURVIVAL)) {
                        p.setGameMode(GameMode.SURVIVAL);
                    }
                }
                if (p.getWorld().getName().equals("world_the_end")) {
                    if (!p.getGameMode().equals(GameMode.SURVIVAL)) {
                        p.setGameMode(GameMode.SURVIVAL);
                    }
                }
                if (p.getWorld().getName().equals("FBC")) {
                    if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                        p.setGameMode(GameMode.CREATIVE);
                    }
                }
                if (p.getWorld().getName().startsWith("World_")) {
                    if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                        p.setGameMode(GameMode.CREATIVE);
                    }
                }
            }
        }
    }
}
