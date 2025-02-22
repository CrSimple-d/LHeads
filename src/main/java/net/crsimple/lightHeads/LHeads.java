package net.crsimple.lightHeads;

import net.crsimple.lightHeads.Commands.LHeadsCommand;
import net.crsimple.lightHeads.Commands.LHeadsCommandCompleter;
import net.crsimple.lightHeads.Events.DeathEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LHeads extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("""
                ----------------
                     Enable
                   LHeads v1.0
                ----------------""");

        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new DeathEvents(), this);

        getCommand("lheads").setExecutor(new LHeadsCommand());
        getCommand("lheads").setTabCompleter(new LHeadsCommandCompleter());
    }

    @Override
    public void onDisable() {
        System.out.println("""
                -----------------
                     Disable
                   LHeads v1.0
                -----------------""");
    }
}
