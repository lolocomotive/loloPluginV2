package de.loicezt.lolopluginv2.cmd.gliding;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Propulsate implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            try {
                float speed = Float.parseFloat(args[0]);
                if (speed > 100) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4The maximum speed value is 100!"));
                    return true;
                }
                if (speed < -100) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4The minimum speed value is -100!"));
                    return true;
                }
                Player player = (Player) sender;
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYEET!"));
                Vector dir = player.getLocation().getDirection();
                Vector push = new Vector();
                push.setX(dir.getX() * speed);
                push.setY(dir.getY() * speed);
                push.setZ(dir.getZ() * speed);
                player.setVelocity(push);

            } catch (Exception e) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Usage: &6/propulsate <speed>"));
            }
            return true;
        }
        return false;
    }
}
