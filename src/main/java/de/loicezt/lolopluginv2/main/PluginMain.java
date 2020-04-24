package de.loicezt.lolopluginv2.main;

import de.loicezt.lolopluginv2.cmd.*;
import de.loicezt.lolopluginv2.cmd.gliding.*;
import de.loicezt.lolopluginv2.cmd.ws.SetWaterslidingParticleAmountMultiplier;
import de.loicezt.lolopluginv2.cmd.ws.SetWaterslidingSpeed;
import de.loicezt.lolopluginv2.events.AnnoyModeEvt;
import de.loicezt.lolopluginv2.events.DolphinEvents;
import de.loicezt.lolopluginv2.events.WsEventsEntity;
import fr.Iceknith.lolopluginv2.commands.CommandIce;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Level;

public class PluginMain extends JavaPlugin implements Listener {


    private static boolean gliding;
    private static boolean debug;
    private static boolean annoy;
    private static float wsSpeed;
    private static float wsPartMult;
    FileConfiguration config = getConfig();


    // This method checks for incoming players and sends them a message
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "[loloPluginV2] &bOMG, You just joined the server!!"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "[loloPluginV2] &eThis server is powered by &2loloPlugin&6V2"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "[loloPluginV2] &eRun &1/lolo &e for more"));
    }


    public static float getWsSpeed() {
        return wsSpeed;
    }

    @Override
    public void onDisable() {
        config.set("annoy", annoy);
        config.set("gliding", gliding);
        config.set("debug", debug);
        config.set("wsSpeed", wsSpeed);
        config.set("wsPartMult", wsPartMult);
        saveConfig();
    }

    public static void setWsSpeed(float wsSpeed) {
        PluginMain.wsSpeed = wsSpeed;
    }

    public static float getWsPartMult() {
        return wsPartMult;
    }

    public static void setWsPartMult(float wsPartMult) {
        PluginMain.wsPartMult = wsPartMult;
    }

    public static boolean isGliding() {
        return gliding;
    }

    public static void setGliding(boolean gliding) {
        PluginMain.gliding = gliding;
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        PluginMain.debug = debug;
    }

    public static boolean isAnnoy() {
        return annoy;
    }

    public static void setAnnoy(boolean annoy) {
        PluginMain.annoy = annoy;
    }

    @Override
    public void onEnable() {
        config.addDefault("gliding", false);
        config.addDefault("debug", false);
        config.addDefault("annoy", false);
        config.addDefault("wsSpeed", 10.0f);
        config.addDefault("wsPartMult", 10.0f);
        config.options().copyDefaults(true);
        saveConfig();
        debug = config.getBoolean("debug");
        annoy = config.getBoolean("annoy");
        gliding = config.getBoolean("gliding");
        annoy = config.getBoolean("annoy");
        wsPartMult = (float) config.getDouble("wsPartMult");
        wsSpeed = (float) config.getDouble("wsSpeed");

        Server server = Bukkit.getServer();
        server.getLogger().log(Level.INFO, "[loloPluginV2] debug : " + String.valueOf(debug));
        server.getLogger().log(Level.INFO, "[loloPluginV2] annoy : " + String.valueOf(annoy));
        server.getLogger().log(Level.INFO, "[loloPluginV2] gliding : " + String.valueOf(gliding));

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
        this.getCommand("ms").setExecutor(new Multispawn());
        this.getCommand("cms").setExecutor(new Cancelms());


        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new SwimCmd(), this);
        getServer().getPluginManager().registerEvents(new WsEventsEntity(), this);
        getServer().getPluginManager().registerEvents(new AnnoyModeEvt(), this);
        getServer().getPluginManager().registerEvents(new DolphinEvents(), this);

        BukkitScheduler s = getServer().getScheduler();
        s.scheduleSyncRepeatingTask(this, new WsEventsEntity(), 0L, 0L);
        s.scheduleSyncRepeatingTask(this, new DolphinEvents(), 0L, 0L);
        s.scheduleSyncRepeatingTask(this, new Multispawn(), 0L, 0L);
    }
}