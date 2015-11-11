package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.misc.Explosion;
import ca.ubc.ece.cpen221.mp4.items.vehicles.M4Sherman;

public class SummonShermanCommand implements Command {
	private final Location loc;

	public SummonShermanCommand(Location location) {
		this.loc = location;
	}

	public void execute(World w) throws InvalidCommandException {
		M4Sherman sherman = new M4Sherman(loc);
		if (Util.isLocationEmpty(w, loc)) {
			w.addActor(sherman);
			w.addItem(sherman);
		} else {
			throw new InvalidCommandException("Location isn't empty");
		}
	}
}
