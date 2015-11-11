package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.misc.Explosion;
import ca.ubc.ece.cpen221.mp4.items.misc.AirStrike;
import ca.ubc.ece.cpen221.mp4.items.misc.BombShell;

public class AirStrikeCommand implements Command{

    private final Location loc;
    private final Direction dir;
    
    public AirStrikeCommand(Location location, Direction dir) {
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
        
        AirStrike explode = new AirStrike(loc, dir);
        
        w.addActor(explode);
        w.addItem(explode);
    }
}

