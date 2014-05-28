package space.entity.ship;

import space.entity.Entity;
import space.entity.ship.modules.Frame;
import space.entity.ship.modules.interfaces.Weapon;

import com.badlogic.gdx.graphics.Texture;

public abstract class Ship extends Entity {
	protected Frame frame;
	protected Weapon[] weapons;
	protected float speed;
	
	public Ship(Texture t, Frame frame) {
		super(t);
	}
}
