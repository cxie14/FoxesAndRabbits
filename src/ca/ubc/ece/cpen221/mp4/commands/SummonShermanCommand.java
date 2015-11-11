package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.misc.Explosion;
import ca.ubc.ece.cpen221.mp4.items.vehicles.M4Sherman;

/**
 * Constructor where <code>target</code> is the location where the Sherman will appear.
	 * the Sherman must appear at an empty location adjacent to the Squid.

 *
 * @param Location location
 *            the location where the Sherman will appear
 */

public class SummonShermanCommand implements Command {
	private final Location loc;

	public SummonShermanCommand(Location location) {
		this.loc = location;
	}

	public void execute(World w) throws InvalidCommandException {
		M4Sherman sherman = new M4Sherman(loc);
		//creates a Sherman at a an empty location w
		if (Util.isLocationEmpty(w, loc)) {
			w.addActor(sherman);
			w.addItem(sherman);
		} else {
			throw new InvalidCommandException("Location isn't empty");
		}
	}
}
