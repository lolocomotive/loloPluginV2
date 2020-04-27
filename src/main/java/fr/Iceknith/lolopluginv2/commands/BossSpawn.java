package fr.Iceknith.lolopluginv2.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BossSpawn implements CommandExecutor {
    public static boolean isBossSpawn;
    public static int Frequence;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        try {
            isBossSpawn = Boolean.parseBoolean(args[0]);
            Frequence = Integer.parseInt(args[1]);
            commandSender.sendMessage("the mob diversity mod has been set to: " + String.valueOf(isBossSpawn) + "with a frenquence of " + Frequence);
        } catch (ArrayIndexOutOfBoundsException e) {
            commandSender.sendMessage("Not enough arguments ");
            commandSender.sendMessage("usage /bs <true or false> <frequence>");
        } catch (Exception e) {
            commandSender.sendMessage("an error has occured");
            commandSender.sendMessage("usage /bs <true or false> <frequence>");

        }
        return true;
    }


}

