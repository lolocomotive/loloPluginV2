package de.loicezt.lolopluginv2.cmd;

import de.loicezt.lolopluginv2.events.AnnoyModeEvt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//import de.loicezt.lolopluginv2.events.WsEvents;

public class SetAnnoy implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                boolean annoy = Boolean.parseBoolean(args[0]);

                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&aSetting annoy mode to : &e" + String.valueOf(annoy)));
                AnnoyModeEvt.annoy = annoy;

            } catch (Exception e) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Usage: &6/setannoy <true or false>"));
            }
            return true;
        }
        return false;
    }
}
