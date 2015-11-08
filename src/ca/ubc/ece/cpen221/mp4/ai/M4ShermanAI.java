package ca.ubc.ece.cpen221.mp4.ai;

import java.util.HashSet;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;
import vehicles.Vehicles;

public class M4ShermanAI extends AbstractAI {

	public M4ShermanAI() {

	}

	public Command getNextAction(ArenaWorld world, Vehicles item) {
		
		Set<Item> runOver = new HashSet<Item>();
		Set<Item> crash= new HashSet<Item>();
		
		for (Item things : world.searchSurroundings(item)) {
			if (things.getStrength() < item.getStrength()) {
				runOver.add(things);
			} else if (things.getStrength() > item.getStrength()) {
				crash.add(item);
			}
		}
		
		Direction dir = Util.getRandomDirection();
		Location targetLocation = new Location(item.getLocation(), dir);
		if (Util.isValidLocation(world, targetLocation)) {
			return new MoveCommand(item, targetLocation);
		}
		return new WaitCommand();
	}
}
