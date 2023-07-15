package pt.bonnie20402.hockey.listeners;

import org.bukkit.entity.Endermite;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;
import pt.bonnie20402.hockey.models.PuckManager;
import pt.bonnie20402.hockey.models.PuckModel;

public class PuckHitListener implements Listener {
    private final PuckManager puckManager;

    public PuckHitListener(PuckManager puckManager) {
        this.puckManager = puckManager;
    }

    @EventHandler
    public void onPuckHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player) {
            if (event.getEntity() instanceof Endermite puckEntity) {
                if (puckManager.hasPuck(puckEntity)) {
                    event.setCancelled(true);
                    PuckModel puck = puckManager.getPuckModel(puckEntity);

                    // Calculate the new direction based on player's yaw angle
                    double yawRadians = Math.toRadians(player.getLocation().getYaw());
                    double x = -Math.sin(yawRadians);
                    double y = 0.0;
                    double z = Math.cos(yawRadians);
                    Vector newDir = new Vector(x, y, z);

                    // Apply a force multiplier to control the intensity of the shot
                    double forceMultiplier = 0.5; // Adjust this value to control the force of the shot
                    newDir.multiply(forceMultiplier);

                    // Set the new direction of the puck
                    puck.setDirection(newDir);
                }
            }
        }
    }
}
