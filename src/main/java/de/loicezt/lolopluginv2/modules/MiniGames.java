package de.loicezt.lolopluginv2.modules;


import de.loicezt.lolopluginv2.types.Module;
import fr.Iceknith.lolopluginv2.commands.DeathSwap;
import fr.Iceknith.lolopluginv2.commands.ManHunt;
import org.bukkit.plugin.java.JavaPlugin;

public class MiniGames implements Module {
    String type = "minigames";

    public void enable(JavaPlugin instance) {
        instance.getCommand("manh").setExecutor(new ManHunt());
        instance.getCommand("swap").setExecutor(new DeathSwap());
    }
}
