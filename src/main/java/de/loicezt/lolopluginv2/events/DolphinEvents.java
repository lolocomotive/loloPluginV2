package de.loicezt.lolopluginv2.events;

import de.loicezt.lolopluginv2.types.DolphinRideItm;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.List;

public class DolphinEvents implements Listener, Runnable {

    public static List<DolphinRideItm> dolphins = new ArrayList<DolphinRideItm>();


    @EventHandler
    public void onDolphinDie(EntityDeathEvent evt) {
        if (evt.getEntity() instanceof Dolphin) {
            for (int i = 0; i < dolphins.size(); i++) {
                if (dolphins.get(i).getDolphin().equals((Dolphin) evt.getEntity())) {
                    Bukkit.broadcastMessage("A dolphin which was on the list just died!" + String.valueOf(i));
                    dolphins.get(i).getHorse().remove();
                    dolphins.remove(i);
                    i--;
                }
            }
        }
    }

    @Override
    public void run() {
        Player p = Bukkit.getPlayer("lolocomotive");
        //p.sendMessage(dolphins.toString());
        for (DolphinRideItm d : dolphins) {
            //p.sendMessage("a dolphin is movin");

            Location point = d.getHorse().getLocation();
            d.getDolphin().teleport(point);
            d.getDolphin().getLocation().setDirection(d.getDolphin().getLocation().getDirection());
        }
    }
}
