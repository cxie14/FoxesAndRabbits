
package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;

public abstract class AbstractArenaVehicle implements ArenaVehicle{

    private int MOVING_RANGE;
    private double MAX_ACCELERATION;
    private double MAX_VELOCITY;
    private int STRENGTH;
    
    private ImageIcon image;
    private String name;
    
    private int cooldown;
    private int[] velocity = new int[2];
    private Location location;
    
    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
    }

    @Override
    public int getMovingRange() {
        return MOVING_RANGE;
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public void loseEnergy(int energy) {
        
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public int getPlantCalories() {
        return 0;
    }

    @Override
    public int getMeatCalories() {
        return 0;
    }

    @Override
    public int getCoolDownPeriod() {
        return 0;
    }

    @Override
    public Command getNextAction(World world) {
        return null;
    }

}
