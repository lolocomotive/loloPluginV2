package de.loicezt.lolopluginv2.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdMain implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bHere are some commands provided by loloPluginV2:"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/glide&a : begin gliding  &6/unglide&a : stop gliding"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/dg&a : disable gliding  &6/eg&a : enable gliding"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/sws&a : sets watersliding speed"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/wsp&a : sets particle amount multiplier"));
            return true;
        }
        return false;
    }

}
