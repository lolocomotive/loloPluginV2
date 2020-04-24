package de.loicezt.lolopluginv2.cmd;

import de.loicezt.lolopluginv2.types.SpawnQueueItm;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Multispawn implements CommandExecutor, Runnable {

    private static List<SpawnQueueItm> list = new ArrayList<>();

    public static List<SpawnQueueItm> getList() {
        return list;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            EntityType t = EntityType.valueOf(args[0].toUpperCase());
            int total = Integer.parseInt(args[1]);

            int step = Integer.parseInt(args[2]);

            Player p = (Player) sender;
            list.add(new SpawnQueueItm(p, step, total, t));

            return true;
        }
        return false;
    }

    public void run() {
        //Bukkit.broadcastMessage(getList().toString());
        for (SpawnQueueItm itm : getList()) {
            if (itm.tick()) {
                itm.getPlayer().sendMessage("Done spawning entities!");
                list.remove(itm);

            }
        }
    }
}
