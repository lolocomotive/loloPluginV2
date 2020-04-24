package de.loicezt.lolopluginv2.cmd;

import de.loicezt.lolopluginv2.events.DolphinEvents;
import de.loicezt.lolopluginv2.types.DolphinRideItm;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SummonRideableDolphin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Dolphin d = (Dolphin) p.getWorld().spawnEntity(p.getLocation(), EntityType.DOLPHIN);
            d.setInvulnerable(true);
            p.sendMessage("New rideable dolphin spawned!");
            World w = d.getWorld();
            Location loc = d.getLocation();
            Horse h = (Horse) w.spawnEntity(loc, EntityType.HORSE);
            h.setAI(false);
            h.setTamed(true);
            h.setAdult();
            PotionEffect effect = new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1);
            h.addPotionEffect(effect);
            h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
            DolphinEvents.dolphins.add(new DolphinRideItm((Dolphin) d, h));

            return true;
        }
        return false;
    }

}
