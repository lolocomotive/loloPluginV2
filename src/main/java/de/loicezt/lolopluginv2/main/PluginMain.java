package de.loicezt.lolopluginv2.main;

import de.loicezt.lolopluginv2.cmd.*;
import de.loicezt.lolopluginv2.cmd.gliding.*;
import de.loicezt.lolopluginv2.cmd.multiworld.AddMyWorld;
import de.loicezt.lolopluginv2.cmd.multiworld.FreeBuildCommon;
import de.loicezt.lolopluginv2.cmd.multiworld.Lobby;
import de.loicezt.lolopluginv2.cmd.multiworld.Survival;
import de.loicezt.lolopluginv2.cmd.ws.SetWaterslidingParticleAmountMultiplier;
import de.loicezt.lolopluginv2.cmd.ws.SetWaterslidingSpeed;
import de.loicezt.lolopluginv2.events.*;
import fr.Iceknith.lolopluginv2.BossHandler;
import fr.Iceknith.lolopluginv2.commands.BossSpawn;
import fr.Iceknith.lolopluginv2.commands.CommandIce;
import fr.Iceknith.lolopluginv2.commands.ManHunt;
import fr.Iceknith.lolopluginv2.commands.MobD;
import fr.Iceknith.lolopluginv2.event.MobEvents;
import fr.Iceknith.lolopluginv2.event.TrackerEvent;
import net.luckperms.api.LuckPerms;
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

import java.util.Objects;
import java.util.logging.Level;

public class PluginMain extends JavaPlugin implements Listener {

    private static boolean gliding;
    private static boolean debug;
    private static boolean annoy;
    private static float wsSpeed;
    private static float wsPartMult;
    private static LuckPerms api;


    FileConfiguration config = getConfig();

    public static LuckPerms getApi() {
        return api;
    }

    // This method checks for incoming players and sends them a message
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.performCommand("lobby");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "[loloPluginV2] &bOMG, You just joined the server!!"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "[loloPluginV2] &eThis server is powered by &2loloPlugin&6V2"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "[loloPluginV2] &eRun &1/lolo &e for more"));
    }

    //getters and setters

    public static float getWsSpeed() {
        return wsSpeed;
    }

    public static void setApi(LuckPerms api) {
        PluginMain.api = api;
    }

    @Override
    public void onEnable() {
        //config handling
        api = Objects.requireNonNull(Bukkit.getServicesManager().getRegistration(LuckPerms.class)).getProvider();
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

        //Register all the commands

        this.getCommand("lolo").setExecutor(new CmdMain());
        this.getCommand("glide").setExecutor(new GlideCmd());
        this.getCommand("unglide").setExecutor(new UnGlide());
        this.getCommand("eg").setExecutor(new EnableGliding());
        this.getCommand("dg").setExecutor(new DisableGliding());
        this.getCommand("sws").setExecutor(new SetWaterslidingSpeed());
        this.getCommand("wsp").setExecutor(new SetWaterslidingParticleAmountMultiplier());
        this.getCommand("propulsate").setExecutor(new Propulsate());
        this.getCommand("setdebug").setExecutor(new SetDebug());
        this.getCommand("setannoy").setExecutor(new SetAnnoy());
        this.getCommand("ice").setExecutor(new CommandIce());
        this.getCommand("mobdiv").setExecutor(new MobD());
        this.getCommand("srd").setExecutor(new SummonRideableDolphin());
        this.getCommand("ms").setExecutor(new Multispawn());
        this.getCommand("cms").setExecutor(new Cancelms());
        this.getCommand("amw").setExecutor(new AddMyWorld());
        this.getCommand("lobby").setExecutor(new Lobby());
        this.getCommand("fbc").setExecutor(new FreeBuildCommon());
        this.getCommand("bs").setExecutor(new BossSpawn());
        this.getCommand("survival").setExecutor(new Survival());
        this.getCommand("manh").setExecutor(new ManHunt());

        //Register Event listeners
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new GlideCmd(), this);
        getServer().getPluginManager().registerEvents(new WsEventsEntity(), this);
        getServer().getPluginManager().registerEvents(new AnnoyModeEvt(), this);
        getServer().getPluginManager().registerEvents(new DolphinEvents(), this);
        getServer().getPluginManager().registerEvents(new MobEvents(), this);
        getServer().getPluginManager().registerEvents(new MWorldEvt(), this);
        getServer().getPluginManager().registerEvents(new TrackerEvent(), this);

        BukkitScheduler s = getServer().getScheduler();
        //Register repeating tasks
        s.scheduleSyncRepeatingTask(this, new WsEventsEntity(), 0L, 0L);
        s.scheduleSyncRepeatingTask(this, new DolphinEvents(), 0L, 0L);
        s.scheduleSyncRepeatingTask(this, new Multispawn(), 0L, 0L);
        s.scheduleSyncRepeatingTask(this, new BossHandler(), 0L, 0L);
        s.scheduleSyncRepeatingTask(this, new GamemodeEvents(), 0L, 0L);
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
    public void onDisable() {
        config.set("annoy", annoy);
        config.set("gliding", gliding);
        config.set("debug", debug);
        config.set("wsSpeed", wsSpeed);
        config.set("wsPartMult", wsPartMult);
        saveConfig();

    }

}
