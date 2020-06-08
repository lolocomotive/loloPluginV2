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

        switch (args[0]) {
            case "enable":
                switch (args[1]) {
                    case "misc":
                    case "miscellaneous": {
                        String id = "miscellaneous";
                        if (!enabledModules.contains(id)) {
                            enabledModules.add(id);
                        }
                    }
                    break;
                    case "watersliding":
                    case "ws": {
                        String id = "watersliding";
                        if (!enabledModules.contains(id)) {
                            enabledModules.add(id);
                        }
                    }
                    break;
                    case "mobdiv":
                    case "mobd":
                    case "mobdiversity": {
                        String id = "mobdiversity";
                        if (!enabledModules.contains(id)) {
                            enabledModules.add(id);
                        }
                    }
                    break;
                    case "multiworld": {
                        String id = "multiworld";
                        if (!enabledModules.contains(id)) {
                            enabledModules.add(id);
                        }
                    }
                    break;
                }
                break;
            case "disable":
                switch (args[1]) {
                    case "misc":
                    case "miscellaneous": {
                        String id = "miscellaneous";
                        if (enabledModules.contains(id)) {
                            enabledModules.remove(id);
                        }
                    }
                    break;
                    case "watersliding":
                    case "ws": {
                        String id = "watersliding";
                        if (enabledModules.contains(id)) {
                            enabledModules.remove(id);
                        }
                    }
                    break;
                    case "mobdiv":
                    case "mobd":
                    case "mobdiversity": {
                        String id = "mobdiversity";
                        if (enabledModules.contains(id)) {
                            enabledModules.remove(id);
                        }
                    }
                    break;
                    case "multiworld": {
                        String id = "multiworld";
                        if (enabledModules.contains(id)) {
                            enabledModules.remove(id);
                        }
                    }
                    break;
                }
                break;


        }
        config.set("modules", enabledModules);
        PluginMain.getInstance().saveConfig();
        return true;
    }
}
