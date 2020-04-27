package fr.Iceknith.lolopluginv2.event;


import fr.Iceknith.lolopluginv2.commands.BossSpawn;
import fr.Iceknith.lolopluginv2.commands.MobD;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class MobEvents implements Listener {
    public static List<Entity> bossList = new ArrayList<>();
    public static List<BossBar> bossBarList = new ArrayList<>();
    public static List<Double> NormalBossHealth = new ArrayList<>();
    Random r = new Random();
    double normalSpeed;
    double NormalHealth;
    double normalAttack;
    double normalKnockbackResistance;
    double normalFollowRange;

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (MobD.isMobD) {
            if (event.getEntity() instanceof Monster) {
                int mobHealth1 = r.nextInt(8);
                NormalHealth = Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
                Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(NormalHealth + mobHealth1 - 4);
                event.getEntity().setHealth(NormalHealth + mobHealth1 - 4);

                normalSpeed = Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).getBaseValue();
                Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue((float) r.nextInt(10) / 100 - (float) r.nextInt(10) / 100 + normalSpeed);

                normalAttack = Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getBaseValue();
                Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(normalAttack + (float) r.nextInt(20) / 10 - (float) r.nextInt(1) / 10);

                normalKnockbackResistance = Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).getBaseValue();
                Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(normalKnockbackResistance + (float) r.nextInt(5) / 10);

                normalFollowRange = Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE)).getBaseValue();
                Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE)).setBaseValue(normalFollowRange + r.nextInt(10) - 5);

                if (BossSpawn.isBossSpawn) {
                    int random1 = r.nextInt(BossSpawn.Frequence) + 1;
                    if (random1 == 1) {
                        int mobHealthB = r.nextInt(150) + 100;
                        NormalHealth = Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
                        Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(NormalHealth + mobHealthB);
                        event.getEntity().setHealth(NormalHealth + mobHealthB);

                        normalSpeed = Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).getBaseValue();
                        Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue((float) r.nextInt(25) / 100 + 0.1 - (float) r.nextInt(10) / 100 + normalSpeed);

                        normalAttack = Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getBaseValue();
                        Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(normalAttack + (float) r.nextInt(100) / 10 + 5 - (float) r.nextInt(10) / 10);

                        normalKnockbackResistance = Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).getBaseValue();
                        Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(normalKnockbackResistance + (float) r.nextInt(75) / 100);

                        normalFollowRange = Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE)).getBaseValue();
                        Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE)).setBaseValue(normalFollowRange + r.nextInt(20) + 10);
                        bossList.add(event.getEntity());
                        bossBarList.add(Bukkit.createBossBar(event.getEntity().getName(), BarColor.RED, BarStyle.SEGMENTED_10));
                        NormalBossHealth.add(NormalHealth + mobHealthB);

                    }
                }
            }


        }


    }


}
