package de.loicezt.lolopluginv2.cmd.multiworld;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lobby implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            //return player to the lobby
            Player p = (Player) sender;
            Location loc = new Location(Bukkit.getWorld("World"), 133.5, 6, -170.5);
            p.teleport(loc);

        } else {
            sender.sendMessage("You must be a player to execute this command");
        }

        return true;
    }
}
