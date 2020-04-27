package fr.Iceknith.lolopluginv2;

import fr.Iceknith.lolopluginv2.event.MobEvents;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class BossHandler implements Runnable {
    public Location bossLoc;
    int i;

    @Override
    public void run() {
        i = -1;
        for (Entity boss : MobEvents.bossList) {
            i = i + 1;
            if (boss.isValid()) {
                bossLoc = boss.getLocation();
                bossLoc.setY(bossLoc.getBlockY() + 2);
                MobEvents.bossBarList.get(i).removeAll();
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getLocation().distance(boss.getLocation()) <= 50) {
                        MobEvents.bossBarList.get(i).addPlayer(p);

                    }
                }
                MobEvents.bossBarList.get(i).setProgress(((LivingEntity) boss).getHealth() / MobEvents.NormalBossHealth.get(i));
                boss.getWorld().spawnParticle(Particle.DRAGON_BREATH, boss.getLocation(), 5, .2, .1, .2, .5);
                boss.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, bossLoc, (int) 10, 0, 0, 0);//
            } else {
                MobEvents.bossBarList.get(i).removeAll();
            }
        }
    }
}
