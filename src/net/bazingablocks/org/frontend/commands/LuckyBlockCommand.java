package net.bazingablocks.org.frontend.commands;

import net.bazingablocks.org.backend.Messages;
import net.bazingablocks.org.frontend.menus.LuckyBlockCreateMenu;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LuckyBlockCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (label.equalsIgnoreCase("luckyblock") || label.equalsIgnoreCase("lb") || label.equalsIgnoreCase("bazingablock") || label.equalsIgnoreCase("bb")){
                if (player.hasPermission("bazingablock.create")) {
                    if (args[0].equalsIgnoreCase("create")) {
                        LuckyBlockCreateMenu.open(player);
                    } else {

                        player.sendMessage("");
                        player.sendMessage("");
                    }
                } else {
                    Messages.format("&4Error &8» &cYou don't have access to that command!");
                }

            }
        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }

}
