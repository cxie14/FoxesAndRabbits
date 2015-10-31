package ca.ubc.ece.cpen221.mp4.ai;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;
import ca.ubc.ece.cpen221.mp4.items.animals.Fox;
import ca.ubc.ece.cpen221.mp4.items.animals.Rabbit;

/**
 * Your Rabbit AI.
 */
public class RabbitAI extends AbstractAI {

	private int closest = 10; // max number; greater than rabbit's view range
	private int temp;
	private boolean foxFound;

	public RabbitAI() {
	}

	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
		// TODO: Change this. Implement your own AI rules.
	    Set<Item> edibles = new HashSet<Item>();
        Set<Item> comrades = new HashSet<Item>();
        for(Item item : world.searchSurroundings(animal)){
            if(item.getStrength() < animal.getStrength() && (item.getPlantCalories()>0)){
                edibles.add(item);
            }
            else if(item.getName().equals(animal.getName())){
                comrades.add(item);
            }
        }
	    

        Random rand = new Random();
	    if(!(animal.getEnergy()>=animal.getMaxEnergy()-15)){
    	    for(Direction direction : Direction.values()){
        	    for(Item item : edibles){
        	        if(item.getLocation().equals(new Location(animal.getLocation(),direction))){
        	            return new EatCommand(animal, item);
        	        }
        	    }
    	    }
    	    
    	    for(Item item : edibles){
    	        Location targetLocation = new Location(
                        animal.getLocation(),
                        Util.getDirectionTowards(
                                animal.getLocation(), 
                                item.getLocation()));
    	        if(Util.isLocationEmpty((World) world, targetLocation)){
                    return new MoveCommand(animal, targetLocation);
                }
    	    } 
	    }
	    else if(comrades.size() == 0 && rand.nextInt(2) == 0){
	        for(Direction direction : Direction.values()){
	            Location breedTarget = new Location(animal.getLocation(), direction);
	            if(Util.isLocationEmpty((World) world, breedTarget)){
	                return new BreedCommand(animal, breedTarget);
	            }
	        }
	    }
	    
        Direction dir = Util.getRandomDirection();
        Location targetLocation = new Location(animal.getLocation(), dir);
        if (Util.isValidLocation(world, targetLocation) && Util.isLocationEmpty((World) world, targetLocation)) {
            return new MoveCommand(animal, targetLocation);
        }

        return new WaitCommand();
	}
}
