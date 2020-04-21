package de.loicezt.lolopluginv2.cmd.ws;

import de.loicezt.lolopluginv2.events.WsEventsEntity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWaterslidingParticleAmountMultiplier implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                int amount = Integer.parseInt(args[0]);
                if (amount > 100) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4The maximum amount is 100!"));
                    return true;
                }
                if (amount < 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4The amount cannot be negative!"));
                    return true;
                }
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&aSetting particle amount multiplier to : &e" + String.valueOf(amount)));
                WsEventsEntity.particleAmountMultiplier = amount;

            } catch (Exception e) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Usage: &6/wsp <amount>"));
            }
            return true;
        }
        return false;
    }

}
