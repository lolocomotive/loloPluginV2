package de.loicezt.lolopluginv2.cmd.multiworld;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AddMyWorld implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            String wName = "World_" + p.getName();
            wName = wName.replace(" ", "_");
            MultiverseCore core = JavaPlugin.getPlugin(MultiverseCore.class);
            MVWorldManager mgr = core.getMVWorldManager();
            MultiverseWorld parentWorld = mgr.getMVWorld("world");
            if (!mgr.isMVWorld(wName)) {
                p.sendMessage("Creating your world...");
                if (mgr.addWorld(
                        wName,
                        World.Environment.NORMAL,
                        "", WorldType.FLAT,
                        false,
                        null,
                        parentWorld.getAdjustSpawn())) {
                    p.sendMessage("World created successfully");
                } else {
                    p.sendMessage("World creation failed");
                    return true;
                }
            }
            Location loc = new Location(Bukkit.getWorld(wName), 0, 10, 0);
            p.teleport(loc);

        } else {
            sender.sendMessage("You must be a player to execute this command");
        }

        return true;
    }
}
