package de.loicezt.lolopluginv2.modules;

import de.loicezt.lolopluginv2.cmd.multiworld.*;
import de.loicezt.lolopluginv2.events.GamemodeEvents;
import de.loicezt.lolopluginv2.events.MWorldEvt;
import de.loicezt.lolopluginv2.types.Module;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Multiworld implements Module {

    String type = "multiworld";

    public void enable(JavaPlugin instance) {
        instance.getCommand("amw").setExecutor(new AddMyWorld());
        instance.getCommand("lobby").setExecutor(new Lobby());
        instance.getCommand("fbc").setExecutor(new FreeBuildCommon());
        instance.getCommand("updatesl").setExecutor(new UpdateSignList());
        instance.getCommand("wparam").setExecutor(new WorldParam());
        instance.getCommand("visit").setExecutor(new Visit());
        instance.getCommand("survival").setExecutor(new Survival());

        Bukkit.getServer().getPluginManager().registerEvents(new MWorldEvt(), instance);

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(instance, new GamemodeEvents(), 0L, 0L);
    }
}
