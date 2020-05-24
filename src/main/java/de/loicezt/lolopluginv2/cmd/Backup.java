package de.loicezt.lolopluginv2.cmd;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Backup implements CommandExecutor, Runnable {
    private CommandSender s = null;

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
            }
            zipOut.closeEntry();
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args[0]) {
            case "all":
                sender.sendMessage("Backing up all Worlds...");
                new Thread(this).start();
                s = sender;
                break;
            default:
                sender.sendMessage("Couldn't find the option you were looking for");
        }
        return true;
    }

    @Override
    public void run() {
        for (World w : Bukkit.getWorlds()) {
            s.sendMessage("Backing up World " + w.getName() + "...");
            String sourceFile = w.getName();
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");

            String formattedDate = myDateObj.format(myFormatObj);
            String cName = "Backups" + File.separator + "Worlds" + File.separator + w.getName() + File.separator + "Backup_" + w.getName() + "_" + formattedDate + ".zip";


            try {
                new File("Backups" + File.separator + "Worlds" + File.separator + w.getName()).mkdirs();
                File fileToZip = new File(sourceFile);
                fileToZip.createNewFile();
                FileOutputStream fos = new FileOutputStream(cName);
                ZipOutputStream zipOut = new ZipOutputStream(fos);
                zipFile(fileToZip, fileToZip.getName(), zipOut);
                zipOut.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                s.sendMessage("§4Something went horribly wrong while making the backups");
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                s.sendMessage(exceptionAsString);
            }

        }
        s.sendMessage("§aBackups done !");
    }
}
