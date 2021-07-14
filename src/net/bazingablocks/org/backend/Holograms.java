package net.bazingablocks.org.backend;

import net.bazingablocks.org.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

public class Holograms {

    private static Plugin plugin = Main.getPlugin(Main.class);

    public static void create(String text, Location location, Integer sec){
        ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND); //Spawn the ArmorStand

        as.setGravity(false);
        as.setCanPickupItems(false);
        as.setCustomName(Messages.format(text));
        as.setCustomNameVisible(true);
        as.setVisible(false);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
            @Override
            public void run(){
                as.remove();
            }
        }, 20L*sec);

    }

}
