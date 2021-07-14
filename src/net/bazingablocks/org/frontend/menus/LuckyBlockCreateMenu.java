package net.bazingablocks.org.frontend.menus;

import net.bazingablocks.org.Main;
import net.bazingablocks.org.backend.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class LuckyBlockCreateMenu implements Listener {

    private static Plugin plugin = Main.getPlugin(Main.class);
    private static String ver = plugin.getDescription().getVersion();
    private static String auth = plugin.getDescription().getAuthors().get(0);

    public static void open(Player player){

        Inventory inv = Bukkit.createInventory(null, 54, Messages.format("&6&lBazinga&e&lBlocks &8» &eConfigure"));

        // Header and footer
        ItemStack b = new ItemStack(Material.BLACK_STAINED_GLASS_PANE); ItemStack g = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta bM = b.getItemMeta();      ItemMeta gM = g.getItemMeta();

        bM.setDisplayName(" "); gM.setDisplayName(" ");
        b.setItemMeta(bM);      g.setItemMeta(gM);

        ItemStack back = new ItemStack(Material.ARROW); ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(Messages.format("&8» &cClose this menu"));  back.setItemMeta(backMeta);

        ItemStack plug = new ItemStack(Material.BOOK);  ItemMeta plugMeta = plug.getItemMeta();
        plugMeta.setDisplayName(Messages.format("&6&lBazinga&e&lBlocks")); plugMeta.setLore(Arrays.asList(
                " ",
                Messages.format(" &8» &7Version: &e" + ver),
                Messages.format(" &8» &7Author: &e" + auth),
                " "));
        plug.setItemMeta(plugMeta);

        // Set header/footer items
        for (int i = 0;i<8;i++){ inv.setItem(i, g); }   for (int i = 44;i<53;i++){ inv.setItem(i, g); }
        inv.setItem(0, b); inv.setItem(3, b); inv.setItem(5, b); inv.setItem(8, b);
        inv.setItem(45, b); inv.setItem(48, b); inv.setItem(50, b); inv.setItem(53, b);

        inv.setItem(4, plug);
        inv.setItem(49, back);

        player.openInventory(inv);

    }

    @EventHandler
    public void onClick(InventoryClickEvent event){

        Player player = Bukkit.getPlayer(event.getWhoClicked().getName());
        Integer slot = event.getSlot();

        if (event.getView().getTitle().equalsIgnoreCase(Messages.format("&6&lBazinga&e&lBlocks &8» &eConfigure"))){

            event.setCancelled(true);

        }

    }


}
