package de.loicezt.lolopluginv2.cmd.ws;

import de.loicezt.lolopluginv2.events.WsEventsEntity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWaterslidingSpeed implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                float speed = Float.parseFloat(args[0]);
                if (speed > 100) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4The maximum speed value is 100!"));
                    return true;
                }
                if (speed < 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4The speed value cannot be negative!"));
                    return true;
                }
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&aSetting sliding speed to : &e" + Float.valueOf(args[0])));
                WsEventsEntity.speed = speed;

            } catch (Exception e) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Usage: &6/sws <speed>"));
            }
            return true;
        }
        return false;
    }

}
