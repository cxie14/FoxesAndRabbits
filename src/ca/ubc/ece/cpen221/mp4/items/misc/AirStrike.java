package ca.ubc.ece.cpen221.mp4.items.misc;

import java.util.Random;

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

public class AirStrike implements MoveableItem, Actor{
    
    private static final int STRENGTH = 9001;
    private static final int INITIAL_ENERGY = Integer.MAX_VALUE;
    private static final int COOLDOWN = 1;
    private static final int MAX_MOVE_RANGE = 1;
    
    private static final int INVERSE_PROBABILITY_MOVE = 2;
    
    private static final String NAME = "Death from above";
    private static final ImageIcon image = Util.loadImage("zeppelin.gif");
    
    private Location location;
    private Direction dir;
    private int energy;
    
    public AirStrike(Location location, Direction dir) {
        this.location = location;
        this.dir = dir;
    }
    
    @Override
    public int getCoolDownPeriod() {
        return 0;
    }

    @Override
    public Command getNextAction(World world) {
        Random rand = new Random();
        if(rand.nextInt(INVERSE_PROBABILITY_MOVE)==0){
            Location nextLocation = new Location(location, dir);
            for(Item i : world.searchSurroundings(location, 1)){
                if(i.getLocation().equals(nextLocation) && i.getStrength() < STRENGTH){
                    i.loseEnergy(STRENGTH);
                }
                else if(i.getLocation().equals(nextLocation) && i.getStrength() >= STRENGTH){
                    loseEnergy(INITIAL_ENERGY);
                }
            }
            if(Util.isLocationEmpty(world, nextLocation)){
                return new MoveCommand(this, nextLocation);
                
            } else{
                this.energy = -1;
                return new ExplosionCommand(this.location, 320);
            }
            
        }
        
        return new ExplosionCommand(Util.getRandomEmptyLocation(world), 800);
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public String getName() {
        return this.NAME;
    }

    @Override
    public Location getLocation() {
        return location;
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
        return this.energy < 0;
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
    public void moveTo(Location targetLocation) {
        this.location = targetLocation;
        
    }

    @Override
    public int getMovingRange() {
        // TODO Auto-generated method stub
        return MAX_MOVE_RANGE;
    }
    
}
