package com.prexry.NoPhantoms;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class NoPhantomsPlugin extends JavaPlugin implements Listener {
    private boolean spawnNaturally;
    private boolean spawnViaCmd;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        reloadConfig();
        spawnNaturally = getConfig().getBoolean("spawn-naturally");
        spawnViaCmd = getConfig().getBoolean("spawn-via-cmd");
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (!spawnNaturally && event.getEntityType() == EntityType.PHANTOM) {
            event.setCancelled(true);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("nophantoms")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                reloadConfig();
                spawnNaturally = getConfig().getBoolean("spawn-naturally");
                spawnViaCmd = getConfig().getBoolean("spawn-via-cmd");
                sender.sendMessage("Configuration reloaded.");
                return true;
            }
        }
        return false;
    }
}
