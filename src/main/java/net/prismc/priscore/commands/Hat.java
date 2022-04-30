package net.prismc.priscore.commands;

import net.prismc.priscore.api.PrisCoreApi;
import net.prismc.priscore.api.UtilApi;
import net.prismc.priscore.prisplayer.PrisBukkitPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Hat extends UtilApi implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // If sender is a player...
        if (sender instanceof Player) {
            PrisBukkitPlayer p = PrisCoreApi.wrapPrisBukkitPlayer((Player) sender);
            FileConfiguration lang = p.getLanguageFile();

            if (p.hasPermission("prismc.hat")) {
                if (!(p.getPlayer().getInventory().getItemInMainHand().getAmount() == 0)) {
                    ItemStack item = p.getPlayer().getInventory().getItemInMainHand();
                    p.getPlayer().getInventory().setHelmet(item);
                    p.sendMessage(getString(lang, "Commands.Hat.HatSet"));
                } else {
                    p.sendMessage(getString(lang, "Commands.Hat.MustHoldItem"));
                }

            } else {
                p.sendMessage(getString(lang, "Commands.MissingPermission"));
            }

            // If sender is something else...
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[PrisLobby]" + ChatColor.YELLOW + " [Utils] " + ChatColor.RED + "Only players can execute this command!");
        }

        return true;
    }
}
