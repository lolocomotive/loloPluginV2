package de.loicezt.lolopluginv2.events;

import de.loicezt.lolopluginv2.main.PluginMain;
import de.loicezt.lolopluginv2.types.PlayerWorld;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

import java.util.ArrayList;

public class ServerEvents implements Listener {
    @EventHandler

    public void onServerLoad(ServerLoadEvent evt) {
        FileConfiguration config = PluginMain.getInstance().getConfig();
        ArrayList<String> players = new ArrayList<>();
        for (World w : Bukkit.getWorlds()) {
            if (w.getName().contains("World_")) {
                players.add(w.getName().substring(6));
                PlayerWorld playerWorld = new PlayerWorld(w);
                if (config.get("playerWorldData." + w.getName().substring(6) + ".visibility") != null) {
                    playerWorld.setVisibility((String) config.get("playerWorldData." + w.getName().substring(6) + ".visibility"));
                } else {
                    playerWorld.setVisibility("PRIVATE");
                    config.set("playerWorldData." + w.getName().substring(6) + ".visibility", "PRIVATE");
                }
                PluginMain.getPlayerWorlds().add(playerWorld);
            }
        }
        PluginMain.getInstance().saveConfig();
        System.out.println("[loloPluginV2]§e playerWorlds config init done !");
        System.out.println(PluginMain.getPlayerWorlds());
    }
}
