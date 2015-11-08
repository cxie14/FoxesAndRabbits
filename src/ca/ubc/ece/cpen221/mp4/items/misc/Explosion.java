package ca.ubc.ece.cpen221.mp4.items.misc;

import java.util.Random;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public class Explosion implements MoveableItem, Actor{
    
    private ImageIcon explosionImage = Util.loadImage("explosion.gif");
    private static final int STRENGTH = 700;
    private static final int MINIMUM_SPREAD_ENERGY = 20;
    private static final int MAXIMUM_ENERGY = 320;
    private static final int ENERGY_DECAY = 5;
    private static final int INVERSE_SPREAD_PROBABILITY = 2;
    private static final int COOLDOWN = 1;
    
    private static final String NAME = "Kaboom, baby";
    
    private int energy;
    private Location location;
    
    @Override
    public ImageIcon getImage() {
        // TODO Auto-generated method stub
        return this.explosionImage;
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
        return this.STRENGTH;
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
        // Don't try
        return 0;
    }

    @Override
    public int getMeatCalories() {
        // Probably not a good idea
        return 0;
    }

    @Override
    public int getCoolDownPeriod() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Command getNextAction(World world) {
        this.loseEnergy(ENERGY_DECAY);
        Random rand = new Random();
        
        for(Direction direction : Direction.values()){
            Location nextLocation = new Location(this.location, direction);
            if(0 == rand.nextInt(this.INVERSE_SPREAD_PROBABILITY)){
                for(Item i : world.searchSurroundings(this.location, 1)){
                    if(i.getLocation() == nextLocation){
                        if(i.getStrength() < this.STRENGTH){
                            i.loseEnergy(STRENGTH);
                        }
                    }
                }
                if(Util.isLocationEmpty(world, nextLocation)){
                    return new ExplosionCommand(nextLocation);
                }
            }
        }
        return null;
    }

    @Override
    public void moveTo(Location targetLocation) {
        this.location = targetLocation;
    }

    @Override
    public int getMovingRange() {
        return 0;
    }

}
