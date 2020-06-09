package fr.Iceknith.lolopluginv2.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MiniGames implements CommandExecutor {
    public static List<World> editing = new ArrayList<>();
    public static List<String> types = new ArrayList<>();
    public static List<Integer> minPlayer = new ArrayList<>();
    public static List<Integer> maxPlayer = new ArrayList<>();
    public static List<List<Location>> playerSpawnPos = new ArrayList<>();
    public static List<World> submitting = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        try {
            switch (args[0]) {
                //Set Mini Game Mode
                case "setGame":
                    if (commandSender instanceof Player) {
                        int i = -1;
                        int i2 = -1;
                        for (World worldSearcher : editing) {
                            i2++;
                            if (((Player) commandSender).getWorld() == worldSearcher) {
                                types.set(i2, args[2]);
                            } else {
                                i++;
                                if (i == editing.size()) {
                                    editing.add(((Player) commandSender).getWorld());
                                    types.add(args[2]);
                                    minPlayer.add(null);
                                    maxPlayer.add(null);
                                    playerSpawnPos.add(null);
                                }
                            }
                        }
                    } else {
                        commandSender.sendMessage("You have to be a player to execute this command");
                    }
                    break;


                //Minimum Player
                case "minPlayer":
                    if (commandSender instanceof Player) {
                        int i = -1;
                        int i2 = -1;
                        for (World worldSearcher : editing) {
                            i2++;
                            if (((Player) commandSender).getWorld() == worldSearcher) {
                                minPlayer.set(i2, Integer.valueOf(args[2]));
                            } else {
                                i++;
                                if (i == editing.size()) {
                                    commandSender.sendMessage("You have to give a type to this mini game world to execute this command");
                                }
                            }
                        }
                    } else {
                        commandSender.sendMessage("You have to be a player to execute this command");
                    }
                    break;


                //Maximum PLayer
                case "maxPlayer":
                    if (commandSender instanceof Player) {
                        int i = -1;
                        int i2 = -1;
                        for (World worldSearcher : editing) {
                            i2++;
                            if (((Player) commandSender).getWorld() == worldSearcher) {
                                maxPlayer.set(i2, Integer.valueOf(args[2]));
                                while (playerSpawnPos.get(i2).size() + 1 == maxPlayer.get(i)) {
                                    if (playerSpawnPos.get(i2).size() > maxPlayer.get(i)) {
                                        playerSpawnPos.get(i2).remove(playerSpawnPos.get(i2).size() - 1);
                                    }
                                    if (playerSpawnPos.get(i2).size() < maxPlayer.get(i)) {
                                        playerSpawnPos.add(null);
                                    }
                                }
                            } else {
                                i++;
                                if (i == editing.size()) {
                                    commandSender.sendMessage("You have to give a type to this mini game world to execute this command");
                                }
                            }
                        }
                    } else {
                        commandSender.sendMessage("You have to be a player to execute this command");
                    }


                    //Player Spawn
                case "playerSpawn":
                    if (commandSender instanceof Player) {
                        int i = -1;
                        int i2 = -1;
                        for (World worldSearcher : editing) {
                            i2++;
                            if (((Player) commandSender).getWorld() == worldSearcher) {
                                if (maxPlayer.get(i2) != null) {
                                    if (maxPlayer.get(i2) > Integer.valueOf(args[2])) {
                                        if (Integer.valueOf(args[2]) > 0) {
                                            playerSpawnPos.get(i2).set(Integer.valueOf(args[2]), ((Player) commandSender).getLocation());
                                        }
                                    }
                                } else {
                                    commandSender.sendMessage("You have to give a maximum player amount to this mini game world to execute this command");
                                }
                            } else {
                                i++;
                                if (i == editing.size()) {
                                    commandSender.sendMessage("You have to give a type to this mini game world to execute this command");
                                }
                            }
                        }

                    } else {
                        commandSender.sendMessage("You have to be a player to execute this command");
                    }
                    break;


                //Submitting
                case "submit":
                    if (commandSender instanceof Player) {
                        int i = -1;
                        int i2 = -1;
                        for (World worldSearcher : editing) {
                            i2++;
                            if (((Player) commandSender).getWorld() == worldSearcher) {
                                if (minPlayer.get(i2) == null) {
                                    commandSender.sendMessage("You have to give a minimum player number to this mini game world to execute this command");
                                } else {
                                    if (maxPlayer.get(i2) == null) {
                                        commandSender.sendMessage("You have to give a maximum player number to this mini game world to execute this command");
                                    } else {
                                        if (playerSpawnPos.get(i2).contains(null)) {
                                            commandSender.sendMessage("You have to give a spawn position for the maximum amount of players to this mini game world to execute this command");
                                        } else {
                                            while (submitting.size() + 1 == i2) {
                                                submitting.add(null);
                                            }
                                            submitting.set(i2, ((Player) commandSender).getWorld());
                                        }
                                    }
                                }
                            } else {
                                i++;
                                if (i == editing.size()) {
                                    commandSender.sendMessage("You have to give a type to this mini game world to execute this command");
                                }
                            }
                        }
                    } else {
                        commandSender.sendMessage("You have to be a player to execute this command");
                    }
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            commandSender.sendMessage("Not enough arguments ");
            commandSender.sendMessage("usage /game <1st arg> <2ond arg>");
        } catch (Exception e) {
            commandSender.sendMessage("an error has occurred");
            commandSender.sendMessage("usage /game <1st arg> <2ond arg>");
        }
        return true;
    }
}
