package fr.Iceknith.lolopluginv2.commands;

import de.loicezt.lolopluginv2.cmd.Backup;
import de.loicezt.lolopluginv2.main.PluginMain;
import de.loicezt.lolopluginv2.types.MiniGameTypes;
import de.loicezt.lolopluginv2.types.PlayerWorld;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

public class MiniGames implements CommandExecutor {
    public static List<World> editing = new ArrayList<>();
    public static List<String> types = new ArrayList<>();
    public static List<Integer> minPlayer = new ArrayList<>();
    public static List<Integer> maxPlayer = new ArrayList<>();
    public static List<List<Location>> playerSpawnPos = new ArrayList<>();
    public static List<World> submitting = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        PlayerWorld pw = null;
        for (PlayerWorld tmp : PluginMain.getPlayerWorlds()) {
            if (tmp.getName().equals(sender.getName())) {
                pw = tmp;
                break;
            }
        }
        FileConfiguration config = PluginMain.getInstance().getConfig();
        if (sender instanceof Player) {
            try {
                switch (args[0]) {
                    //Set Mini Game Mode
                    case "setGame":
                        switch (args[1].toLowerCase()) {
                            case "normal":
                                pw.setType(MiniGameTypes.NORMAL);
                                break;
                            case "air":
                                pw.setType(MiniGameTypes.AIR);
                                break;
                            case "water":
                                pw.setType(MiniGameTypes.WATER);
                                break;
                            case "earth":
                                pw.setType(MiniGameTypes.EARTH);
                                break;
                            case "fire":
                                pw.setType(MiniGameTypes.FIRE);
                                break;
                            default:
                                sender.sendMessage("§4Invalid game type: " + args[1]);
                                return true;
                        }
                        config.set("playerWorldData." + pw.getName() + ".gameType", pw.getType().toString());
                        PluginMain.getInstance().saveConfig();
                        sender.sendMessage("§aSet the game type to " + pw.getType().toString().toLowerCase());
                        break;

                    //Minimum Player
                    case "minPlayer":
                        try {
                            pw.setMinPlayer(Byte.parseByte(args[1]));
                        } catch (Exception e) {
                            sender.sendMessage("§4Invalid number: " + args[1]);
                            return true;
                        }
                        config.set("playerWorldData." + pw.getName() + ".minGamePlayer", pw.getMinPlayer());
                        PluginMain.getInstance().saveConfig();
                        sender.sendMessage("§aSet the game minimum players to " + pw.getMinPlayer());
                        break;


                    //Maximum PLayer
                    case "maxPlayer":
                        try {
                            pw.setMaxPlayer(Byte.parseByte(args[1]));
                        } catch (Exception e) {
                            sender.sendMessage("§4Invalid number: " + args[1]);
                            return true;
                        }
                        config.set("playerWorldData." + pw.getName() + ".maxGamePlayer", pw.getMaxPlayer());
                        PluginMain.getInstance().saveConfig();
                        sender.sendMessage("§aSet the game maximum players to " + pw.getMaxPlayer());
                        break;


                    //Player Spawn
                    case "playerSpawn":
                        try {
                            pw.getPlayerLoc().size();
                        } catch (NullPointerException e) {
                            pw.setPlayerLoc(new ArrayList<>());
                        }
                        if (((Player) sender).getWorld().equals(pw.getWorld())) {
                            pw.getPlayerLoc().add(((Player) sender).getLocation());
                        } else {
                            sender.sendMessage("§4You have to be in your world to do that!");
                            return true;
                        }
                        config.set("playerWorldData." + pw.getName() + ".playerLocation", pw.getPlayerLoc());
                        PluginMain.getInstance().saveConfig();
                        sender.sendMessage("§Added your current location to the spawn locations");
                        break;


                    //Submitting
                    case "submit":
                        if (pw.isCanSubmit()) {
                            try {
                                String.valueOf(pw.getMinPlayer());
                            } catch (NullPointerException e) {
                                sender.sendMessage("§4You must set the minimum player amount first!");
                                return true;
                            }
                            try {
                                String.valueOf(pw.getMaxPlayer());
                            } catch (NullPointerException e) {
                                sender.sendMessage("§4You must set the maximum player amount first!");
                                return true;
                            }
                            try {
                                pw.getPlayerLoc().size();
                            } catch (NullPointerException e) {
                                sender.sendMessage("§4You must set at least " + String.valueOf(pw.getMinPlayer()) + " player spawn locations first!");
                                return true;
                            }
                            try {
                                pw.getType().toString();
                            } catch (NullPointerException e) {
                                sender.sendMessage("§4You must set the game type first!");
                                return true;
                            }
                            if (pw.getPlayerLoc().size() < pw.getMaxPlayer()) {
                                sender.sendMessage("§4You must set at least " + String.valueOf(pw.getMinPlayer()) + " player spawn locations first!");
                                return true;
                            }

                            sender.sendMessage("§eSubmitting your World . . .");

                            PlayerWorld finalPw = pw;
                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    String sourceFile = "World_" + finalPw.getName();
                                    LocalDateTime myDateObj = LocalDateTime.now();
                                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
                                    String formattedDate = myDateObj.format(myFormatObj);
                                    String cName = "Submitted" + File.separator + "Worlds" + File.separator + finalPw.getName() + File.separator + "Minigame_" + finalPw.getName() + "_" + formattedDate + ".zip";
                                    try {
                                        new File("Submitted" + File.separator + "Worlds" + File.separator + finalPw.getName()).mkdirs();
                                        File fileToZip = new File(sourceFile);
                                        fileToZip.createNewFile();
                                        FileOutputStream fos = new FileOutputStream(cName);
                                        ZipOutputStream zipOut = new ZipOutputStream(fos);
                                        Backup.zipFile(fileToZip, fileToZip.getName(), zipOut);
                                        zipOut.close();
                                        fos.close();
                                        sender.sendMessage("§aDone !");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        sender.sendMessage("§4Something went wrong while submitting ur world");
                                        StringWriter sw = new StringWriter();
                                        e.printStackTrace(new PrintWriter(sw));
                                        String exceptionAsString = sw.toString();
                                        sender.sendMessage(exceptionAsString);
                                    }


                                }
                            };
                            new Thread(r).start();

                        } else {
                            sender.sendMessage("§4you cannot submit more than once a day!");
                        }
                        break;
                    default:
                        sender.sendMessage("§4invalid options");


                }

            } catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage("§4Not enough arguments ");
                sender.sendMessage("usage /game <1st arg> <2ond arg>");
            } catch (Exception e) {
                e.printStackTrace();
                sender.sendMessage("§4An unknown error has occurred");
                sender.sendMessage("usage /game <1st arg> <2ond arg>");
            }
        } else {
            sender.sendMessage("§4You have to be a player to execute this command !");
        }
        return true;
    }

}
