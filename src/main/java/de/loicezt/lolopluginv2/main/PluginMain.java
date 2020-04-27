package de.loicezt.lolopluginv2.main;

import de.loicezt.lolopluginv2.cmd.*;
import de.loicezt.lolopluginv2.cmd.gliding.*;
import de.loicezt.lolopluginv2.cmd.multiworld.AddMyWorld;
import de.loicezt.lolopluginv2.cmd.multiworld.FreeBuildCommon;
import de.loicezt.lolopluginv2.cmd.multiworld.Lobby;
import de.loicezt.lolopluginv2.cmd.ws.SetWaterslidingParticleAmountMultiplier;
import de.loicezt.lolopluginv2.cmd.ws.SetWaterslidingSpeed;
import de.loicezt.lolopluginv2.events.AnnoyModeEvt;
import de.loicezt.lolopluginv2.events.DolphinEvents;
import de.loicezt.lolopluginv2.events.WsEventsEntity;
import fr.Iceknith.lolopluginv2.BossHandler;
import fr.Iceknith.lolopluginv2.commands.BossSpawn;
import fr.Iceknith.lolopluginv2.commands.CommandIce;
import fr.Iceknith.lolopluginv2.commands.MobD;
import fr.Iceknith.lolopluginv2.event.EventMain;
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
    private static boolean debug;
    private static boolean annoy;
    private static float wsSpeed;
    private static float wsPartMult;
    FileConfiguration config = getConfig();


    @Override
    public void onEnable() {
        config.addDefault("youAreAwesome", true);
        config.options().copyDefaults(true);
        saveConfig();
        gliding = false;

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

        //Register Event listeners
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new GlideCmd(), this);
        getServer().getPluginManager().registerEvents(new WsEventsEntity(), this);
        getServer().getPluginManager().registerEvents(new AnnoyModeEvt(), this);
        getServer().getPluginManager().registerEvents(new DolphinEvents(), this);
        getServer().getPluginManager().registerEvents(new EventMain(), this);

        BukkitScheduler s = getServer().getScheduler();
        //Register repeating tasks
        s.scheduleSyncRepeatingTask(this, new WsEventsEntity(), 0L, 0L);
        s.scheduleSyncRepeatingTask(this, new DolphinEvents(), 0L, 0L);
        s.scheduleSyncRepeatingTask(this, new Multispawn(), 0L, 0L);
        s.scheduleSyncRepeatingTask(this, new BossHandler(), 0L, 0L);
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
