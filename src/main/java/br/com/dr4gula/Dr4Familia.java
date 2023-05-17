package br.com.dr4gula;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Dr4Familia extends JavaPlugin {
    private int taskId;
    private static Dr4Familia instance;
    @Override
    public void onEnable() {
        instance = this;
        getCommand("familia").setExecutor(new MenuCommand());
        getCommand("familiagivekey").setExecutor(new GiveKeyCommand());
        // Registrar o MenuListener como listener e fornecer o BukkitScheduler
        getServer().getPluginManager().registerEvents(new MenuListener(getServer().getScheduler()), this);


        System.out.println("[Dr4familia] Plugin iniciado!");

    }

    @Override
    public void onDisable() {

        getServer().getScheduler().cancelTask(taskId);
        System.out.println("[Dr4familia] Plugin Finalizado!");
    }

    public static Dr4Familia getInstance() {
        return instance;
    }
}
