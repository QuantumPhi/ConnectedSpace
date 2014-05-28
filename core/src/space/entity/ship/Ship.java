package space.entity.ship;

import space.entity.Entity;
import space.entity.ship.projectile.Projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Ship extends Entity {
	protected int shields;
	protected double regen;
	protected double speed;
	
	protected Projectile projectile;
	
	public Ship(Texture t, int sh, double r, double s, Projectile p) {
		super(t);
		shields = sh;
		regen = r;
		speed = s;
		projectile = p;
	}
	
	public void update(int delta) {
	    double dx = Gdx.input.getAccelerometerX();
	    double dy = Gdx.input.getAccelerometerY();
	    x += dx;
	    y += dy;
	}
}
