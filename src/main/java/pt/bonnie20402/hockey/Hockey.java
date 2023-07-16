package pt.bonnie20402.hockey;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pt.bonnie20402.hockey.listeners.EntityDamageListener;
import pt.bonnie20402.hockey.listeners.PuckHitListener;
import pt.bonnie20402.hockey.models.PuckCommand;
import pt.bonnie20402.hockey.models.PuckManager;

import java.util.LinkedList;

public final class Hockey extends JavaPlugin {
    private PuckManager puckManager;

    @Override
    public void onEnable() {
        createOjbects();
        registerEvents();
        registerCommands();
        this.getLogger().info("Hockey has been enabled!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    private void createOjbects() {
        puckManager = new PuckManager(this,new LinkedList<>() );
    }
    private void registerEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new EntityDamageListener(puckManager),this);
        Bukkit.getServer().getPluginManager().registerEvents(new PuckHitListener(puckManager),this);
    }
    private void registerCommands() {
        this.getCommand("puck").setExecutor(new PuckCommand(this,puckManager));
    }
}
