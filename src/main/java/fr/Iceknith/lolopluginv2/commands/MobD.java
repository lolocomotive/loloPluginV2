package fr.Iceknith.lolopluginv2.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MobD implements CommandExecutor {

    public static boolean isMobD;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        try {
            isMobD = Boolean.parseBoolean(args[0]);
            commandSender.sendMessage("the mob diversity mod has been set to: " + String.valueOf(isMobD));
        } catch (ArrayIndexOutOfBoundsException e) {
            commandSender.sendMessage("Not enough arguments ");
            commandSender.sendMessage("usage /mobdiv <true or false> ");
        } catch (Exception e) {
            commandSender.sendMessage("an error has occured");
            commandSender.sendMessage("usage /mobdiv <true or false> ");

        }
        return true;
    }
}
