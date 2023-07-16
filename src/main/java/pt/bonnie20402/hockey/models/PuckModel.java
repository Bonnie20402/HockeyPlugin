package pt.bonnie20402.hockey.models;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class PuckModel implements Listener {

    private static final double GRAVITY = 0.06;
    private static final double FRICTION = 0.02;
    private static final float MIN_Y = 6.0f;
    private Endermite puck;
    private Vector direction;
    private final Plugin plugin;

    public PuckModel(Plugin plugin, Location initialLocation) {
        this.plugin = plugin;
        World world = initialLocation.getWorld();
        direction = new Vector(0, 0, 0);
        puck = (Endermite) world.spawnEntity(initialLocation, EntityType.ENDERMITE);
        puck.setGlowing(false);
        puck.setGliding(false);
        puck.setSilent(true);
        puck.setPersistent(true);
        puck.setVisualFire(false);
        puck.setInvulnerable(true);
        applySlownessEffect();
        startMoveTask();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private void applySlownessEffect() {
        LivingEntity livingEntity = puck;
        PotionEffect slownessEffect = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1000, true, false, false);
        livingEntity.addPotionEffect(slownessEffect);
    }

    private void startMoveTask() {
        PuckModel puckModel = this;
        new BukkitRunnable() {
            @Override
            public void run() {
                Location currentLocation = puck.getLocation();
                Location newLocation = currentLocation.add(direction);
                Block block = newLocation.getBlock();
                if (block.getType() != Material.AIR) {
                    // Collision with a block, adjust direction
                    Vector rotatedDirection = rotateVector(direction, Math.PI / 2);
                    newLocation = currentLocation.add(rotatedDirection);

                    // Reverse the direction if the new location is still obstructed
                    Block newBlock = newLocation.getBlock();
                    if (newBlock.getType() != Material.AIR) {
                        rotatedDirection.multiply(-1);
                        newLocation = currentLocation.add(rotatedDirection);
                    }
                    direction = rotatedDirection;
                }
                puck.teleport(newLocation);
            }
        }.runTaskTimer(plugin, 0L, 1L);

        // Gravity and friction TASK
        new BukkitRunnable() {
            @Override
            public void run() {
                Vector velocity = puckModel.getDirection();

                // Apply friction to slow down the puck
                velocity.multiply(1 - FRICTION);

                // Apply gravity
                if (puck.getLocation().getY() > MIN_Y) {
                    velocity.setY(velocity.getY() - GRAVITY);
                }

                puckModel.setDirection(velocity);
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    private Vector normalizeVector(Vector vector) {
        double magnitude = Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY() + vector.getZ() * vector.getZ());
        return vector.multiply(1.0 / magnitude);
    }

    private Vector rotateVector(Vector vector, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = vector.getX() * cos - vector.getZ() * sin;
        double z = vector.getX() * sin + vector.getZ() * cos;
        return new Vector(x, vector.getY(), z);
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public Endermite getPuck() {
        return puck;
    }
}
