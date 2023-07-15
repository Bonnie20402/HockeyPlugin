package pt.bonnie20402.hockey.models;
import org.bukkit.Location;

public class SimpleLocation {
    private double x, y, z;

    private float yaw,pitch;

    public SimpleLocation() { }

    public SimpleLocation(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public SimpleLocation(Location location) {
        setLocation(location);
    }
    public void setLocation(Location location) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}