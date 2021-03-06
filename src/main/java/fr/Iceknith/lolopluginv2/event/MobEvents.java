package fr.Iceknith.lolopluginv2.event;


import fr.Iceknith.lolopluginv2.commands.BossSpawn;
import fr.Iceknith.lolopluginv2.commands.MobD;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

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
                        int mobHealthB = r.nextInt(200) + 150;
                        NormalHealth = Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
                        Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(NormalHealth + mobHealthB);
                        event.getEntity().setHealth(NormalHealth + mobHealthB);

                        normalSpeed = Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).getBaseValue();
                        Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue((float) r.nextInt(20) / 100 + 0.15 - (float) r.nextInt(5) / 100 + normalSpeed);

                        normalAttack = Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getBaseValue();
                        Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(normalAttack + (float) r.nextInt(100) / 10 + 5 - (float) r.nextInt(10) / 10);

                        normalKnockbackResistance = Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).getBaseValue();
                        Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(normalKnockbackResistance + (float) r.nextInt(75) / 100);


                        Objects.requireNonNull(event.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE)).setBaseValue(50);
                        bossList.add(event.getEntity());
                        bossBarList.add(Bukkit.createBossBar("The God " + event.getEntity().getName(), BarColor.RED, BarStyle.SEGMENTED_10));
                        NormalBossHealth.add(NormalHealth + mobHealthB);
                        LivingEntity livingEntity = (LivingEntity) event.getEntity();
                        Objects.requireNonNull(livingEntity.getEquipment()).setHelmet(new ItemStack(Material.GOLDEN_HELMET, 1));

                        if (event.getEntity().getName().equals("Skeleton")) {
                            if (event.getEntity() instanceof LivingEntity) {
                                ItemStack item = new ItemStack(Material.BOW, 1);
                                int chance = r.nextInt(10);
                                if (chance == 1) {
                                    item.addEnchantment(Enchantment.ARROW_FIRE, 1);
                                }
                                chance = r.nextInt(4) + 1;
                                item.addEnchantment(Enchantment.ARROW_DAMAGE, chance);
                                chance = r.nextInt(1) + 1;
                                item.addEnchantment(Enchantment.ARROW_KNOCKBACK, chance);
                                item.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                                Objects.requireNonNull(livingEntity.getEquipment()).setItemInMainHand(item);

                            }

                        }
                        if (event.getEntity().getName().equals("Pillager")) {
                            if (event.getEntity() instanceof LivingEntity) {
                                ItemStack item = new ItemStack(Material.CROSSBOW, 1);
                                int chance = r.nextInt(1) + 1;
                                item.addEnchantment(Enchantment.PIERCING, 4);
                                item.addEnchantment(Enchantment.MULTISHOT, 1);
                                item.addEnchantment(Enchantment.ARROW_INFINITE, 3);
                                Objects.requireNonNull(livingEntity.getEquipment()).setItemInMainHand(item);

                            }

                        }
                        if (event.getEntity().getName().equals("Creeper")) {
                            Creeper creeper = (Creeper) event.getEntity();
                            creeper.setExplosionRadius(30);
                            creeper.setMaxFuseTicks(150);

                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMobDeth(EntityDeathEvent event) {
        for (Entity boss : bossList) {
            if (event.getEntity() == boss) {
                event.getDrops().clear();
                event.getDrops().add(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1));
            }
        }
    }
}
