package ca.ubc.ece.cpen221.mp4.items.vehicles;

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

public class MotorCycle implements ArenaVehicle{
    private static final int EXPLOSION_ENERGY = 160;
    private static final int MOVING_RANGE = 2;
    private static final double MAX_ACCELERATION = 0.25;
    private static final double MAX_SPEED = 1;
    private static final double MIN_SPEED = 0.15;
    private static final double MAX_TURN_SPEED = 0.5;
    private static final double BORDER_LEEWAY = 3;
    private static final double BORDER_SPEED_LEEWAY = 0.1;
    private static final int STRENGTH = 400;
    private static final int INITIAL_ENERGY = 500;
    private static final Direction INITIAL_DIRECTION = Direction.NORTH;
    
    private static final ImageIcon image = Util.loadImage("motorcycles.gif");
    private static final String name = "Motorcycle";
    
    private int energy;
    private double speed;
    private Direction direction;
    private Location location;
    private boolean turbo;
    
    public MotorCycle(Location initialLocation){
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
        //Slow down before hitting the borders 
        //if total speed decay before crashing is lower or equal to speed leeway, start decelarating 
        this.turbo = false;
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
                    if(speed < MAX_SPEED){
                        speed = Math.min(speed + MAX_ACCELERATION, MAX_SPEED);
                    }
                    else{
                        this.turbo = true;
                    }
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
                    if(speed < MAX_SPEED){
                        speed = Math.min(speed + MAX_ACCELERATION, MAX_SPEED);
                    }
                    else{
                        this.turbo = true;
                    }
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
                    if(speed < MAX_SPEED){
                        speed = Math.min(speed + MAX_ACCELERATION, MAX_SPEED);
                    }
                    else{
                        this.turbo = true;
                    }
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
                    if(speed < MAX_SPEED){
                        speed = Math.min(speed + MAX_ACCELERATION, MAX_SPEED);
                    }
                    else{
                        this.turbo = true;
                    }
                }
                break;
        }
        
        boolean crashed = false;
        Location nextLocation = new Location(location, direction);
        for(Item i : world.searchSurroundings(location, 1)){
            if(i.getLocation().equals(nextLocation) && i.getStrength() < STRENGTH){
                i.loseEnergy(STRENGTH);
                speed = Math.max(speed - 1.5*MAX_ACCELERATION, MIN_SPEED);
            }
            else if(i.getLocation().equals(nextLocation) && i.getStrength() >= STRENGTH){
                loseEnergy(INITIAL_ENERGY);
                crashed = true;
            }
        }
        if(this.turbo){
            Location turboLocation = new Location(nextLocation, direction);
            for(Item i : world.searchSurroundings(location, 2)){
                if(i.getLocation().equals(turboLocation) && i.getStrength() < STRENGTH){
                    i.loseEnergy(Integer.MAX_VALUE/16);
                    speed = Math.max(speed - 1.5*MAX_ACCELERATION, MIN_SPEED);
                }
                else if(i.getLocation().equals(turboLocation) && i.getStrength() >= STRENGTH){
                    loseEnergy(INITIAL_ENERGY);
                    crashed = true;
                }
            }
            nextLocation = turboLocation;
        }
        
        if(crashed && Util.isLocationEmpty(world, location)){
            return new ExplosionCommand(location, EXPLOSION_ENERGY);
        }
        
        if(Util.isLocationEmpty(world, nextLocation))
            return new MoveCommand(this, nextLocation);
        else
            return new WaitCommand();
    }
    
}
