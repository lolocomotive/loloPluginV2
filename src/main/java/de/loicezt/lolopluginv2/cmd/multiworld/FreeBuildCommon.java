package de.loicezt.lolopluginv2.cmd.multiworld;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreeBuildCommon implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            //teleport player to the common freebuild server
            Player p = (Player) sender;
            Location loc = new Location(Bukkit.getWorld("FBC"), 180, 6, -82);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent set default");
            p.teleport(loc);
            p.setGameMode(GameMode.CREATIVE);
        } else {
            sender.sendMessage("You must be a player to execute this command");
        }

        return true;
    }
}
