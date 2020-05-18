package de.loicezt.lolopluginv2.cmd.multiworld;

import de.loicezt.lolopluginv2.main.PluginMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class UpdateSignList implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (int i = 0; i < PluginMain.getPlayerWorlds().size(); i++) {
            Location FIRST_SIGN = new Location(Bukkit.getWorld("world"), 156, 15, -194);
            //Bukkit.getWorld("world").getBlockAt(FIRST_SIGN.getBlockX(), FIRST_SIGN.getBlockY(), FIRST_SIGN.getBlockZ() + i).setType(Material.OAK_WALL_SIGN);
            String cmd = "data merge block " +
                    String.valueOf(FIRST_SIGN.getBlockX()) + " " +
                    String.valueOf(FIRST_SIGN.getBlockY()) + " " +
                    String.valueOf(FIRST_SIGN.getBlockZ() + i) + " " +
                    "{Text1:'{\"text\":\"Serveur de\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"visit " + PluginMain.getPlayerWorlds().get(i).getName() + "\"},\"bold\":true,\"color\":\"aqua\"}'," +
                    "Text2:'{\"text\":\"" + PluginMain.getPlayerWorlds().get(i).getName() + "\",\"color\":\"dark_red\"}'," +
                    "Text3:'{\"text\":\"" + PluginMain.getPlayerWorlds().get(i).getVisibility() + "\",\"color\":\"green\"}',Text4:'{\"text\":\"==========================\"}'}";
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd);
            sender.sendMessage(String.valueOf(FIRST_SIGN.getBlockX()) + "," + String.valueOf(FIRST_SIGN.getBlockY()) + "," + String.valueOf(FIRST_SIGN.getBlockZ() + i));
        }
        return true;
    }
}