package net.bazingablocks.org.frontend;

import net.bazingablocks.org.Main;
import net.bazingablocks.org.backend.LuckyBlockFile;
import net.bazingablocks.org.backend.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class LuckyBlockEvent implements Listener {

    private static Plugin plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onBreak(BlockBreakEvent event){ // Lucky Blocks :D

        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack item = player.getInventory().getItemInMainHand();

        // Loop through lucky blocks
        for (String lb : LuckyBlockFile.get().getConfigurationSection("luckyblocks").getKeys(false)){

            // Check if block is the correct tool
            if (block.getType() == Material.valueOf(LuckyBlockFile.get().getString("luckyblocks." + lb + ".material"))){

                // Check if player is holding the correct tool
                if (LuckyBlockFile.get().getStringList("luckyblocks." + lb + ".requiredTools").contains(item.getType().toString())){

                    // Loop through rewards
                    for (String rewards : LuckyBlockFile.get().getConfigurationSection("luckyblocks." + lb + ".rewards").getKeys(false)){

                        // Probability code
                        if( new Random().nextDouble() <= LuckyBlockFile.get().getDouble("luckyblocks." + lb + ".rewards." + rewards + ".probability") ) {

                            // Run title
                            Messages.sendTitle(player,
                                    Messages.format(LuckyBlockFile.get().getString("luckyblocks." + lb + ".effects.title")).replaceAll("REWARD", LuckyBlockFile.get().getString("luckyblocks." + lb + ".rewards." + rewards + ".rewardName")),
                                    Messages.format(LuckyBlockFile.get().getString("luckyblocks." + lb + ".effects.subtitle").replaceAll("REWARD", LuckyBlockFile.get().getString("luckyblocks." + lb + ".rewards." + rewards + ".rewardName"))),
                                    5,
                                    5,
                                    40);
                            Messages.sendActionbar(player, LuckyBlockFile.get().getString("luckyblocks." + lb + ".effects.actionbar").replaceAll("REWARD", LuckyBlockFile.get().getString("luckyblocks." + lb + ".rewards." + rewards + ".rewardName")));

                            // Send message
                            for (int i = 0;i<LuckyBlockFile.get().getStringList("luckyblocks." + lb + ".effects.message").size();i++){
                                player.sendMessage(Messages.format(LuckyBlockFile.get().getStringList("luckyblocks." + lb + ".effects.message").get(i).replaceAll("REWARD", LuckyBlockFile.get().getString("luckyblocks." + lb + ".rewards." + rewards + ".rewardName"))));
                            }

                            // Play sound
                            player.playSound(player.getLocation(), Sound.valueOf(LuckyBlockFile.get().getString("luckyblocks." + lb + ".effects.sound")), 5, 5);

                            // Play lightning effect
                            if (LuckyBlockFile.get().getBoolean("luckyblocks." + lb + ".effects.lightning") == true){
                                block.getWorld().strikeLightningEffect(block.getLocation());
                            }

                            // Play particles
                            block.getWorld().spawnParticle(Particle.valueOf(LuckyBlockFile.get().getString("luckyblocks." + lb + ".effects.particle")), block.getLocation(), 200);

                            // Run command
                            for (int c = 0;c<LuckyBlockFile.get().getStringList("luckyblocks." + lb + ".rewards." + rewards + ".commands").size();c++){
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), LuckyBlockFile.get().getStringList("luckyblocks." + lb + ".rewards." + rewards + ".commands").get(c).replaceAll("PLAYER", player.getName()));
                            }

                        }

                    }

                }

            }

        }

    }


}
