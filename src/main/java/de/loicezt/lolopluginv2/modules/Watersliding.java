package de.loicezt.lolopluginv2.modules;

import de.loicezt.lolopluginv2.cmd.ws.SetWaterslidingParticleAmountMultiplier;
import de.loicezt.lolopluginv2.cmd.ws.SetWaterslidingSpeed;
import de.loicezt.lolopluginv2.events.WsEventsEntity;
import de.loicezt.lolopluginv2.types.Module;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Watersliding implements Module {

    String type = "watersliding";

    public void enable(JavaPlugin instance) {

        instance.getCommand("sws").setExecutor(new SetWaterslidingSpeed());
        instance.getCommand("wsp").setExecutor(new SetWaterslidingParticleAmountMultiplier());

        Bukkit.getServer().getPluginManager().registerEvents(new WsEventsEntity(), instance);

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(instance, new WsEventsEntity(), 0L, 0L);

    }
}
