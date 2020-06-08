package de.loicezt.lolopluginv2.cmd;

import de.loicezt.lolopluginv2.main.PluginMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class Misc implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            switch (args[0]) {
                case "enable":
                    switch (args[1]) {
                        case "annoy":
                            PluginMain.setAnnoy(true);
                            break;
                        case "debug":
                            PluginMain.setDebug(true);
                            break;
                        case "gliding":
                            PluginMain.setGliding(true);
                            break;
                        default:
                            sender.sendMessage("could not find the Misc feature you were looking for!");
                            return false;
                    }
                    break;
                case "disable":
                    switch (args[1]) {
                        case "annoy":
                            PluginMain.setAnnoy(false);
                            break;
                        case "debug":
                            PluginMain.setDebug(false);
                            break;
                        case "gliding":
                            PluginMain.setGliding(false);
                            break;
                        default:
                            sender.sendMessage("could not find the Misc feature you were looking for!");
                            return false;
                    }
                    break;
                default:
                    sender.sendMessage("could not find the option you were looking for!");
                    return false;
            }

        } catch (IndexOutOfBoundsException ex) {
            sender.sendMessage("Not enough arguments !");
            return false;
        }
        sender.sendMessage(args[0] + "d feature " + args[1]);
        return true;
    }
}
