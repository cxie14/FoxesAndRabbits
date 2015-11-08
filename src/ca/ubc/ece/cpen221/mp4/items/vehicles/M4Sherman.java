package vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;
import ca.ubc.ece.cpen221.mp4.items.animals.Bear;

public class M4Sherman implements Vehicles{
	private static final int INITIAL_ENERGY = 1000;
	private static final int MAX_ENERGY = 1500;
	private static final int STRENGTH = 2000;
	private static final int MIN_BREEDING_ENERGY = 2;
	private static final int VIEW_RANGE = 28;
	private static final int COOLDOWN = 15;
	private static final ImageIcon bearImage = Util.loadImage("bear.gif");

	private final AI ai;
	private Location location;
	private int energy;
	
	/**
	 * Create a new {@link M4Sherman} with an {@link AI} at
	 * <code> initialLocation </code>. The <code> initialLoation
	 * </code> must be valid and empty.
	 *
	 * @param M4ShermanAI
	 *            : The AI designed for M4Shermans
	 * @param initialLocation
	 *            : the location where this M4Sherman will be created
	 */
	public M4Sherman(AI M4ShermanAI, Location initialLocation) {
		ai = M4ShermanAI;
		location = initialLocation;
		energy = INITIAL_ENERGY;
	}


	@Override
	public ImageIcon getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "M4 Sherman";
	}

	@Override
	public Location getLocation() {
		location= initialLocation
		return location;
	}

	@Override
	public int getStrength() {
		return STRENGTH;
	}

	@Override
	public void loseEnergy(int energy) {
		// the M4Sherman doesn't lose energy
		
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPlantCalories() {
		// this M4Sherman is not a plant
		return 0;
	}

	@Override
	public int getMeatCalories() {
		// this M4Sherman is not a piece of meat
		return 0;
	}

	@Override
	public void moveTo(Location targetLocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMovingRange() {
		return 40;
	}
	
	
	public int getCoolDownPeriod() {
		return COOLDOWN;
	}

}
