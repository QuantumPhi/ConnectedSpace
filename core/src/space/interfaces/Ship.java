package space.interfaces;

import space.entity.Entity;
import space.entity.ship.modules.Frame;

import com.badlogic.gdx.graphics.Texture;

public abstract class Ship extends Entity {
	protected int shields;
	protected double regen;
	
	protected Projectile[] weapons;
	protected double speed;
	
	public Ship(Texture t, int health, double r, double s) {
		super(t);
		shields = health;
		regen = r;
		speed = s;
		
		initWeapons();
	}
	
	private void initWeapons() {
	    
	}
}
