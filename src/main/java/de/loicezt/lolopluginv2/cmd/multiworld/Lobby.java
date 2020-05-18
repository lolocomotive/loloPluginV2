package de.loicezt.lolopluginv2.cmd.multiworld;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lobby implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            try {
                if (Integer.parseInt(args[0]) == 1) {
                    Location loc = new Location(Bukkit.getWorld("world"), 155.5, 14, -193.5);
                    Player p = (Player) sender;
                    p.teleport(loc);
                    p.setGameMode(GameMode.ADVENTURE);
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent set lobby");
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {

            } catch (Exception e) {
                sender.sendMessage("Ooops, looks like something went wrong here, sorry ;(");
                e.printStackTrace();
                return true;
            }
            //return player to the lobby
            Player p = (Player) sender;
            Location loc = new Location(Bukkit.getWorld("world"), 133.5, 6, -170.5);
            p.teleport(loc);
            p.setGameMode(GameMode.ADVENTURE);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent set lobby");
        } else {
            sender.sendMessage("You must be a player to execute this command");
        }

        return true;
    }
}
