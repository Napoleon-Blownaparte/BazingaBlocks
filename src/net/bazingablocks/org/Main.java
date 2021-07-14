package net.bazingablocks.org;

import net.bazingablocks.org.backend.LuckyBlockFile;
import net.bazingablocks.org.frontend.LuckyBlockEvent;
import net.bazingablocks.org.frontend.commands.LuckyBlockCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {

        // Save files
        LuckyBlockFile.save();

        // Register Events
        getServer().getPluginManager().registerEvents(new LuckyBlockEvent(), this);

        // Register Commands
        this.getCommand("bb").setExecutor(new LuckyBlockCommand());

    }


}
