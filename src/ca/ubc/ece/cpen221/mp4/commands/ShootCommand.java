package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.World;

public class ShootCommand {
	
	private Direction dir;

	public ShootCommand(Direction direction){
		this.dir=direction;
		new BombShell(dir);
		World.addItem(BombShell);
		World.addActor(BombShell);
	}
}
