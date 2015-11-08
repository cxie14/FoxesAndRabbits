package ca.ubc.ece.cpen221.mp4.items.misc;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public class BombShell  implements MoveableItem, Actor{
    
    private ImageIcon explosionImage = Util.loadImage("explosion.gif");
    private static final int STRENGTH = 700;
    private static final int MINIMUM_SPREAD_ENERGY = 20;
    private static final int MAXIMUM_ENERGY = 320;
    private static final int ENERGY_DECAY = 5;
    private static final int INVERSE_SPREAD_PROBABILITY = 2;
    private static final int COOLDOWN = 1;
    
    private static final String NAME = "Eat this";
    
    @Override
    public ImageIcon getImage() {
        return ;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Location getLocation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getStrength() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void loseEnergy(int energy) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isDead() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getPlantCalories() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMeatCalories() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCoolDownPeriod() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Command getNextAction(World world) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void moveTo(Location targetLocation) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getMovingRange() {
        // TODO Auto-generated method stub
        return 0;
    }

}
