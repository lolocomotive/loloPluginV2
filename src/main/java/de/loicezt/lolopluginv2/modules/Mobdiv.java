package de.loicezt.lolopluginv2.modules;

import de.loicezt.lolopluginv2.types.Module;
import fr.Iceknith.lolopluginv2.BossHandler;
import fr.Iceknith.lolopluginv2.commands.BossSpawn;
import fr.Iceknith.lolopluginv2.commands.MobD;
import fr.Iceknith.lolopluginv2.event.MobEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Mobdiv implements Module {

    String type = "mobdiversity";

    public void enable(JavaPlugin instance) {
        instance.getCommand("mobdiv").setExecutor(new MobD());
        instance.getCommand("bs").setExecutor(new BossSpawn());

        Bukkit.getServer().getPluginManager().registerEvents(new MobEvents(), instance);

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(instance, new BossHandler(), 0L, 0L);

    }
}
