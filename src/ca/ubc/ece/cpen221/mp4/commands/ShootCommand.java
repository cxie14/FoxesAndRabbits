package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.Direction;



import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.misc.Explosion;
import ca.ubc.ece.cpen221.mp4.items.misc.BombShell;

/**
 * Construct a {@link ShootCommand}, where the bombShells are shot in the 
 * <code>dir<code>  of the <code>location</code> of the item to be destroyed.
 * The target location must be within <code>item</code>'s moving range and 
 * the target location must be empty and valid.
 *
 * @param location
 *            the location of the item to be destroyed
 * @param dir
 * 			the direction in which the BombShell will be shot
 */

public class ShootCommand implements Command{
	private final Direction dir;
    private final Location loc;
   
    
    public ShootCommand(Location location, Direction dir) {
        this.loc=location;
        this.dir = dir;
    }
    
    public void execute(World w){
        
        for(Item i : w.getItems()){
            if(i.getLocation().equals(loc)){
                i.loseEnergy(Integer.MAX_VALUE/16);
                break;
            }
        }
        
        BombShell explode = new BombShell(dir, loc);
        
        w.addActor(explode);
        w.addItem(explode);
    }

}

