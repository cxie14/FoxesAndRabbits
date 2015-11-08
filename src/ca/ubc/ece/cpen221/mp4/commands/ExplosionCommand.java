package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.misc.Explosion;

public class ExplosionCommand implements Command{

	private final Location loc;
	private final int energy;
	
	public ExplosionCommand(Location location, int energy) {
		this.loc=location;
		this.energy = energy;
	}
	
	public void execute(World w){
		
		for(Item i : w.getItems()){
			if(i.getLocation().equals(loc)){
				i.loseEnergy(Integer.MAX_VALUE);
				break;
			}
		}
		
		Explosion explode = new Explosion(loc, energy);
		
		w.addActor(explode);
		w.addItem(explode);
	}
}
