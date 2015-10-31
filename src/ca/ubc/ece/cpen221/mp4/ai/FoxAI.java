package ca.ubc.ece.cpen221.mp4.ai;

import java.util.HashSet;
import java.util.Iterator;
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
import ca.ubc.ece.cpen221.mp4.items.animals.*;

/**
 * Your Fox AI.
 */
public class FoxAI extends AbstractAI {
	private int closest = 2; // max number; greater than fox's view range

	public FoxAI() {

	}

	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
		// TODO: Change this. Implement your own AI to make decisions regarding
		// the next action.
	    
	    Set<Item> edibles = new HashSet<Item>();
	    Set<Item> comrades = new HashSet<Item>();
	    for(Item item : world.searchSurroundings(animal)){
            if(item.getStrength() < animal.getStrength() && (item.getMeatCalories()>0)){
                edibles.add(item);
            }
            else if(item.getName().equals(animal.getName())){
                comrades.add(item);
            }
        }
	    
	    
	    if(!(animal.getEnergy()>=animal.getMaxEnergy()-25) && edibles.size()>2){
            for(Direction direction : Direction.values()){
                for(Item item : edibles){
                    if(item.getLocation().equals(new Location(animal.getLocation(),direction))){
                        return new EatCommand(animal, item);
                    }
                }
            }
            
            for(Item item : world.searchSurroundings(animal)){
                Location targetLocation = new Location(
                        animal.getLocation(),
                        Util.getDirectionTowards(
                                animal.getLocation(), 
                                item.getLocation()));
                if(item.getStrength() < animal.getStrength() && item.getMeatCalories()>0 && Util.isLocationEmpty((World) world, targetLocation)){
                    return new MoveCommand(animal, targetLocation);
                }
            } 
        }
        else if(comrades.size() == 0){
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
