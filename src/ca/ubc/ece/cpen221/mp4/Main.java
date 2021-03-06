package ca.ubc.ece.cpen221.mp4;

import javax.swing.SwingUtilities;

import ca.ubc.ece.cpen221.mp4.ai.*;
import ca.ubc.ece.cpen221.mp4.items.Gardener;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.animals.*;
import ca.ubc.ece.cpen221.mp4.items.vehicles.M4Sherman;
import ca.ubc.ece.cpen221.mp4.items.vehicles.MotorCycle;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Panzer;
import ca.ubc.ece.cpen221.mp4.staff.WorldImpl;
import ca.ubc.ece.cpen221.mp4.staff.WorldUI;

/**
 * The Main class initialize a world with some {@link Grass}, {@link Rabbit}s,
 * {@link Fox}es, {@link Gnat}s, {@link Gardener}, etc.
 *
 * You may modify or add Items/Actors to the World.
 *
 */
public class Main {

	static final int X_DIM = 40;
	static final int Y_DIM = 40;
	static final int SPACES_PER_GRASS = 7;
	static final int INITIAL_GRASS = X_DIM * Y_DIM / SPACES_PER_GRASS;
	static final int INITIAL_GNATS = INITIAL_GRASS / 4;
	static final int INITIAL_RABBITS = INITIAL_GRASS / 4;
	static final int INITIAL_FOXES = INITIAL_GRASS / 32;
	static final int INITIAL_TIGERS = INITIAL_GRASS / 32;
	static final int INITIAL_BEARS = INITIAL_GRASS / 40;
	static final int INITIAL_PLATYPUSES= INITIAL_GRASS / 16;
	static final int INITIAL_HYENAS = INITIAL_GRASS / 32;
	static final int INITIAL_CARS = INITIAL_GRASS / 100;
	static final int INITIAL_TRUCKS = INITIAL_GRASS / 150;
	static final int INITIAL_MOTORCYCLES = 12; //INITIAL_GRASS / 32;
	static final int INITIAL_MANS = INITIAL_GRASS / 150;
	static final int INITIAL_WOMANS = INITIAL_GRASS / 100;
	static final int INITIAL_HUNTERS = INITIAL_GRASS / 150;
	static final int INITIAL_SQUID= 1;

	static final int INITIAL_SHERMANS= 10;

	
	static final int INITIAL_PANZERS = 5;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main().createAndShowWorld();
			}
		});
	}

	public void createAndShowWorld() {
		World world = new WorldImpl(X_DIM, Y_DIM);
		initialize(world);
		new WorldUI(world).show();
	}

	public void initialize(World world) {
		addGrass(world);
		world.addActor(new Gardener());

		addGnats(world);
		addRabbits(world);
		addFoxes(world);
		addPanzers(world);
		addMotorcycles(world);
		addShermans(world);
		addPlatypuses(world);
		addSquid(world);
		// TODO: You may add your own creatures here!
	}

	private void addMotorcycles(World world) {
	    for (int i = 0; i < INITIAL_MOTORCYCLES; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            MotorCycle cycle = new MotorCycle(loc);
            world.addItem(cycle);
            world.addActor(cycle);
        }
    }

    private void addPanzers(World world) {
	    for (int i = 0; i < INITIAL_PANZERS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Panzer panzer = new Panzer(loc);
            world.addItem(panzer);
            world.addActor(panzer);
        }
    }

    private void addGrass(World world) {
		for (int i = 0; i < INITIAL_GRASS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			world.addItem(new Grass(loc));
		}
	}

	private void addGnats(World world) {
		for (int i = 0; i < INITIAL_GNATS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Gnat gnat = new Gnat(loc);
			world.addItem(gnat);
			world.addActor(gnat);
		}
	}

	private void addFoxes(World world) {
		FoxAI foxAI = new FoxAI();
		for (int i = 0; i < INITIAL_FOXES; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Fox fox = new Fox(foxAI, loc);
			world.addItem(fox);
			world.addActor(fox);
		}
	}

	private void addRabbits(World world) {
		RabbitAI rabbitAI = new RabbitAI();
		for (int i = 0; i < INITIAL_RABBITS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Rabbit rabbit = new Rabbit(rabbitAI, loc);
			world.addItem(rabbit);
			world.addActor(rabbit);
		}
	}
	
	private void addShermans(World world){
		for(int i = 0; i< INITIAL_SHERMANS; i++){
			Location loc= Util.getRandomEmptyLocation(world);
			M4Sherman sherman= new M4Sherman(loc);
			world.addItem(sherman);
			world.addActor(sherman);
		}
	}
	
	private void addPlatypuses(World world){
		PlatypusAI platypusAI = new PlatypusAI();
		for(int i=0; i<INITIAL_PLATYPUSES; i++){
			Location loc= Util.getRandomEmptyLocation(world);
			Platypus platypus= new Platypus(platypusAI, loc);
			world.addItem(platypus);
			world.addActor(platypus);
		}
	}
	
	private void addSquid(World world){
		SquidAI squidAI= new SquidAI();
		for(int i=0; i<INITIAL_SQUID; i++){
			Location loc= Util.getRandomEmptyLocation(world);
			Squid squid= new Squid(squidAI, loc);
			world.addItem(squid);
			world.addActor(squid);
		}
	}
}
