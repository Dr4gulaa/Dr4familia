package br.com.dr4gula;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Arrays;

import static org.bukkit.Material.*;

public class MenuCommand implements CommandExecutor{


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;

            if(p.hasPermission("familia.menu")) {


                Inventory inv = Bukkit.createInventory(p, 54, ChatColor.GOLD.toString() + ChatColor.BOLD + "Familias Menu");

                //Familias Lendarias
                ItemStack Flend = new ItemStack(RED_DYE);
                ItemMeta metaL = Flend.getItemMeta();
                metaL.setDisplayName(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Familias Lendarias");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.RED + "Kamado 0,5\n");
                lore.add(ChatColor.RED + "Tsugkuni 1,25%\n");
                lore.add(ChatColor.RED + "Himejima 1,25%\n");
                lore.add(ChatColor.RED + "Shinazugawa 2%");
                metaL.setLore(lore);
                Flend.setItemMeta(metaL);
                inv.setItem(13, Flend);

                //Familias Epicas
                ItemStack Fepic = new ItemStack(PURPLE_DYE);
                ItemMeta metaF = Fepic.getItemMeta();
                metaF.setDisplayName(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "Familias Epicas");
                ArrayList<String> lore2 = new ArrayList<>();
                lore2.add(ChatColor.LIGHT_PURPLE + "Iguro 1,5%\n");
                lore2.add(ChatColor.LIGHT_PURPLE + "Kanroji 1,5%\n");
                lore2.add(ChatColor.LIGHT_PURPLE + "Agatsuma 1,5%\n");
                lore2.add(ChatColor.LIGHT_PURPLE + "Tomioka 1,5%\n");
                lore2.add(ChatColor.LIGHT_PURPLE + "Rengoku 1,5%\n");
                lore2.add(ChatColor.LIGHT_PURPLE + "Tokito 1,5%");
                metaF.setLore(lore2);
                Fepic.setItemMeta(metaF);
                inv.setItem(22, Fepic);

                //Familias Raras
                ItemStack Frare = new ItemStack(GREEN_DYE);
                ItemMeta metaR = Frare.getItemMeta();
                metaR.setDisplayName(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Familias Raras");
                ArrayList<String> lore3 = new ArrayList<>();
                lore3.add(ChatColor.GREEN + "Uzui 9%\n");
                lore3.add(ChatColor.GREEN + "Hashibira 9%\n");
                lore3.add(ChatColor.GREEN + "Kocho 9%\n");
                lore3.add(ChatColor.GREEN + "Haganezuka 9%");
                metaR.setLore(lore3);
                Frare.setItemMeta(metaR);
                inv.setItem(31, Frare);

                //Familias Comuns
                ItemStack Fcomum = new ItemStack(BLUE_DYE);
                ItemMeta metaC = Fcomum.getItemMeta();
                metaC.setDisplayName(ChatColor.DARK_BLUE.toString() + ChatColor.BOLD + "Familias Comuns 50%");
                ArrayList<String> lore4 = new ArrayList<>();
                lore4.add(ChatColor.BLUE + "Bitoru 6,25%\n");
                lore4.add(ChatColor.BLUE + "Kusakabe 6,25%\n");
                lore4.add(ChatColor.BLUE + "Sugawara 6,25%\n");
                lore4.add(ChatColor.BLUE + "Takahashi 6,25%");
                lore4.add(ChatColor.BLUE + "Koremune 6,25%");
                lore4.add(ChatColor.BLUE + "Yamato 6,25%");
                lore4.add(ChatColor.BLUE + "Genji 6,25%");
                lore4.add(ChatColor.BLUE + "Heishi 6,25%");
                metaC.setLore(lore4);
                Fcomum.setItemMeta(metaC);
                inv.setItem(40, Fcomum);

                //Botão Close
                ItemStack close = new ItemStack(BARRIER);
                ItemMeta closeMeta = close.getItemMeta();
                closeMeta.setDisplayName(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Sair!");
                close.setItemMeta(closeMeta);
                inv.setItem(0, close);

                //Botão Spin
                ItemStack spin = new ItemStack(EMERALD);
                ItemMeta spinMeta = spin.getItemMeta();
                spinMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Roletar!");
                spin.setItemMeta(spinMeta);
                inv.setItem(53, spin);
                //Frame
                ItemStack frame = new ItemStack(BLACK_STAINED_GLASS);
                for (int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 16, 17, 18, 26, 27, 35, 36, 37, 44, 45, 46, 47, 48, 49, 50, 51, 52}) {
                    inv.setItem(i, frame);
                }


                p.openInventory(inv);
            }else{
                p.sendMessage(ChatColor.RED + "Você não tem permissão para usar esse comando.");
            }
        }

        return false;
    }
}
