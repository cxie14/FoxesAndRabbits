package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class ExplosionCommand implements Command{

	private final Location loc;
	
	public ExplosionCommand(Location location) {
		this.loc=location;
	}
	
	public void execute(World w){
		
		for(Item i : w.getItems()){
			if(i.getLocation().equals(loc)){
				i.loseEnergy(Integer.MAX_VALUE);
				break;
			}
		}
		
		
	}
}
