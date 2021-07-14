package net.bazingablocks.org;

import net.bazingablocks.org.backend.LuckyBlockFile;
import net.bazingablocks.org.frontend.LuckyBlockEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {

        // Save files
        LuckyBlockFile.save();

        // Register Events
        getServer().getPluginManager().registerEvents(new LuckyBlockEvent(), this);

        // Register Commands
//        this.getCommand("pickaxe").setExecutor(new LuckyBlockCommand());

    }


}
