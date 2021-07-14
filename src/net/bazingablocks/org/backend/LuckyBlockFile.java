package net.bazingablocks.org.backend;

import net.bazingablocks.org.Main;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.Boolean.TRUE;

public class LuckyBlockFile {

    private static Plugin plugin = Main.getPlugin(Main.class);

    public static FileConfiguration get(){

        File file = new File(plugin.getDataFolder(), "luckyblocks.yml");
        FileConfiguration yml = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()){

            // Create new file
            try {
                file.mkdir();
                file.createNewFile();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return yml;

    }

    public static void save(){

        File file = new File(plugin.getDataFolder(), "luckyblocks.yml");
        FileConfiguration yml = YamlConfiguration.loadConfiguration(file);

        // If file exists, save the file
        if (file.exists()){
            try {
                yml.save(file);
                System.out.println("[BazingaBlocks] Successfully saved file 'luckyblocks.yml'");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { // If file doesn't exist, create new file and contents

            try {

                File dir = file.getParentFile();
                dir.mkdirs();

                file.createNewFile();
                yml.set("luckyblocks." + 1 + ".material", "DIAMOND_BLOCK");
                yml.set("luckyblocks." + 1 + ".requiredTools", Arrays.asList("DIAMOND_PICKAXE", "NETHERITE_PICKAXE"));

                yml.set("luckyblocks." + 1 + ".effects.title", "&e&ki &6&lLUCKY &e&lBLOCK &6&ki");
                yml.set("luckyblocks." + 1 + ".effects.subtitle", "&7You found &cREWARD&7!");
                yml.set("luckyblocks." + 1 + ".effects.actionbar", "&7You found &cREWARD&7 in your Lucky Block!");
                yml.set("luckyblocks." + 1 + ".effects.particle", "SOUL_FIRE_FLAME");
                yml.set("luckyblocks." + 1 + ".effects.sound", "BLOCK_BEACON_ACTIVATE");
                yml.set("luckyblocks." + 1 + ".effects.lightning", true);
                yml.set("luckyblocks." + 1 + ".effects.message", Arrays.asList(
                        " ",
                        " &6&lLUCKY &e&lBLOCK &8» &7Congratulations, you found &cREWARD &7in your Lucky Block!",
                        " "
                ));
                yml.set("luckyblocks." + 1 + ".rewards." + 1 + ".probability", 0.5);
                yml.set("luckyblocks." + 1 + ".rewards." + 1 + ".rewardName", "16x Diamonds");
                yml.set("luckyblocks." + 1 + ".rewards." + 1 + ".commands", Arrays.asList(
                        "give PLAYER diamond 16"
                ));

                yml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}