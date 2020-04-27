package de.loicezt.lolopluginv2.cmd.multiworld;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Survival implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            //teleport player to the common survival server
            Player p = (Player) sender;

            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent set survival");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "speed fly 1 " + p.getName());
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "speed walk 1 " + p.getName());
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "effect clear " + p.getName());
            Location loc = new Location(Bukkit.getWorld("survie"), -208, 67, -96);
            p.teleport(loc);
            p.setGameMode(GameMode.SURVIVAL);
            p.setFlying(false);

        } else {
            sender.sendMessage("You must be a player to execute this command");
        }

        return true;
    }
}
