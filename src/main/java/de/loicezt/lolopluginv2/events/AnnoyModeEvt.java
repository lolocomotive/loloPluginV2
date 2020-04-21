package de.loicezt.lolopluginv2.events;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AnnoyModeEvt implements Listener {

    public static boolean annoy = false;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        if (annoy) {
            Player player = e.getPlayer();
            World world = player.getWorld();
            world.getBlockAt(player.getLocation().getBlockX(),
                    player.getLocation().getBlockY() - 1,
                    player.getLocation().getBlockZ()
            ).setType(Material.DIRT);
        }

    }

}
