package ca.ubc.ece.cpen221.mp4.ai;

import java.util.HashSet;
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
import ca.ubc.ece.cpen221.mp4.commands.SummonShermanCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

public class SquidAI {
		private int closest = 5; // max number; greater than bear's view range

		public SquidAI() {

		}

		public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
			// TODO: Change this. Implement your own AI to make decisions regarding
			// the next action.

			Set<Item> edibles = new HashSet<Item>();
			Set<Item> comrades = new HashSet<Item>();
			for (Item item : world.searchSurroundings(animal)) {
					edibles.add(item);
			}

		
				for (Direction direction : Direction.values()) {
					for (Item item : edibles) {
						if (item.getLocation().equals(new Location(animal.getLocation(), direction))) {
							return new EatCommand(animal, item);
						}
					}
				}

				for (Item item : ((World) world).searchSurroundings(animal.getLocation(), 1)) {
					Location targetLocation = new Location(animal.getLocation(),
							Util.getRandomDirection());
					if (Util.isLocationEmpty((World) world, targetLocation)) {
						return new MoveCommand(animal, targetLocation);
					}
				}
			
				for (Direction direction : Direction.values()) {
					Location location = new Location(animal.getLocation(), direction);
					if (Util.isLocationEmpty((World) world, location)) {
						return new SummonShermanCommand(location);
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
