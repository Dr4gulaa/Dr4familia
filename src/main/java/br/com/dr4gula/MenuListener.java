package br.com.dr4gula;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Arrays;
import java.util.Random;

public class MenuListener implements Listener {

    private final BukkitScheduler scheduler;

    public MenuListener(BukkitScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + "Familias Menu")
                && e.getCurrentItem() != null) {

            String[] familia = new String[22];

            familia[0] = "Kamado";
            familia[1] = "Tsugkuni";
            familia[2] = "Himejima";
            familia[3] = "Shinazugawa";
            familia[4] = "Iguro";
            familia[5] = "Kanroji";
            familia[6] = "Agatsuma";
            familia[7] = "Tomioka";
            familia[8] = "Rengoku";
            familia[9] = "Tokito";
            familia[10] = "Uzui";
            familia[11] = "Hashibira";
            familia[12] = "Kocho";
            familia[13] = "Haganezuka";
            familia[14] = "Bitoru";
            familia[15] = "Kusakabe";
            familia[16] = "Sugawara";
            familia[17] = "Takahashi";
            familia[18] = "Koremune";
            familia[19] = "Yamato";
            familia[20] = "Genji";
            familia[21] = "Heishi";





            Player p = (Player) e.getWhoClicked();

            e.setCancelled(true);

            switch (e.getRawSlot()) {
                case 0:  // Sair do menu
                    break;

                case 53: //Sortear
                    p.closeInventory();

                    boolean hasKey = hasKey(p);
                    if (hasKey) {
                        removeKey(p);
                        animatedFamilia(p, familia, true);

                    } else {
                        p.sendMessage(ChatColor.RED + "[Dr4Familia] Você precisa da Key Familias para realizar o sorteio.");
                    }
                    break;

                default:
                    return;
            }


            p.closeInventory();
        }

    }


    public void animatedFamilia(Player p, String[] familia, boolean hasKey) {
        int intervaloInicial = 6;
        int intervaloFinal = 30;
        int iterations = 15;
        int winnerIndex = new Random().nextInt(familia.length);

        int taskId = new BukkitRunnable() {
            private int count = 0;
            private int currentIndex = winnerIndex;
            private int currentInterval = intervaloInicial;

            @Override
            public void run() {
                if (count >= iterations) {
                    this.cancel();
                    enviarTitulo(p, familia[currentIndex], 10, 30, 10, ChatColor.BLUE, "Parabéns!", 2, 10, 2, ChatColor.BLUE);
                    playSoundAtPlayer(p, Sound.BLOCK_ANVIL_LAND, 8.0f, 1.0f);
                    return;
                }

                if (count > iterations - 5) {
                    currentInterval += (intervaloFinal - intervaloInicial) / 5;
                }

                enviarTitulo(p, familia[currentIndex], 2, 10, 2, ChatColor.GOLD, "", 0, 0, 0, ChatColor.BLUE);

                currentIndex = (currentIndex + 1) % familia.length;
                count++;
            }
        }.runTaskTimer(Dr4Familia.getInstance(), 0L, intervaloInicial).getTaskId();

        scheduler.scheduleSyncDelayedTask(Dr4Familia.getPlugin(Dr4Familia.class), () -> {
            scheduler.cancelTask(taskId);
            enviarTitulo(p, familia[winnerIndex], 10, 30, 10, ChatColor.BLUE, "parabéns!", 2, 10, 2, ChatColor.BLUE);
            playSoundAtPlayer(p, Sound.BLOCK_ANVIL_LAND, 8.0f, 1.0f);
        }, intervaloInicial * iterations);
    }

    public static void enviarTitulo(Player p, String title, int fadeInTitle, int stayTitle, int fadeOutTitle, ChatColor colorTitle, String subtitle, int fadeInSubtitle, int staySubtitle, int fadeOutSubtitle, ChatColor colorSubtitle) {
        p.sendTitle(colorTitle + title, colorSubtitle + subtitle, fadeInTitle, stayTitle, fadeOutTitle);
        playSoundAtPlayer(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
    }


    public static void playSoundAtPlayer(Player p, Sound sound, float volume, float pitch) {
        Location location = p.getLocation();
        p.playSound(location, sound, volume, pitch);
    }

    private boolean hasKey(Player p) {
        ItemStack[] inventory = p.getInventory().getContents();
        for (ItemStack item : inventory) {
            if (item != null && item.getType() == Material.TRIPWIRE_HOOK && item.getItemMeta() != null) {
                ItemMeta meta = item.getItemMeta();
                if (meta.hasDisplayName() && meta.getDisplayName().equals(ChatColor.GOLD.toString() + ChatColor.BOLD + "Key Familias")) {
                    return true;
                }
            }
        }
        return false;
    }

    private void removeKey(Player player) {
        ItemStack[] inventoryContents = player.getInventory().getContents();
        ItemStack key = null;

        for (int i = 0; i < inventoryContents.length; i++) {
            ItemStack item = inventoryContents[i];
            if (item != null && item.getType() == Material.TRIPWIRE_HOOK && item.getItemMeta() != null) {
                ItemMeta meta = item.getItemMeta();
                if (meta.hasDisplayName() && meta.getDisplayName().equals(ChatColor.GOLD.toString() + ChatColor.BOLD + "Key Familias")) {
                    key = item;
                    break;
                }
            }
        }

        if (key != null) {
            int amount = key.getAmount();
            if (amount > 1) {
                key.setAmount(amount - 1);
                player.getInventory().setContents(inventoryContents);
                player.sendMessage(ChatColor.GREEN + "[Dr4familia] A Key Familias foi utilizada com sucesso.");
            } else {
                player.getInventory().remove(key);
                player.sendMessage(ChatColor.GREEN + "[Dr4familia] A Key Familias foi utilizada com sucesso.");
            }
        } else {
            player.sendMessage(ChatColor.RED + "[Dr4familia] Você não possui a Key Familias no seu inventário.");
        }
    }


}
