package net.crsimple.lightHeads;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class LHeadsGetLogic {
    private final FileConfiguration config =
            LHeads.getPlugin(LHeads.class).getConfig();

    public ItemStack getHead(String playerName) {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta) playerHead.getItemMeta();

        headMeta.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
        headMeta.setDisplayName(config.getString("playerHeadSettings.display-name")
                .replace("%player-head%", playerName));

        playerHead.setItemMeta(headMeta);
        return playerHead;
    }

    public ItemStack getHead(String playerName, int count) {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta) playerHead.getItemMeta();

        headMeta.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
        headMeta.setDisplayName(config.getString("playerHeadSettings.display-name")
                .replace("%player-head%", playerName)
                .replace("%count%", String.valueOf(count)));

        playerHead.setItemMeta(headMeta);
        playerHead.setAmount(count);

        return playerHead;
    }
}
