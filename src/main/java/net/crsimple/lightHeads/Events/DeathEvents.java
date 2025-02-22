package net.crsimple.lightHeads.Events;

import net.crsimple.lightHeads.LHeads;
import net.crsimple.lightHeads.LHeadsGetLogic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import java.util.List;

public class DeathEvents extends LHeadsGetLogic implements Listener {

    private final FileConfiguration config =
            LHeads.getPlugin(LHeads.class).getConfig();

    @EventHandler
    public void onPlayerDie(PlayerDeathEvent event) {
        if(!config.getBoolean("playerHeadSettings.drop-enable")) return;

        final Player player = event.getEntity();

        if(Math.random() < config.getDouble("playerHeadSettings.chance")) {
            ItemStack playerHead = getHead(player.getName());
            event.getDrops().add(playerHead);
        }
    }

    @EventHandler
    public void onMobDie(EntityDeathEvent event) {
        if(!config.getBoolean("mobHeadSettings.drop-enable")) return;

        final Entity entity = event.getEntity();
        if(config.getBoolean("mobHeadSettings.only-mobs-in-the-list") &&
                !(config.getStringList("mobHeadSettings.enable-mobs").contains(entity.getName()))) return;

        else if(Math.random() < config.getDouble("mobHeadSettings.chance")) {

            ItemStack mobHead = getHead(entity.getName());
            mobHead.getItemMeta().setLore(List.of("", "Not real/decorative"));

            event.getDrops().add(mobHead);
        }

    }
}
