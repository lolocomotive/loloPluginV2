package fr.Iceknith.lolopluginv2;

import fr.Iceknith.lolopluginv2.commands.DeathSwap;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.List;


public class SwapHandler implements Runnable, Listener {
    public static List<Integer> counter = new ArrayList<>();
    public Location Playerp;

    @Override
    public void run() {
        if (DeathSwap.p1 != null) {
            while (counter.size() + 1 == DeathSwap.p1.size()) {
                counter.add(0);
            }
            int i = -1;
            for (int smthing : counter) {
                i++;
                if (counter.get(i) == 6000) {
                    Playerp = DeathSwap.p2.get(i).getLocation();
                    DeathSwap.p2.get(i).teleport(DeathSwap.p1.get(i).getLocation());
                    DeathSwap.p1.get(i).teleport(Playerp);
                    DeathSwap.p1.get(i).sendMessage("Wooosh, you swapped place with the other player");
                    DeathSwap.p2.get(i).sendMessage("Wooosh, you swapped place with the other player");
                    counter.set(i, 0);
                }
                counter.set(i, counter.get(i) + 1);
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        int i = -1;
        int d = 0;
        for (Player p1 : DeathSwap.p1) {
            i++;
            if (p1 == event.getEntity()) {
                p1.sendMessage("The player2 won the game");
                DeathSwap.p2.get(i).sendMessage("The player2 won the game");
                d = 1;
            }
        }
        int i2 = -1;
        for (Player p2 : DeathSwap.p2) {
            i2++;
            if (p2 == event.getEntity()) {
                p2.sendMessage("The player1 won the game");
                DeathSwap.p1.get(i2).sendMessage("The player1 won the game");
                d = 2;
            }
        }
        if (i == 1) {
            DeathSwap.p1.remove(i);
            DeathSwap.p2.remove(i);
            counter.remove(i);
        }
        if (i == 2) {
            DeathSwap.p1.remove(i2);
            DeathSwap.p2.remove(i2);
            counter.remove(i2);
        }
    }
}
