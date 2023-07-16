package pt.bonnie20402.hockey.models;

import org.bukkit.entity.Endermite;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Objects;

public class PuckManager {
    private final Plugin plugin;
    private final List<PuckModel> pucks;
    public PuckManager(Plugin plugin, List<PuckModel> pucks) {
        this.plugin = plugin;
        this.pucks = pucks;
    }
    private List<PuckModel> getPucks() {
        return pucks;
    }
    public void addPuck(PuckModel puck) {
        this.pucks.add(puck);
    }
    public boolean hasPuck(PuckModel puck) {
        return this.pucks.contains(puck);
    }
    public boolean hasPuck(Endermite puckToSearch) {
        for(PuckModel puck : pucks) {
            if( Objects.equals(puck.getPuck(), puckToSearch) ) return true;
        }
        return false;
    }
    public PuckModel getPuckModel(Endermite puck) {
        if( !this.hasPuck(puck) ) throw new IllegalStateException("The puck does not exist");
        else {
            for(PuckModel puckelment : pucks) {
                if( Objects.equals(puckelment.getPuck(), puck) ) return puckelment;
            }
        }
        return null;
    }
    public void removePuck(PuckModel puckModel) {
        this.pucks.remove(puckModel);
    }
}
