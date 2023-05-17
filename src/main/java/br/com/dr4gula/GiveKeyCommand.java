package br.com.dr4gula;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GiveKeyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.hasPermission("familia.givekey")) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);

                    if (target != null) {
                        ItemStack key = new ItemStack(Material.TRIPWIRE_HOOK);
                        ItemMeta keyMeta = key.getItemMeta();
                        keyMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Key Familias");
                        keyMeta.setLore(Arrays.asList(" ", ChatColor.GRAY + "Digite /familia para utilizar a chave"));
                        keyMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                        keyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        key.setItemMeta(keyMeta);

                        target.getInventory().addItem(key);
                        target.sendMessage(ChatColor.GREEN + "[Dr4familia] Você recebeu uma Key Familias de " + p.getName());
                    } else {
                        p.sendMessage(ChatColor.RED + "[Dr4familia]: Jogador não encontrado.");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "[Dr4familia]: Uso correto: /familia givekey [playername]");
                }
            } else {
                p.sendMessage(ChatColor.RED + "[Dr4familia]: Você não tem permissão para usar esse comando.");
            }
        }

        return false;
    }
}