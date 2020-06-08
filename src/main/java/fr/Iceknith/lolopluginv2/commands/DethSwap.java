package fr.Iceknith.lolopluginv2.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DethSwap implements CommandExecutor {

    public static List<Player> p1 = new ArrayList<>();
    public static List<Player> p2 = new ArrayList<>();


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] args) {
        try {
            if (Bukkit.getPlayerExact(args[0]) != null) {
                p1.add(Bukkit.getPlayerExact(args[0]));
                if (Bukkit.getPlayerExact(args[1]) != null) {
                    p2.add(Bukkit.getPlayerExact(args[1]));
                }
                Bukkit.broadcastMessage("The deth swap has began");
            }


        } catch (ArrayIndexOutOfBoundsException e) {
            commandSender.sendMessage("Not enough arguments ");
            commandSender.sendMessage("usage /swap <player 1> <player 2>");
        } catch (Exception e) {
            commandSender.sendMessage("an error has occured");
            commandSender.sendMessage("usage /swap <player 1> <player 2>");

        }
        return true;
    }
}
