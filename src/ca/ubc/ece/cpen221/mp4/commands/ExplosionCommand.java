package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.misc.Explosion;

/**
 * Construct a {@link ExplosionCommand}, where the item at <code>location<code>
 * is destroyed in an explosion whose has a size determined by <code>energy<code>.
 * The target location must be valid and not empty.
 *
 * @param location
 *            the location of the item to be destroyed
 * @param dir
 * 			the energy of the item at that 
 * @throws InvalidCommandException if the location is not empty
 */
public class ExplosionCommand implements Command{

	private final Location loc;
	private final int energy;
	
	public ExplosionCommand(Location location, int energy) {
	    
		this.loc=location;
		this.energy = energy;
	}
	
	public void execute(World w) throws InvalidCommandException{
	    
	    if (!Util.isLocationEmpty(w, loc)) {
            throw new InvalidCommandException("Invalid ExplosionCommand: non-empty exploding target location"+ loc);
        }
		
		for(Item i : w.getItems()){
			if(i.getLocation().equals(loc)){
				i.loseEnergy(Integer.MAX_VALUE/16);
				break;
			}
		}
		if(Util.isLocationEmpty(w, loc)){
		    Explosion explode = new Explosion(loc, energy);
		
    		w.addActor(explode);
    		w.addItem(explode);
		}
	}
	
	public String toString(){
	    return "Explosion" + loc;
	}
}
