package de.loicezt.lolopluginv2.cmd.gliding;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class UnGlide implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;
            player.sendMessage("Y'all not be Gliding anymore");
            player.setGliding(false);

            return true;
        }
        return false;
    }


}
