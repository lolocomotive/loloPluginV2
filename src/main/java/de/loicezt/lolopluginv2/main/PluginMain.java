package de.loicezt.lolopluginv2.main;

import de.loicezt.lolopluginv2.cmd.CmdMain;
import de.loicezt.lolopluginv2.cmd.SetAnnoy;
import de.loicezt.lolopluginv2.cmd.SetDebug;
import de.loicezt.lolopluginv2.cmd.SummonRideableDolphin;
import de.loicezt.lolopluginv2.cmd.gliding.*;
import de.loicezt.lolopluginv2.cmd.ws.SetWaterslidingParticleAmountMultiplier;
import de.loicezt.lolopluginv2.cmd.ws.SetWaterslidingSpeed;
import de.loicezt.lolopluginv2.events.AnnoyModeEvt;
import de.loicezt.lolopluginv2.events.DolphinEvents;
import de.loicezt.lolopluginv2.events.WsEventsEntity;
import fr.Iceknith.lolopluginv2.commands.CommandIce;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class PluginMain extends JavaPlugin implements Listener {

    public static boolean gliding;
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.addDefault("youAreAwesome", true);
        config.options().copyDefaults(true);
        saveConfig();
        gliding = false;
        this.getCommand("lolo").setExecutor(new CmdMain());
        this.getCommand("glide").setExecutor(new SwimCmd());
        this.getCommand("unglide").setExecutor(new UnGlide());
        this.getCommand("eg").setExecutor(new EnableGliding());
        this.getCommand("dg").setExecutor(new DisableGliding());
        this.getCommand("sws").setExecutor(new SetWaterslidingSpeed());
        this.getCommand("wsp").setExecutor(new SetWaterslidingParticleAmountMultiplier());
        this.getCommand("propulsate").setExecutor(new Propulsate());
        this.getCommand("setdebug").setExecutor(new SetDebug());
        this.getCommand("setannoy").setExecutor(new SetAnnoy());
        this.getCommand("ice").setExecutor(new CommandIce());
        this.getCommand("srd").setExecutor(new SummonRideableDolphin());


        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new SwimCmd(), this);
        getServer().getPluginManager().registerEvents(new WsEventsEntity(), this);
        getServer().getPluginManager().registerEvents(new AnnoyModeEvt(), this);
        getServer().getPluginManager().registerEvents(new DolphinEvents(), this);

        BukkitScheduler s = getServer().getScheduler();
        s.scheduleSyncRepeatingTask(this, new WsEventsEntity(), 0L, 0L);
        s.scheduleSyncRepeatingTask(this, new DolphinEvents(), 0L, 0L);
    }

    // This method checks for incoming players and sends them a message
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "[loloPluginV2] &bOMG, You just joined the server!!"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "[loloPluginV2] &eThis server is powered by &2loloPlugin&6V2"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "[loloPluginV2] &eRun &1/lolo &e for more"));
    }
}
