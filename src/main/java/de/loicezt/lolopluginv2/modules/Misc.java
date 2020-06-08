package de.loicezt.lolopluginv2.modules;

import de.loicezt.lolopluginv2.cmd.*;
import de.loicezt.lolopluginv2.cmd.gliding.GlideCmd;
import de.loicezt.lolopluginv2.cmd.gliding.Propulsate;
import de.loicezt.lolopluginv2.cmd.gliding.UnGlide;
import de.loicezt.lolopluginv2.events.AnnoyModeEvt;
import de.loicezt.lolopluginv2.events.DolphinEvents;
import de.loicezt.lolopluginv2.events.ServerEvents;
import de.loicezt.lolopluginv2.types.Module;
import fr.Iceknith.lolopluginv2.commands.CommandIce;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Misc implements Module {

    String type = "miscellaneous";

    public void enable(JavaPlugin instance) {
        instance.getCommand("glide").setExecutor(new GlideCmd());
        instance.getCommand("unglide").setExecutor(new UnGlide());
        instance.getCommand("propulsate").setExecutor(new Propulsate());
        instance.getCommand("srd").setExecutor(new SummonRideableDolphin());
        instance.getCommand("ms").setExecutor(new Multispawn());
        instance.getCommand("cms").setExecutor(new Cancelms());
        instance.getCommand("lolo").setExecutor(new CmdMain());
        instance.getCommand("ice").setExecutor(new CommandIce());
        instance.getCommand("lbackup").setExecutor(new Backup());
        instance.getCommand("misc").setExecutor(new de.loicezt.lolopluginv2.cmd.Misc());

        Bukkit.getServer().getPluginManager().registerEvents(new GlideCmd(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new AnnoyModeEvt(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new DolphinEvents(), instance);
        Bukkit.getServer().getPluginManager().registerEvents((Listener) instance, instance);
        Bukkit.getServer().getPluginManager().registerEvents(new ServerEvents(), instance);

        BukkitScheduler s = Bukkit.getServer().getScheduler();
        s.scheduleSyncRepeatingTask(instance, new DolphinEvents(), 0L, 0L);
        s.scheduleSyncRepeatingTask(instance, new Multispawn(), 0L, 0L);

    }
}
