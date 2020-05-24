package de.loicezt.lolopluginv2.main;

import de.loicezt.lolopluginv2.cmd.*;
import de.loicezt.lolopluginv2.cmd.gliding.*;
import de.loicezt.lolopluginv2.cmd.multiworld.*;
import de.loicezt.lolopluginv2.cmd.ws.SetWaterslidingParticleAmountMultiplier;
import de.loicezt.lolopluginv2.cmd.ws.SetWaterslidingSpeed;
import de.loicezt.lolopluginv2.events.*;
import de.loicezt.lolopluginv2.types.PlayerWorld;
import fr.Iceknith.lolopluginv2.BossHandler;
import fr.Iceknith.lolopluginv2.commands.BossSpawn;
import fr.Iceknith.lolopluginv2.commands.CommandIce;
import fr.Iceknith.lolopluginv2.commands.MobD;
import fr.Iceknith.lolopluginv2.event.MobEvents;
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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PluginMain extends JavaPlugin implements Listener {

    private static boolean gliding;
    private static boolean debug;
    private static boolean annoy;
    private static float wsSpeed;
    private static float wsPartMult;
    private static PluginMain instance;
    private static List<PlayerWorld> playerWorlds = new ArrayList<>();

    FileConfiguration config = getConfig();

    public static PluginMain getInstance() {
        return instance;
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

    public static void setWsSpeed(float wsSpeed) {
        PluginMain.wsSpeed = wsSpeed;
        getInstance().config.set("wsSpeed", wsSpeed);
        getInstance().saveConfig();
    }

    public static void setGliding(boolean gliding) {
        PluginMain.gliding = gliding;
        getInstance().config.set("gliding", gliding);
        getInstance().saveConfig();
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

    public static void setDebug(boolean debug) {
        PluginMain.debug = debug;
        getInstance().config.set("debug", debug);
        getInstance().saveConfig();
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setAnnoy(boolean annoy) {
        PluginMain.annoy = annoy;
        getInstance().config.set("annoy", annoy);
        getInstance().saveConfig();
    }

    public static boolean isAnnoy() {
        return annoy;
    }

    public static List<PlayerWorld> getPlayerWorlds() {
        return playerWorlds;
    }

    public static void setPlayerWorlds(List<PlayerWorld> playerWorlds) {
        PluginMain.playerWorlds = playerWorlds;
    }

    @Override
    public void onEnable() {
        //config handling
        instance = this;
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
        this.getCommand("updatesl").setExecutor(new UpdateSignList());
        this.getCommand("wparam").setExecutor(new WorldParam());
        this.getCommand("visit").setExecutor(new Visit());
        this.getCommand("lbackup").setExecutor(new Backup());

        //Register Event listeners
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new GlideCmd(), this);
        getServer().getPluginManager().registerEvents(new WsEventsEntity(), this);
        getServer().getPluginManager().registerEvents(new AnnoyModeEvt(), this);
        getServer().getPluginManager().registerEvents(new DolphinEvents(), this);
        getServer().getPluginManager().registerEvents(new MobEvents(), this);
        getServer().getPluginManager().registerEvents(new MWorldEvt(), this);
        getServer().getPluginManager().registerEvents(new ServerEvents(), this);

        BukkitScheduler s = getServer().getScheduler();
        //Register repeating tasks
        s.scheduleSyncRepeatingTask(this, new WsEventsEntity(), 0L, 0L);
        s.scheduleSyncRepeatingTask(this, new DolphinEvents(), 0L, 0L);
        s.scheduleSyncRepeatingTask(this, new Multispawn(), 0L, 0L);
        s.scheduleSyncRepeatingTask(this, new BossHandler(), 0L, 0L);
        s.scheduleSyncRepeatingTask(this, new GamemodeEvents(), 0L, 0L);
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.translateAlternateColorCodes('&', "&2 Disabling loloPluginV2, Bye"));
    }

}
