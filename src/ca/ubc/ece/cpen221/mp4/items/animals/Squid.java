package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.ai.BearAI;
import ca.ubc.ece.cpen221.mp4.ai.SquidAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Squid implements ArenaAnimal{
	
	private static final int INITIAL_ENERGY = 300;
	private static final int MAX_ENERGY = 500;
	private static final int STRENGTH = Integer.MAX_VALUE;
	private static final int MIN_BREEDING_ENERGY = 0;
	private static final int VIEW_RANGE = 40;
	private static final int COOLDOWN = 25;
	private static final ImageIcon squidImage = Util.loadImage("squid.gif");

	private final SquidAI ai;

	private Location location;
	private int energy;

	/**
	 * Create a new {@link Squid} with an {@link AI} at
	 * <code> initialLocation </code>. The <code> initialLoation
	 * </code> must be valid and empty.
	 *
	 * @param ai2
	 *            : The AI designed for squid
	 * @param initialLocation
	 *            : the location where this squid will be created
	 */
	public Squid(SquidAI squidAI, Location initialLocation) {
		ai = squidAI;
		location = initialLocation;
		energy = INITIAL_ENERGY;
	}

	@Override
	public LivingItem breed() {
		Squid child = new Squid(ai, location);
		child.energy = energy / 2;
		this.energy = energy / 2;
		return child;
	}

	@Override
	public void eat(Food food) {
		// Note that energy does not exceed energy limit.
		energy = Math.min(MAX_ENERGY, energy + food.getPlantCalories());
	}

	@Override
	public int getCoolDownPeriod() {
		return COOLDOWN;
	}

	@Override
	public int getEnergy() {
		return energy;
	}

	@Override
	public ImageIcon getImage() {
		return squidImage;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public int getMaxEnergy() {
		return MAX_ENERGY;
	}

	@Override
	public int getMeatCalories() {
		// The amount of meat calories it provides is equal to its current
		// energy.
		return energy;
	}

	@Override
	public int getMinimumBreedingEnergy() {
		return MIN_BREEDING_ENERGY;
	}

	@Override
	public int getMovingRange() {
		return 5; // Can only move to adjacent locations.
	}

	@Override
	public String getName() {
		return "Squid";
	}

	@Override
	public Command getNextAction(World world) {
		Command nextAction = ai.getNextAction(world, this);
		this.energy--; // Loses 1 energy regardless of action.
		return nextAction;
	}

	@Override
	public int getPlantCalories() {
		// This Squid is not a plant.
		return 0;
	}

	@Override
	public int getStrength() {
		return STRENGTH;
	}

	@Override
	public int getViewRange() {
		return VIEW_RANGE;
	}

	@Override
	public boolean isDead() {
		return false;
	}

	@Override
	public void loseEnergy(int energyLoss) {
		
	}

	@Override
	public void moveTo(Location targetLocation) {
		location = targetLocation;

	}


}
