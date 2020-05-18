package de.loicezt.lolopluginv2.cmd.multiworld;

import de.loicezt.lolopluginv2.main.PluginMain;
import de.loicezt.lolopluginv2.types.PlayerWorld;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Visit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            for (PlayerWorld w : PluginMain.getPlayerWorlds()) {
                if (w.getName().equals(args[0])) {
                    if (w.getVisibility().equals("PUBLIC")) {
                        Location loc = new Location(w.getWorld(), 0, 10, 0);
                        ((Player) sender).teleport(loc);
                        return true;
                    } else {
                        sender.sendMessage("§4World is not public");
                        return true;
                    }
                }
            }
            sender.sendMessage("§4No such world found!");
        } else {
            sender.sendMessage("§4You must be a player to execute this command!");
        }
        return true;
    }
}
