package de.loicezt.lolopluginv2.types;

import org.bukkit.plugin.java.JavaPlugin;

public interface Module {

    String type = null;

    public abstract void enable(JavaPlugin instance);

}
