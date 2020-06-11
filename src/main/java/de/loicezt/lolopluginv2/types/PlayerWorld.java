package de.loicezt.lolopluginv2.types;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PlayerWorld {
    private String visibility;
    private World world;
    private String welcomeMessage;
    private int permissionLevel;
    private boolean isMiniGame;
    private MiniGameTypes type;
    private byte minPlayer;
    private byte maxPlayer;
    private List<Location> playerLoc;
    private boolean canSubmit = true;
    private List<ItemStack> playerKit;

    public boolean isCanSubmit() {
        return canSubmit;
    }

    public void setCanSubmit(boolean canSubmit) {
        this.canSubmit = canSubmit;
    }

    public boolean isMiniGame() {
        return isMiniGame;
    }

    public void setMiniGame(boolean miniGame) {
        isMiniGame = miniGame;
    }

    public MiniGameTypes getType() {
        return type;
    }

    public void setType(MiniGameTypes type) {
        this.type = type;
    }

    public byte getMinPlayer() {
        return minPlayer;
    }

    public void setMinPlayer(byte minPlayer) {
        this.minPlayer = minPlayer;
    }

    public byte getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(byte maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public List<Location> getPlayerLoc() {
        return playerLoc;
    }

    public void setPlayerLoc(List<Location> playerLoc) {
        this.playerLoc = playerLoc;
    }

    public List<ItemStack> getPlayerKit() {
        return playerKit;
    }

    public void setPlayerKit(List<ItemStack> playerKit) {
        this.playerKit = playerKit;
    }

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
