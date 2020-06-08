package fr.Iceknith.lolopluginv2.event;

import fr.Iceknith.lolopluginv2.commands.ManHunt;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class TrackerEvent implements Listener {
    public int i;

    @EventHandler
    public void onPlayerUses(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (p.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
            if (ManHunt.hunter != null) {
                if (ManHunt.hunted != null) {
                    i = -1;
                    for (Player hunter : ManHunt.hunter) {
                        i++;
                        if (ManHunt.hunter.get(i) == p) {
                            ManHunt.hunter.get(i).setCompassTarget(ManHunt.hunted.get(i).getLocation());
                            ManHunt.hunter.get(i).sendMessage("Pointing towards " + ManHunt.hunted.get(i).getName());
                        }
                    }
                }
            }
        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        i = -1;
        for (Player hunted : ManHunt.hunted) {
            i++;
            if (hunted == event.getEntity()) {
                hunted.sendMessage("The hunter won the game");
                ManHunt.hunter.get(i).sendMessage("The hunter won the game");
                ManHunt.hunter.remove(i);
                ManHunt.hunted.remove(i);
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        for (Player hunter : ManHunt.hunted) {
            if (hunter == event.getPlayer()) {
                Objects.requireNonNull(hunter.getEquipment()).setItemInMainHand(new ItemStack(Material.COMPASS, 1));
            }
        }
    }
}
