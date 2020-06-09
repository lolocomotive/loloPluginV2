package de.loicezt.lolopluginv2.cmd;

import de.loicezt.lolopluginv2.main.PluginMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Module implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration config = PluginMain.getInstance().getConfig();
        List<String> enabledModules = (ArrayList<String>) config.getList("modules");
        String id = "";

        switch (args[0]) {
            case "enable":
                switch (args[1]) {

                    case "misc":
                    case "miscellaneous": {
                        id = "miscellaneous";
                    }
                    break;
                    case "watersliding":
                    case "ws": {
                        id = "watersliding";
                    }
                    break;
                    case "mobdiv":
                    case "mobd":
                    case "mobdiversity": {
                        id = "mobdiversity";
                    }
                    break;
                    case "multiworld": {
                        id = "multiworld";
                    }
                    break;
                    case "minigames": {
                        id = "minigames";
                    }
                    break;

                }
                if (!enabledModules.contains(id)) {
                    enabledModules.add(id);
                }
                sender.sendMessage("Module §e" + args[1] + "§f will be enabled at next reload/startup");
                break;
            case "disable":
                switch (args[1]) {
                    case "misc":
                    case "miscellaneous": {
                        id = "miscellaneous";
                    }
                    break;
                    case "watersliding":
                    case "ws": {
                        id = "watersliding";
                    }
                    break;
                    case "mobdiv":
                    case "mobd":
                    case "mobdiversity": {
                        id = "mobdiversity";
                    }
                    break;
                    case "multiworld": {
                        id = "multiworld";
                    }
                    break;
                    case "minigames": {
                        id = "minigames";
                    }
                    break;
                }
                if (enabledModules.contains(id)) {
                    enabledModules.remove(id);
                }
                sender.sendMessage("Module §e" + args[1] + "§f will be disabled at next reload/startup");
                break;


        }
        config.set("modules", enabledModules);
        PluginMain.getInstance().saveConfig();
        return true;
    }
}
