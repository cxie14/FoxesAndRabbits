package ca.ubc.ece.cpen221.mp4.items.vehicles;

import java.util.Random;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.ExplosionCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;


public class M4Sherman implements ArenaVehicle{
	private static final int MOVING_RANGE = 1;
    private static final double MAX_ACCELERATION = 0.002;
    private static final double MAX_SPEED = 0.05;
    private static final double MIN_SPEED = 0.025;
    private static final double MAX_TURN_SPEED = 0.05;
    private static final double BORDER_LEEWAY = 3;
    private static final double BORDER_SPEED_LEEWAY = 0.1;
    private static final int STRENGTH = 50;
    private static final int INITIAL_ENERGY = 100;
    private static final Direction INITIAL_DIRECTION = Direction.NORTH;
    private static final int PROBABILITY_OF_EXPLOSION = 10;
    
    private static final ImageIcon image = Util.loadImage("m4sherman.gif");
    private static final String name = "M4 Sherman";
    
    private int energy;
    private double speed;
    private Direction direction;
    private Location location;
    
    public M4Sherman(Location initialLocation){
        this.location = initialLocation;
        this.speed = MIN_SPEED;
        this.direction = INITIAL_DIRECTION;
        this.energy = INITIAL_ENERGY;
    }
    
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
        return name;
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
        return this.energy <= 0;
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
        return (int) Math.round(1.0/speed);
    }

    @Override
    public Command getNextAction(World world) {
        //random chance of explosion
    	Random rand = new Random();
    	int randVal = rand.nextInt(PROBABILITY_OF_EXPLOSION);
    	
    	if(randVal== PROBABILITY_OF_EXPLOSION){
    		return new ExplosionCommand(this);
    	}
    	
    	//Slow down before hitting the borders 
        //if total speed decay before crashing is lower or equal to speed leeway, start decelarating 
        if(speed <= MAX_TURN_SPEED){
            direction = Util.getRandomDirection();
        }
        
        switch(this.direction){
       
            case NORTH:
                if(speed-(location.getY()-BORDER_LEEWAY)*MAX_ACCELERATION >= BORDER_SPEED_LEEWAY){
                    if(speed > MIN_SPEED){
                        speed = Math.max(speed - MAX_ACCELERATION, MIN_SPEED);
                    }
                    else{
                        return new WaitCommand();
                    }
                } else{
                    speed = Math.min(speed + MAX_ACCELERATION, MAX_SPEED);
                }
                break;
                
            case SOUTH:
                if(speed-(world.getHeight()-location.getY()-BORDER_LEEWAY)*MAX_ACCELERATION >= BORDER_SPEED_LEEWAY){
                    if(speed > MIN_SPEED){
                        speed = Math.max(speed - MAX_ACCELERATION, MIN_SPEED);
                    }
                    else{
                        return new WaitCommand();
                    }
                } else{
                    speed = Math.min(speed + MAX_ACCELERATION, MAX_SPEED);
                }
                break;
                
            case EAST:
                if(speed-(world.getWidth()-location.getX()-BORDER_LEEWAY)*MAX_ACCELERATION >= BORDER_SPEED_LEEWAY){
                    if(speed > MIN_SPEED){
                        speed = Math.max(speed - MAX_ACCELERATION, MIN_SPEED);
                    }
                    else{
                        return new WaitCommand();
                    }
                } else{
                    speed = Math.min(speed + MAX_ACCELERATION, MAX_SPEED);
                }
                break;
                
            case WEST:
                if(speed-(location.getX()-BORDER_LEEWAY)*MAX_ACCELERATION >= BORDER_SPEED_LEEWAY){
                    if(speed > MIN_SPEED){
                        speed = Math.max(speed - MAX_ACCELERATION, MIN_SPEED);
                    }
                    else{
                        return new WaitCommand();
                    }
                } else{
                    speed = Math.min(speed + MAX_ACCELERATION, MAX_SPEED);
                }
                break;
        }
        
        
        
        Location nextLocation = new Location(location, direction);
        for(Item i : world.searchSurroundings(location, 1)){
            if(i.getLocation().equals(nextLocation) && i.getStrength() < STRENGTH){
                i.loseEnergy(Integer.MAX_VALUE/3);
            }
            else if(i.getLocation().equals(nextLocation) && i.getStrength() >= STRENGTH){
                loseEnergy(Integer.MAX_VALUE/3);
            }
        }
        if(Util.isLocationEmpty(world, nextLocation))
            return new MoveCommand(this, nextLocation);
        else
            return new WaitCommand();
    }
    

}
