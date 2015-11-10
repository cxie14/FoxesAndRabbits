package ca.ubc.ece.cpen221.mp4.items.misc;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.ExplosionCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public class BombShell  implements MoveableItem, Actor{
    
    private ImageIcon shellImage = Util.loadImage("explosion.gif");
    private static final int STRENGTH = 200;
    private static final int INITIAL_ENERGY = 320;
    private static final int COOLDOWN = 1;
    private static final int MAX_MOVE_RANGE = 2;
    
    private static final String NAME = "Eat this";
    
    private Location location;
    private int energy;
    private Direction shotDirection;
    
    public BombShell(Direction shotDir, Location location) {
        this.location = location;
        this.shotDirection = shotDir;
        this.energy = this.INITIAL_ENERGY;
    }
    
    @Override
    public ImageIcon getImage() {
        return this.shellImage;
    }

    @Override
    public String getName() {
        return this.NAME;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public void loseEnergy(int energy) {
        this.energy -= energy;
    }

    @Override
    public boolean isDead() {
        return energy < 0;
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
        return 1;
    }

    @Override
    public Command getNextAction(World world) {
        Location nextLocation = new Location(this.location, shotDirection);
        Location nextNextLocation = new Location(nextLocation, shotDirection);
        
        for(Item i : world.getItems()){
            if(i.getLocation().equals(nextLocation)){
                if(i.getStrength() > this.STRENGTH){
                    return new ExplosionCommand(this.location, this.energy);
                } else{
                    i.loseEnergy(this.STRENGTH);
                }
            }
        }
        for(Item i : world.getItems()){
            if(i.getLocation().equals(nextNextLocation)){
                if(i.getStrength() > this.STRENGTH){
                    return new ExplosionCommand(nextLocation, this.energy);
                } else{
                    i.loseEnergy(this.STRENGTH);
                }
            }
        }
        if(Util.isLocationEmpty(world, nextLocation) && Util.isLocationEmpty(world, nextNextLocation)){
            return new MoveCommand(this, nextNextLocation);
        } else {
            return new ExplosionCommand(this.location, this.energy);
        }
    }

    @Override
    public void moveTo(Location targetLocation) {
        this.location = targetLocation;
    }

    @Override
    public int getMovingRange() {
        // TODO Auto-generated method stub
        return MAX_MOVE_RANGE;
    }

}
