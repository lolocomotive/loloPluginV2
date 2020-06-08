package fr.Iceknith.lolopluginv2.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ManHunt implements CommandExecutor {
    public static List<Player> hunter = new ArrayList<>();
    public static List<Player> hunted = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        try {
            if (Bukkit.getPlayerExact(args[0]) != null) {
                hunter.add(Bukkit.getPlayerExact(args[0]));
                if (Bukkit.getPlayerExact(args[1]) != null) {
                    hunted.add(Bukkit.getPlayerExact(args[1]));
                }
                Bukkit.broadcastMessage("The manhunt has began");
            }


        } catch (ArrayIndexOutOfBoundsException e) {
            commandSender.sendMessage("Not enough arguments ");
            commandSender.sendMessage("usage /manh <player hunter> <player hunted>");
        } catch (Exception e) {
            commandSender.sendMessage("an error has occurred");
            commandSender.sendMessage("usage /manh <player hunter> <player hunted>");

        }
        return true;
    }
}
