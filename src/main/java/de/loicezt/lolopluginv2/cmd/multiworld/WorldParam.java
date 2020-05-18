package de.loicezt.lolopluginv2.cmd.multiworld;

import de.loicezt.lolopluginv2.main.PluginMain;
import de.loicezt.lolopluginv2.types.PlayerWorld;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldParam implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                switch (args[0]) {
                    case "visibility":
                    case "v":
                        for (PlayerWorld pw : PluginMain.getPlayerWorlds()) {
                            if (pw.getName().equals(sender.getName())) {
                                pw.setVisibility(args[1]);
                                sender.sendMessage("Setting the visibility of your World to:" + args[1]);
                                PluginMain.getInstance().getConfig().set("playerWorldData." + pw.getName() + ".visibility", args[1]);
                                PluginMain.getInstance().saveConfig();
                            }
                        }
                        break;
                    case "message":
                    case "m":
                        for (PlayerWorld pw : PluginMain.getPlayerWorlds()) {
                            if (pw.getName().equals(sender.getName())) {
                                pw.setWelcomeMessage(args[1]);
                                sender.sendMessage("Setting the Welcome message of your World to:" + args[1]);
                                PluginMain.getInstance().getConfig().set("playerWorldData." + pw.getName() + ".welcomeMessage", args[1]);
                                PluginMain.getInstance().saveConfig();
                            }
                        }

                }
            } catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage("That's not how the command works !");
            }
        } else {
            sender.sendMessage("§4Sorry, you must be a player to execute this command!");
        }

        return true;
    }
}
