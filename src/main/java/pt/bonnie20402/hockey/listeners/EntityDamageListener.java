package pt.bonnie20402.hockey.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Endermite;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import pt.bonnie20402.hockey.models.PuckManager;

public class EntityDamageListener implements Listener {
    private final PuckManager puckManager;

    public EntityDamageListener(PuckManager puckManager) {
        this.puckManager = puckManager;
    }

    @EventHandler
    public void onPuckDamaged(EntityDamageEvent event) {
        if ( event.getEntity() instanceof Endermite && event.getCause() == EntityDamageEvent.DamageCause.FALL ) {
            Endermite puck = (Endermite) event.getEntity();
            if(puckManager.hasPuck (puck) ) {
                Bukkit.broadcastMessage("well");
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onDamageByPuck(EntityDamageByEntityEvent event) {
        if ( event.getDamager() instanceof Endermite ) {
            Endermite puck = (Endermite) event.getDamager();
            if(puckManager.hasPuck (puck) ) {
                Bukkit.broadcastMessage("no damage prevented");
                event.setCancelled(true);
            }
        }
    }
}
