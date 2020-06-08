package de.loicezt.lolopluginv2.types;

import org.bukkit.World;

public class PlayerWorld {
    private String visibility;
    private World world;
    private String welcomeMessage;
    private int permissionLevel;

    public PlayerWorld(World world) {
        this.world = world;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public String getName() {
        return world.getName().substring(6);
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String toString() {
        String s = "World :" + world.getName();
        s = s + " visibility:" + visibility;
        return s;
    }
}
