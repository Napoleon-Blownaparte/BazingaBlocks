package net.bazingablocks.org.backend;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {

    public static void sendJSON(Player player, String message, ClickEvent.Action clickAction, String intOut, String tooltip){
        TextComponent msg = new TextComponent(ChatColor.translateAlternateColorCodes('&', message));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', tooltip)).create()));
        msg.setClickEvent(new ClickEvent(clickAction, ChatColor.translateAlternateColorCodes('&', intOut)));
        player.spigot().sendMessage(msg);
    }

    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int fadeOut, int showTime){
        player.sendTitle(format(title), format(subtitle), fadeIn, showTime, fadeOut);
    }

    public static void sendActionbar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public static String format(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
