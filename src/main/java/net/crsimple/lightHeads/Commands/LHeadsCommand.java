package net.crsimple.lightHeads.Commands;

import net.crsimple.lightHeads.LHeads;
import net.crsimple.lightHeads.LHeadsGetLogic;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

public class LHeadsCommand extends LHeadsGetLogic implements CommandExecutor {

    FileConfiguration config = LHeads.getPlugin(LHeads.class).getConfig();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage(ChatColor.RED + "You can't use this command through the console");
            return true;
        }

        final Player player = (Player) commandSender;

        if (strings.length == 0) return false;

        final int count = strings.length==3? Integer.parseInt(strings[2]) :1;
        if (count < 1) {
            player.sendMessage(ChatColor.RED + "Count must not be less than 1");
            return true;
        }

        if (strings[0].equalsIgnoreCase("give")) {

            if (!player.hasPermission(new Permission("lheads.command.give"))) {
                commandSender.sendMessage(ChatColor.RED + "You don't have permission");
                return true;
            }
            else if (strings.length == 1) return false;

            ItemStack playerHead;

            try {

                playerHead = getHead(strings[1], count);

            } catch (Exception e) {

                return false;
            }

            player.getInventory().addItem(playerHead);

            player.sendMessage(config.getString("commands.lheads.give.message")
                    .replace("%sender%", commandSender.getName())
                    .replace("%player-head%", strings[1])
                    .replace("%count%", String.valueOf(count)));
            player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_PLACE, 0.5f, 0.5f);

            return true;

        } else if (strings[0].equalsIgnoreCase("random")) {

            if (!player.hasPermission(new Permission("lheads.random"))) return false;
        }
        return false;

    }
}
