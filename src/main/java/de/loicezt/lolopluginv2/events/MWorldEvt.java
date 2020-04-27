package de.loicezt.lolopluginv2.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class MWorldEvt implements Listener {
    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent e) {
        if (e.getPlayer().getWorld().getName().equals("world_nether")) {
            if (e.getFrom().getName().equals("survie")) {

            } else {
                e.getPlayer().sendMessage("§4Error, you can only go to the nether from the survival world");
                e.getPlayer().performCommand("lobby");
            }
        }
        if (e.getPlayer().getWorld().getName().equals("world_the_end")) {
            if (e.getFrom().getName().equals("survie")) {

            } else {
                e.getPlayer().sendMessage("§4Error, you can only go to the nether from the survival world");
                e.getPlayer().performCommand("lobby");
            }
        }
    }
}
