package de.loicezt.lolopluginv2.cmd.gliding;

import de.loicezt.lolopluginv2.main.PluginMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DisableGliding implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Bukkit.broadcastMessage("Gliding disabled");
            PluginMain.gliding = false;

            return true;
        }
        return false;
    }

}
