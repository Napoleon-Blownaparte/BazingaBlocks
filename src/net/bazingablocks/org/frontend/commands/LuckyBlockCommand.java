package net.bazingablocks.org.frontend.commands;

import net.bazingablocks.org.Main;
import net.bazingablocks.org.backend.LuckyBlockFile;
import net.bazingablocks.org.backend.Messages;
import net.bazingablocks.org.frontend.menus.LuckyBlockCreateMenu;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class LuckyBlockCommand implements CommandExecutor {

    private static Plugin plugin = Main.getPlugin(Main.class);

    private static String ver = plugin.getDescription().getVersion();
    private static String auth = plugin.getDescription().getAuthors().get(0);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (label.equalsIgnoreCase("bb")) {
                if (player.hasPermission("bazingablock.admin")) {
                    if (args.length > 0) {
                        if (args[0].equalsIgnoreCase("create")) {
                            if (player.hasPermission("bazingablock.create")) {
                                LuckyBlockCreateMenu.open(player);
                            }
                        } else if (args[0].equalsIgnoreCase("reload")) {
                            if (player.hasPermission("reload")) {
                                LuckyBlockFile.reload();
                            }
                        }
                    } else {
                        player.sendMessage("");
                        Messages.sendJSON(player, "                            &6&lBazinga&e&lBlocks", ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/bazingablocks-1-16-1-17-configurable-lucky-blocks-great-for-prison-servers.94241/",
                                " &8» &7Author: &e" + auth + "\n &8» &7Version: &e" + ver);
                        player.sendMessage("");
                        Messages.sendJSON(player, " &8» &6/bb &ecreate&8: &7Create a Lucky Block", ClickEvent.Action.RUN_COMMAND, "/bb create", "&7Required Permission &8» &cbazingablock.create");
                        Messages.sendJSON(player, " &8» &6/bb &ereload&8: &7Reload the plugin", ClickEvent.Action.RUN_COMMAND, "/bb reload", "&7Required Permission &8» &cbazingablock.reload");
                        player.sendMessage("");
                    }
                }

                } else {
                    Messages.format("&4Error &8» &cYou don't have access to that command!");
                }

            }
        return false;
    }

}
