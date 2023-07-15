package pt.bonnie20402.hockey.models;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class PuckCommand implements CommandExecutor {

    private final Plugin plugin;
    private final PuckManager puckManager;

    public PuckCommand(Plugin plugin,PuckManager puckManager) {
        this.plugin = plugin;
        this.puckManager = puckManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player))return true;
        Location spawn = player.getLocation();
        PuckModel puckModel = new PuckModel(plugin,spawn);
        puckManager.addPuck(puckModel);
        player.sendMessage("You spawned the puck omfg yess");
        return true;
    }
}
