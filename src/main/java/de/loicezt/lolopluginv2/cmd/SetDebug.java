package de.loicezt.lolopluginv2.cmd;

import de.loicezt.lolopluginv2.main.PluginMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetDebug implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                boolean debug = Boolean.parseBoolean(args[0]);

                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&aSetting debug mode to : &e" + String.valueOf(debug)));
                PluginMain.setDebug(debug);

            } catch (Exception e) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Usage: &6/setDebug <true or false>"));
            }
            return true;
        }
        return false;
    }

}
