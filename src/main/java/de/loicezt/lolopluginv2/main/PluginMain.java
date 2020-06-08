package de.loicezt.lolopluginv2.main;

import de.loicezt.lolopluginv2.modules.Misc;
import de.loicezt.lolopluginv2.modules.Mobdiv;
import de.loicezt.lolopluginv2.modules.Multiworld;
import de.loicezt.lolopluginv2.modules.Watersliding;
import de.loicezt.lolopluginv2.types.Module;
import de.loicezt.lolopluginv2.types.PlayerWorld;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

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
    private static ArrayList<Module> enabledModules = new ArrayList<>();

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
        getInstance().config.set("moduleData.misc.wsSpeed", wsSpeed);
        getInstance().saveConfig();
    }

    public static void setGliding(boolean gliding) {
        PluginMain.gliding = gliding;
        getInstance().config.set("moduleData.misc.gliding", gliding);
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
        getInstance().config.set("moduleData.misc.debug", debug);
        getInstance().saveConfig();
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setAnnoy(boolean annoy) {
        PluginMain.annoy = annoy;
        getInstance().config.set("moduleData.misc.annoy", annoy);
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
        config.addDefault("moduleData.misc.gliding", false);
        config.addDefault("moduleData.misc.debug", false);
        config.addDefault("moduleData.misc.annoy", false);
        config.addDefault("moduleData.watersliding.wsSpeed", 10.0f);
        config.addDefault("moduleData.watersliding.wsPartMult", 10.0f);
        config.addDefault("modules", new String[]{"multiworld", "watersliding", "mobdiversity", "miscellaneous"});
        config.options().copyDefaults(true);
        saveConfig();
        debug = config.getBoolean("moduleData.misc.debug");
        annoy = config.getBoolean("moduleData.misc.annoy");
        gliding = config.getBoolean("moduleData.misc.gliding");
        annoy = config.getBoolean("moduleData.misc.annoy");
        wsPartMult = (float) config.getDouble("moduleData.watersliding.wsPartMult");
        wsSpeed = (float) config.getDouble("moduleData.watersliding.wsSpeed");
        Server server = Bukkit.getServer();
        server.getLogger().log(Level.INFO, "[loloPluginV2] debug : " + String.valueOf(debug));
        server.getLogger().log(Level.INFO, "[loloPluginV2] annoy : " + String.valueOf(annoy));
        server.getLogger().log(Level.INFO, "[loloPluginV2] gliding : " + String.valueOf(gliding));

        List<String> moduleString = (List<String>) config.getList("modules");

        for (String tmpModule : moduleString) {
            server.getLogger().log(Level.INFO, "[loloPluginV2] Adding module " + tmpModule);
        }

        getCommand("module").setExecutor(new de.loicezt.lolopluginv2.cmd.Module());

        enabledModules.add(new Multiworld());
        enabledModules.add(new Watersliding());
        enabledModules.add(new Misc());
        enabledModules.add(new Mobdiv());

        enabledModules.forEach((module) -> module.enable(this));
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.translateAlternateColorCodes('&', "&2 Disabling loloPluginV2, Bye"));
    }

}
