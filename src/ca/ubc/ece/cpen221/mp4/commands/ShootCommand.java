package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.Direction;



import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.misc.Explosion;
import ca.ubc.ece.cpen221.mp4.items.misc.BombShell;

<<<<<<< HEAD
public class ShootCommand {
	
	private final Direction dir;
=======
public class ShootCommand implements Command{

>>>>>>> 58ea8950b41695a2ca87ccdd3a4776ec135aeda6
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

